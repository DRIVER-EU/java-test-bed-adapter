package eu.driver.adapter.core;

import java.util.HashMap;
import java.util.Map;

import org.apache.avro.generic.IndexedRecord;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.slf4j.Logger;

import eu.driver.adapter.core.consumer.GenericCallbackConsumer;
import eu.driver.adapter.core.consumer.SpecificCallbackConsumer;
import eu.driver.adapter.core.producer.ConfigurationProducer;
import eu.driver.adapter.core.producer.GenericProducer;
import eu.driver.adapter.core.producer.HeartbeatProducer;
import eu.driver.adapter.logger.CISLogger;
import eu.driver.adapter.properties.ClientProperties;
import eu.driver.adapter.properties.ConsumerProperties;
import eu.driver.adapter.properties.ProducerProperties;
import eu.driver.api.GenericAvroReceiver;
import eu.driver.api.IAvroReceiver;
import eu.driver.model.edxl.EDXLDistribution;

public class CISAdapter {

	/**
	 * Kafka Producer shared by all specific Producers for sending Avro messages to
	 * the CIS.
	 */
	private Producer<EDXLDistribution, IndexedRecord> sharedAvroProducer;

	private HeartbeatProducer heartbeatProducer;
	private ConfigurationProducer configurationProducer;

	private Map<String, GenericProducer> producerMap;

	private Logger logger = CISLogger.logger(CISAdapter.class);

	public CISAdapter() {
		producerMap = new HashMap<>();
		initializeProducers();
		startHeartbeats();
		configurationProducer.sendConfiguration(); // send initial configuration
		logger.info("CISAdapter initialized");
	}

	/**
	 * Initializes the core producers used by the CIS Adapter
	 */
	private void initializeProducers() {
		// actual Kafka producer used by all generic producers s
		sharedAvroProducer = new KafkaProducer<EDXLDistribution, IndexedRecord>(ProducerProperties.getInstance());
		// producer that generates periodic heartbeats
		heartbeatProducer = new HeartbeatProducer(sharedAvroProducer);
		// producer that is capable of generating and sending configuration messages
		configurationProducer = new ConfigurationProducer(sharedAvroProducer);
		// TODO: initialize producers for configured topics to consume/produce
	}

	/**
	 * Start sending period Heartbeat messages
	 */
	private void startHeartbeats() {
		ClientProperties props = ClientProperties.getInstance();
		int heartbeatInterval = Integer.parseInt(props.getProperty(ClientProperties.HEARTBEAT_INTERVAL));
		heartbeatProducer.startHeartbeats(heartbeatInterval);
	}

	public void addAvroReceiver(String topic, GenericAvroReceiver receiver) {
		GenericCallbackConsumer consumer = new GenericCallbackConsumer(
				new KafkaConsumer<IndexedRecord, IndexedRecord>(ConsumerProperties.getInstance()), topic);
		ClientProperties.getInstance().addConsumedTopic(topic);
		configurationProducer.sendConfiguration();
		Thread t = new Thread(consumer); // TODO: maintain this and clean up thread
		t.start();
		logger.info("New Generic Callback Consumer created for topic: " + topic);
		consumer.addReceiver(receiver);
	}

	public <Value extends IndexedRecord> void addSpecificReceiver(String topic, IAvroReceiver<Value> receiver) {
		SpecificCallbackConsumer<IndexedRecord, Value> consumer = new SpecificCallbackConsumer<IndexedRecord, Value>(
				new KafkaConsumer<IndexedRecord, Value>(ConsumerProperties.getInstance()), topic);
		ClientProperties.getInstance().addConsumedTopic(topic);
		configurationProducer.sendConfiguration();
		Thread t = new Thread(consumer); // TODO: maintain this and clean up thread
		t.start();
		logger.info("New Specific Callback Consumer created for topic: " + topic);
		consumer.addReceiver(receiver);
	}

	public GenericProducer getProducer(String topic) {
		// TODO: add removing producer
		GenericProducer producer = producerMap.get(topic);
		if (producer == null) {
			producer = createProducer(topic);
			producerMap.put(topic, producer);
			logger.info("New Generic Producer created for topic: " + topic);
		}
		return producer;
	}

	private GenericProducer createProducer(String topic) {
		GenericProducer producer = new GenericProducer(sharedAvroProducer, topic);
		ClientProperties.getInstance().addProducedTopic(topic);
		configurationProducer.sendConfiguration();
		return producer;
	}

}
