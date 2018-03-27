package eu.driver.adapter.core;

import java.util.HashMap;
import java.util.Map;

import org.apache.avro.generic.IndexedRecord;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;

import eu.driver.adapter.constants.AdapterMode;
import eu.driver.adapter.constants.TopicConstants;
import eu.driver.adapter.core.consumer.AdapterCallbackConsumer;
import eu.driver.adapter.core.consumer.AdminHeartbeatConsumer;
import eu.driver.adapter.core.consumer.GenericCallbackConsumer;
import eu.driver.adapter.core.consumer.TimeConsumer;
import eu.driver.adapter.core.consumer.TopicInviteConsumer;
import eu.driver.adapter.core.producer.GenericProducer;
import eu.driver.adapter.core.producer.HeartbeatProducer;
import eu.driver.adapter.core.producer.LogProducer;
import eu.driver.adapter.excpetion.CommunicationException;
import eu.driver.adapter.logger.CISLogger;
import eu.driver.adapter.properties.ClientProperties;
import eu.driver.adapter.properties.ConsumerProperties;
import eu.driver.adapter.properties.ProducerProperties;
import eu.driver.api.GenericAvroReceiver;
import eu.driver.api.IAdaptorCallback;
import eu.driver.model.core.Timing;
import eu.driver.model.edxl.EDXLDistribution;
import eu.driver.model.core.Log;
import eu.driver.model.core.TopicInvite;

public class CISAdapter {

	
	private static CISAdapter aMe = null;
	
	private String clientID = null;
	/**
	 * Kafka Producer shared by all specific Producers for sending Avro messages to
	 * the CIS.
	 */
	private Producer<EDXLDistribution, IndexedRecord> sharedAvroProducer;

	/*
	 * The Core Producers
	 */
	private HeartbeatProducer heartbeatProducer;
	private LogProducer logProducer;
	
	/*
	 * The type specific producers
	 */
	private Map<String, GenericProducer> producerMap = new HashMap<String, GenericProducer>();
	/*
	 * The type specific consumers
	 */
	private Map<String, AdapterCallbackConsumer> consumerMap = new HashMap<String, AdapterCallbackConsumer>();

	
	/*
	 * The type specific callbacks
	 */
	private Map<String, IAdaptorCallback> callbackMap = new HashMap<String, IAdaptorCallback>();
	
	/*
	 * The invites to topics
	 */
	private Map<String, TopicInvite> topicInvitesPublisher = new HashMap<String, TopicInvite>();
	private Map<String, TopicInvite> topicInvitesConsumers = new HashMap<String, TopicInvite>();

	private CISLogger logger = new CISLogger(CISAdapter.class);
	
	private Timing timing = null;
	
	private AdapterMode adpterMode = AdapterMode.DEV_MODE;
	private Boolean connectModeSec = false;
	private Boolean adapterInitDone = false;

	private CISAdapter() {
		producerMap = new HashMap<>();
		try {
			initializeProducers();
		} catch (Exception e) {
			
		}
		this.clientID = ClientProperties.getInstance().getProperty("client.id");
	}
	
	public static synchronized CISAdapter getInstance() {
		if (CISAdapter.aMe == null) {
			CISAdapter.aMe = new CISAdapter();
		}
		return CISAdapter.aMe;
	}
	
	public String getClientID() {
		return this.clientID;
	}
	
	
	/**
	 * Initializes the core producers used by the CIS Adapter
	 */
	private void initializeProducers() {
		logger.info("--> initializeProducers");
		// actual Kafka producer used by all generic producers s
		sharedAvroProducer = new KafkaProducer<EDXLDistribution, IndexedRecord>(ProducerProperties.getInstance(connectModeSec));
		try {
			logger.info("Check Adpter DEV Mode");
			heartbeatProducer = new HeartbeatProducer(sharedAvroProducer, TopicConstants.HEARTBEAT_TOPIC);	
			heartbeatProducer.sendInitialHeartbeat();
			addAvroReceiver(TopicConstants.ADMIN_HEARTBEAT_TOPIC, new AdminHeartbeatConsumer());
			adpterMode = AdapterMode.DEV_MODE;
		} catch (Exception cEx) {
			logger.info("CISAdapter initialized failed with non secure connection!");
			logger.info("Check Adpter SEC DEV Mode");
			connectModeSec = true;
			sharedAvroProducer = new KafkaProducer<EDXLDistribution, IndexedRecord>(ProducerProperties.getInstance(connectModeSec));
			try {
				heartbeatProducer = new HeartbeatProducer(sharedAvroProducer, TopicConstants.HEARTBEAT_TOPIC);	
				heartbeatProducer.sendInitialHeartbeat();
				addAvroReceiver(TopicConstants.ADMIN_HEARTBEAT_TOPIC, new AdminHeartbeatConsumer());
				adpterMode = AdapterMode.SEC_DEV_MODE;
			} catch (Exception e) {
				logger.info("Adapter running in TRIAL Mode, wait for AdminTool heartbeat for futur initalization!");
				addAvroReceiver(TopicConstants.ADMIN_HEARTBEAT_TOPIC, new AdminHeartbeatConsumer());
				adpterMode = AdapterMode.TRIAL_MODE;
			}
		}
		if (adpterMode != AdapterMode.TRIAL_MODE) {
			initCoreTopics();
			adapterInitDone = true;	
		} 
		logger.info("initializeProducers -->");
	}
	
