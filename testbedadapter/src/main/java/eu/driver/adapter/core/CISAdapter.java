package eu.driver.adapter.core;

import java.util.HashMap;
import java.util.Map;

import org.apache.avro.generic.GenericRecord;
import org.apache.avro.generic.IndexedRecord;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.slf4j.Logger;

import eu.driver.adapter.core.consumer.GenericCallbackConsumer;
import eu.driver.adapter.core.consumer.GenericPollableConsumer;
import eu.driver.adapter.core.producer.ConfigurationProducer;
import eu.driver.adapter.core.producer.GenericProducer;
import eu.driver.adapter.core.producer.HeartbeatProducer;
import eu.driver.adapter.logger.CISLogger;
import eu.driver.adapter.properties.ClientProperties;
import eu.driver.adapter.properties.ConsumerProperties;
import eu.driver.adapter.properties.ProducerProperties;
import eu.driver.api.IAvroReceiver;

public class CISAdapter {

	/**
	 * Kafka Producer shared by all specific Producers for sending Avro messages to the CIS.
	 */
	private Producer<IndexedRecord, IndexedRecord> sharedAvroProducer;
	
	private HeartbeatProducer heartbeatProducer;
	private ConfigurationProducer configurationProducer;
	
	private Map<String, GenericProducer> producerMap;
	private Map<String, GenericPollableConsumer> pollableConsumerMap;
	private Map<String, GenericCallbackConsumer> callbackConsumerMap;
	
	private Logger logger = CISLogger.logger(CISAdapter.class);

	public CISAdapter() {
		producerMap = new HashMap<>();
		pollableConsumerMap = new HashMap<>();
		callbackConsumerMap = new HashMap<>();
		initializeProducers();
		startHeartbeats();
		configurationProducer.sendConfiguration(); // send initial configuration
		logger.info("CISAdapter initialized");
	}
	
	/**
	 * Initializes the core producers used by the CIS Adapter
	 */
	private void initializeProducers() {
		// actual Kafka producer used by all specific producers 
		sharedAvroProducer = new KafkaProducer<IndexedRecord, IndexedRecord>(ProducerProperties.getInstance());
		// producer that generates periodic heartbeats
		heartbeatProducer = new HeartbeatProducer(sharedAvroProducer);
		// producer that is capable of generating and sending configuration messages
		configurationProducer = new ConfigurationProducer(sharedAvroProducer);
		// TODO: initialize producers for configured topics to consume/produce 
	}
	
	private KafkaConsumer<GenericRecord, GenericRecord> createKafkaConsumer() {
		return new KafkaConsumer<GenericRecord, GenericRecord>(ConsumerProperties.getInstance());
	}

	/**
	 * Start sending period Heartbeat messages
	 */
	private void startHeartbeats() {
		ClientProperties props = ClientProperties.getInstance();
		int heartbeatInterval = Integer.parseInt(props.getProperty(ClientProperties.HEARTBEAT_INTERVAL));
		heartbeatProducer.startHeartbeats(heartbeatInterval);
	}
	
	public GenericPollableConsumer getPollableConsumer(String topic) {
		// TODO: add removing consumer
		GenericPollableConsumer consumer = pollableConsumerMap.get(topic);
		if(consumer == null) {
			consumer = createPollableConsumer(topic);
			pollableConsumerMap.put(topic, consumer);
			logger.info("New Generic Pollable Consumer created for topic: " + topic);
		}
		return consumer;
	}
	
	private GenericPollableConsumer createPollableConsumer(String topic) {
		GenericPollableConsumer consumer = new GenericPollableConsumer(createKafkaConsumer(), topic);
		ClientProperties.getInstance().addConsumedTopic(topic);
		configurationProducer.sendConfiguration();
		Thread t = new Thread(consumer); // TODO: maintain this and clean up thread
		t.start();
		return consumer;
	}
	
	public void addAvroReceiver(String topic, IAvroReceiver<GenericRecord> receiver) {
		// TODO: add removing receiver
		GenericCallbackConsumer consumer = callbackConsumerMap.get(topic);
		if(consumer == null) {
			consumer = createCallbackConsumer(topic);
			callbackConsumerMap.put(topic, consumer);
			logger.info("New Generic Callback Consumer created for topic: " + topic);
		}
		consumer.addReceiver(receiver);
	}
	
	private GenericCallbackConsumer createCallbackConsumer(String topic) {
		GenericCallbackConsumer consumer = new GenericCallbackConsumer(createKafkaConsumer(), topic);
		ClientProperties.getInstance().addConsumedTopic(topic);
		configurationProducer.sendConfiguration();
		Thread t = new Thread(consumer); // TODO: maintain this and clean up thread
		t.start();
		return consumer;
	}
	
	public GenericProducer getProducer(String topic) {
		// TODO: add removing producer
		GenericProducer producer = producerMap.get(topic);
		if(producer == null) {
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
