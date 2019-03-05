package eu.driver.adapter.core;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

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
import eu.driver.model.core.Log;
import eu.driver.model.core.State;
import eu.driver.model.core.Timing;
import eu.driver.model.core.TimingControl;
import eu.driver.model.core.TopicInvite;
import eu.driver.model.edxl.EDXLDistribution;

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
	private GenericProducer largeDataProducer;
	
	/*
	 * The type specific producers
	 */
	private Map<String, GenericProducer> producerMap = new HashMap<String, GenericProducer>();
	/*
	 * The type specific consumers
	 */
	private Map<String, AdapterCallbackConsumer> consumerMap = new HashMap<String, AdapterCallbackConsumer>();
	private List<GenericCallbackConsumer> consumers = new ArrayList<GenericCallbackConsumer>();

	
	/*
	 * The type specific callbacks
	 */
	private Map<String, List<IAdaptorCallback>> callbackMap = new HashMap<String, List<IAdaptorCallback>>();
	
	/*
	 * The invites to topics
	 */
	private Map<String, TopicInvite> topicInvitesPublisher = new HashMap<String, TopicInvite>();
	private Map<String, TopicInvite> topicInvitesConsumers = new HashMap<String, TopicInvite>();

	private CISLogger logger = new CISLogger(CISAdapter.class);
	
	private Timing timing = null;
	private long updatedSimTimeAt = new Date().getTime();
	private long pTrialTimeSpeed = 0;
	private State pState = State.Idle;
	private long pTrialTime = 0;
	
	private AdapterMode adpterMode = AdapterMode.DEV_MODE;
	private Boolean handleTopicInvite = true;
	private Boolean connectModeSec = false;
	private Boolean adapterInitDone = false;

	private CISAdapter(Boolean handleTopicInvite) {
		this.handleTopicInvite = handleTopicInvite;
		producerMap = new HashMap<>();
		try {
			initializeProducers();
		} catch (Exception e) {
			
		}
		this.clientID = ClientProperties.getInstance().getProperty("client.id");
	}
	
	public static synchronized CISAdapter getInstance(Boolean handleTopicInvite) {
		if (CISAdapter.aMe == null) {
			CISAdapter.aMe = new CISAdapter(handleTopicInvite);
		}
		return CISAdapter.aMe;
	}
	
	public static synchronized CISAdapter getInstance() {
		if (CISAdapter.aMe == null) {
			CISAdapter.aMe = new CISAdapter(true);
		}
		return CISAdapter.aMe;
	}
	
	public static synchronized CISAdapter getNewInstance(Boolean handleTopicInvite) {
		if (CISAdapter.aMe != null) {
			CISAdapter.aMe.closeAdapter();
		}
		CISAdapter.aMe = new CISAdapter(handleTopicInvite);
		return CISAdapter.aMe;
	}
	
	public static synchronized CISAdapter getNewInstance() {
		if (CISAdapter.aMe != null) {
			CISAdapter.aMe.closeAdapter();
		}
		CISAdapter.aMe = new CISAdapter(true);
		return CISAdapter.aMe;
	}
	
	public void closeAdapter() {
		// Stop Heartbeat
		heartbeatProducer.stopHeartbeats();
		
		// Stop/Close All Consumers
		for (GenericCallbackConsumer consumer : this.consumers) {
			consumer.stop();
		}
	}
	
	public String getClientID() {
		return this.clientID;
	}
	
	
	/**
	 * Initializes the core producers used by the CIS Adapter
	 */
	private void initializeProducers() {
		logger.info("--> initializeProducers");
		// actual Kafka producer used by all generic producers
		try {
			sharedAvroProducer = new KafkaProducer<EDXLDistribution, IndexedRecord>(ProducerProperties.getInstance(connectModeSec));
		} catch (Exception cEx) {
			logger.info("CISAdapter failed to create a KafkaProducer!");
			cEx.printStackTrace();
		}
		try {
			logger.info("Check Adpter DEV Mode");
			heartbeatProducer = new HeartbeatProducer(sharedAvroProducer, TopicConstants.HEARTBEAT_TOPIC);	
			heartbeatProducer.sendInitialHeartbeat();
			addAvroReceiver(TopicConstants.ADMIN_HEARTBEAT_TOPIC, new AdminHeartbeatConsumer(null));
			adpterMode = AdapterMode.DEV_MODE;
		} catch (Exception cEx) {
			logger.info("CISAdapter initialized failed with non secure connection!");
			logger.info("Check Adpter SEC DEV Mode");
			connectModeSec = true;
			sharedAvroProducer = new KafkaProducer<EDXLDistribution, IndexedRecord>(ProducerProperties.getInstance(connectModeSec));
			try {
				heartbeatProducer = new HeartbeatProducer(sharedAvroProducer, TopicConstants.HEARTBEAT_TOPIC);	
				heartbeatProducer.sendInitialHeartbeat();
				addAvroReceiver(TopicConstants.ADMIN_HEARTBEAT_TOPIC, new AdminHeartbeatConsumer(null));
				adpterMode = AdapterMode.SEC_DEV_MODE;
			} catch (Exception e) {
				logger.info("Adapter running in TRIAL Mode, wait for AdminTool heartbeat for futur initalization!");
				addAvroReceiver(TopicConstants.ADMIN_HEARTBEAT_TOPIC, new AdminHeartbeatConsumer(CISAdapter.aMe));
				adpterMode = AdapterMode.TRIAL_MODE;
			}
		}
		if (adpterMode != AdapterMode.TRIAL_MODE) {
			initCoreTopics();
			adapterInitDone = true;	
		} 
		logger.info("initializeProducers -->");
	}
	
	public void reestablishAdminHeartbeatConsumer() {
		logger.info("Wait for AdminTool to come up and send Heartbeats!");
		
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) { }
		
		addAvroReceiver(TopicConstants.ADMIN_HEARTBEAT_TOPIC, new AdminHeartbeatConsumer(CISAdapter.aMe));
	}
	
	public void initCoreTopics() {
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
			if (handleTopicInvite) {
				addAvroReceiver(TopicConstants.TOPIC_INVITE_TOPIC, new TopicInviteConsumer());
			}
			addAvroReceiver(TopicConstants.TIMING_CONTROL_TOPIC, new TimeConsumer());
			largeDataProducer = new GenericProducer(sharedAvroProducer, TopicConstants.LARGE_DATA_UPDTAE);
			
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
		consumers.add(consumer);
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
		} else if (message.getSchema().getName().equalsIgnoreCase("LargeDataUpdate")) {
			producer = producerMap.get(TopicConstants.LARGE_DATA_UPDTAE);
			if (producer == null && (adpterMode == AdapterMode.DEV_MODE || adpterMode == AdapterMode.SEC_DEV_MODE)) {
				producer = createProducer(TopicConstants.LARGE_DATA_UPDTAE);
			}
		} else if (message.getSchema().getName().equalsIgnoreCase("MapLayerUpdate")) {
			producer = producerMap.get(TopicConstants.MAP_LAYER_UPDTAE);
			if (producer == null && (adpterMode == AdapterMode.DEV_MODE || adpterMode == AdapterMode.SEC_DEV_MODE)) {
				producer = createProducer(TopicConstants.MAP_LAYER_UPDTAE);
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
		List<IAdaptorCallback> callbacks = this.callbackMap.get(topicName);
		if (callbacks == null) {
			callbacks = new ArrayList<IAdaptorCallback>();
		}
		callbacks.add(callback);
		this.callbackMap.put(topicName, callbacks);
		AdapterCallbackConsumer callbackConsumer = consumerMap.get(topicName);
		if (callbackConsumer != null) {
			callbackConsumer.setCallbacks(callbacks);
		} else {
			if (adpterMode == AdapterMode.DEV_MODE || adpterMode == AdapterMode.SEC_DEV_MODE) {
				callbackConsumer = new AdapterCallbackConsumer(topicName, callbacks);
				addAvroReceiver(topicName, callbackConsumer);
				consumerMap.put(topicName, callbackConsumer);
			} else if (adapterInitDone) {
				if (topicName.equalsIgnoreCase(TopicConstants.ADMIN_HEARTBEAT_TOPIC) || 
					topicName.equalsIgnoreCase(TopicConstants.HEARTBEAT_TOPIC) ||
					topicName.equalsIgnoreCase(TopicConstants.LOGGING_TOPIC) ||
					topicName.equalsIgnoreCase(TopicConstants.TIMING_TOPIC) ||
					topicName.equalsIgnoreCase(TopicConstants.TOPIC_INVITE_TOPIC)||
					topicName.equalsIgnoreCase(TopicConstants.LARGE_DATA_UPDTAE)) {
						callbackConsumer = new AdapterCallbackConsumer(topicName, callbacks);
						addAvroReceiver(topicName, callbackConsumer);
						consumerMap.put(topicName, callbackConsumer);
				} else {
					// check if we have already an invite for this, is yes, create the consumer and bind the callback to that
					if (this.topicInvitesConsumers.get(topicName) != null) {
						logger.info("Invite for that topic as subscriber was received, add consumer!");
						callbackConsumer = new AdapterCallbackConsumer(topicName, callbacks);
						addAvroReceiver(topicName, callbackConsumer);
						consumerMap.put(topicName, callbackConsumer);
						logger.debug("Consumer Created, added to consumerMap.");
					}
				}
			}
		}
		
		logger.info("addCallback-->");
	}
	
	public void addLogEntry(Log logEntry) {
		logger.debug("--> addLogEntry");
		try {
			logProducer.send(logEntry);
		} catch (Exception cEx) {
			logger.error("Cannot send the log to the log topic!", cEx);
		}
		logger.debug("addLogEntry -->");
	}
	
	public void addLogCallback(IAdaptorCallback callback) {
		AdapterCallbackConsumer callbackConsumer = new AdapterCallbackConsumer(TopicConstants.LOGGING_TOPIC, callback);
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
		synchronized(this.timing) {
			this.timing = timing;
			long latency = 0;
			this.updatedSimTimeAt = new Date().getTime();
		    this.pTrialTimeSpeed = timing.getTrialTimeSpeed().longValue();
		    if (timing.getState() != null) {
		      this.pState = timing.getState();
		    }
		    this.pTrialTime = timing.getTrialTime() + latency * timing.getTrialTimeSpeed().longValue();
		}
	}
	
	public Timing getTimeInfo() {
		if (this.timing != null) {
			synchronized(this.timing) {
				return this.timing;
			}
		}
		return null;
	}
	
	/**
	 * Get the simulation time as Date.
	 */
	public Date getTrialTime() {
		long now = new Date().getTime();
	    long timePassedSinceLastUpdate = now - this.updatedSimTimeAt;
	    return this.pState == State.Idle
	      ? new Date()
	      : new Date(this.pTrialTime + timePassedSinceLastUpdate * this.pTrialTimeSpeed);
	}
	
	/**
	 * Get elapsed time in msec.
	 */
	public long getTimeElapsed() {
	    long now = new Date().getTime();
	    long timePassedSinceLastUpdate = now - this.updatedSimTimeAt;
	    return this.pTrialTime + timePassedSinceLastUpdate;
	}
	
	public State getState() {
	    return this.pState;
	}
	
	/**
	 * Positive number, indicating how fast the simulation / trial time moves with respect
	 * to the actual time. A value of 0 means a pause, 1 is as fast as real-time.
	 */
	public long getTrialSpeed() {
	    return this.pTrialTimeSpeed;
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
			List<IAdaptorCallback> callbacks = this.callbackMap.get(topicName);
			if (callbacks != null && consumerMap.get(topicName) != null) {
				AdapterCallbackConsumer callbackConsumer = new AdapterCallbackConsumer(topicName, callbacks);
				addAvroReceiver(topicName, callbackConsumer);
				consumerMap.put(topicName, callbackConsumer);
			}
		}
	}
	
	public AdapterMode getAdapterMode() {
		return this.adpterMode;
	}

}