	private void initCoreTopics() {
		logger.info("--> initCoreTopics");
		try {
			if (adpterMode == AdapterMode.TRIAL_MODE) {
				heartbeatProducer = new HeartbeatProducer(sharedAvroProducer, TopicConstants.HEARTBEAT_TOPIC);
				heartbeatProducer.sendInitialHeartbeat();
			}
			this.startHeartbeats();
			logProducer = new LogProducer(sharedAvroProducer);
			logger.setLogProducer(logProducer);
			
			// create the consumers
			addAvroReceiver(TopicConstants.TOPIC_INVITE_TOPIC, new TopicInviteConsumer());
			addAvroReceiver(TopicConstants.TIMING_TOPIC, new TimeConsumer());
			
		} catch (Exception e) {
			logger.error("Error initializing the CoreTopic Producers/Consumers!", e);
		}
		logger.info("initCoreTopics -->");
	}

	/**
	 * Start sending period Heartbeat messages
	 */
	private void startHeartbeats() {
		ClientProperties props = ClientProperties.getInstance();
		int heartbeatInterval = Integer.parseInt(props.getProperty(ClientProperties.HEARTBEAT_INTERVAL));
		heartbeatProducer.startHeartbeats(heartbeatInterval);
	}

	private void addAvroReceiver(String topic, GenericAvroReceiver receiver) {
		GenericCallbackConsumer consumer = new GenericCallbackConsumer(
				new KafkaConsumer<IndexedRecord, IndexedRecord>(ConsumerProperties.getInstance(connectModeSec)), topic);
		Thread t = new Thread(consumer); // TODO: maintain this and clean up thread
		t.start();
		logger.info("New Generic Callback Consumer created for topic: " + topic);
		consumer.addReceiver(receiver);
	}
	
	public void sendMessage(IndexedRecord message) throws CommunicationException {
		logger.debug("-->sendMessage");
		GenericProducer producer = null;
		if (message.getSchema().getName().equalsIgnoreCase("Alert")) {
			producer = producerMap.get(TopicConstants.STANDARD_TOPIC_CAP);
			if (producer == null && (adpterMode == AdapterMode.DEV_MODE || adpterMode == AdapterMode.SEC_DEV_MODE)) {
				producer = createProducer(TopicConstants.STANDARD_TOPIC_CAP);
			}
			
		} else if (message.getSchema().getName().equalsIgnoreCase("SlRep")) {
			producer = producerMap.get(TopicConstants.STANDARD_TOPIC_MLP);
			if (producer == null && (adpterMode == AdapterMode.DEV_MODE || adpterMode == AdapterMode.SEC_DEV_MODE)) {
				producer = createProducer(TopicConstants.STANDARD_TOPIC_MLP);
			}
		} else if (message.getSchema().getName().equalsIgnoreCase("FeatureCollection")) {
			producer = producerMap.get(TopicConstants.STANDARD_TOPIC_GEOJSON);
			if (producer == null && (adpterMode == AdapterMode.DEV_MODE || adpterMode == AdapterMode.SEC_DEV_MODE)) {
				producer = createProducer(TopicConstants.STANDARD_TOPIC_GEOJSON);
			}
		} else if (message.getSchema().getName().equalsIgnoreCase("TSO_2_0")) {
			producer = producerMap.get(TopicConstants.STANDARD_TOPIC_EMSI);
			if (producer == null && (adpterMode == AdapterMode.DEV_MODE || adpterMode == AdapterMode.SEC_DEV_MODE)) {
				producer = createProducer(TopicConstants.STANDARD_TOPIC_EMSI);
			}
		}
		
		if (producer != null) {
			producer.send(message);
		} else {
			throw new CommunicationException("Thers is no producer available!");
		}
		logger.debug("sendMessage-->");
	}
	
	public void sendMessage(IndexedRecord message, String topicName) throws CommunicationException {
		GenericProducer producer = producerMap.get(topicName);
		if (producer != null) {
			producer.send(message);
		}
	}
	
	public void addCallback(IAdaptorCallback callback, String topicName) {
		logger.info("--> addCallback: " + topicName);
		this.callbackMap.put(topicName, callback);
		
		if (adpterMode == AdapterMode.DEV_MODE || adpterMode == AdapterMode.SEC_DEV_MODE) {
			AdapterCallbackConsumer callbackConsumer = new AdapterCallbackConsumer(callback);
			addAvroReceiver(topicName, callbackConsumer);
			consumerMap.put(topicName, callbackConsumer);
		} else if (adapterInitDone) {
			if (topicName.equalsIgnoreCase(TopicConstants.ADMIN_HEARTBEAT_TOPIC) || 
				topicName.equalsIgnoreCase(TopicConstants.HEARTBEAT_TOPIC) ||
				topicName.equalsIgnoreCase(TopicConstants.LOGGING_TOPIC) ||
				topicName.equalsIgnoreCase(TopicConstants.TIMING_TOPIC) ||
				topicName.equalsIgnoreCase(TopicConstants.TOPIC_INVITE_TOPIC)) {
					AdapterCallbackConsumer callbackConsumer = new AdapterCallbackConsumer(callback);
					addAvroReceiver(topicName, callbackConsumer);
					consumerMap.put(topicName, callbackConsumer);
			} else {
				// check if we have already an invite for this, is yes, create the consumer and bind the callback to that
				if (this.topicInvitesConsumers.get(topicName) != null) {
					logger.info("Invite for that topic as subscriber was received, add consumer!");
					AdapterCallbackConsumer callbackConsumer = new AdapterCallbackConsumer(callback);
					addAvroReceiver(topicName, callbackConsumer);
					consumerMap.put(topicName, callbackConsumer);
					logger.debug("Consumer Created, added to consumerMap.");
				}
			}
		}
		
		logger.info("addCallback-->");
	}
	
	public Timing getTimeInfo() {
		return this.timing;
	}
	
	public void addLogEntry(Log logEntry) {
		logger.info("--> addLogEntry");
		try {
			logProducer.send(logEntry);
		} catch (Exception cEx) {
			logger.error("Cannot send the log to the log topic!", cEx);
		}
		logger.info("addLogEntry -->");
	}
	
	public void addLogCallback(IAdaptorCallback callback) {
		AdapterCallbackConsumer callbackConsumer = new AdapterCallbackConsumer(callback);
		addAvroReceiver(TopicConstants.LOGGING_TOPIC, callbackConsumer);
		consumerMap.put(TopicConstants.LOGGING_TOPIC, callbackConsumer);
	}

	public GenericProducer createProducer(String topic) {
		GenericProducer producer = new GenericProducer(sharedAvroProducer, topic);
		producerMap.put(topic, producer);
		return producer;
	}
	
	/*
	 * GETTER/SETTERS
	 */
	public void setCurrentTiming(Timing timing) {
		this.timing = timing;
	}
	
	public LogProducer getLogProducer() {
		return this.logProducer;
	}
	
	/*
	 * methods for core topics
	 */
	public void topicInviteReceived(TopicInvite inviteMsg) {
		// check if the client ID is publisher and/or subscriber
		String topicName = inviteMsg.getTopicName().toString();
		if (inviteMsg.getPublishAllowed()) {
			this.topicInvitesPublisher.put(topicName, inviteMsg);
			createProducer(topicName);
		}
		if (inviteMsg.getSubscribeAllowed()) {
			this.topicInvitesConsumers.put(topicName, inviteMsg);
			IAdaptorCallback callback = this.callbackMap.get(topicName);
			if (callback != null && consumerMap.get(topicName) != null) {
				AdapterCallbackConsumer callbackConsumer = new AdapterCallbackConsumer(callback);
				addAvroReceiver(topicName, callbackConsumer);
				consumerMap.put(topicName, callbackConsumer);
			}
		}
	}

}
