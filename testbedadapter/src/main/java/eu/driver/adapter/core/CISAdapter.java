package eu.driver.adapter.core;

import java.util.HashMap;
import java.util.Map;

import org.apache.avro.generic.GenericRecord;
import org.slf4j.Logger;

import eu.driver.adapter.core.consumer.GenericCallbackConsumer;
import eu.driver.adapter.core.consumer.GenericPollableConsumer;
import eu.driver.adapter.core.producer.ConfigurationProducer;
import eu.driver.adapter.core.producer.GenericProducer;
import eu.driver.adapter.core.producer.HeartbeatProducer;
import eu.driver.adapter.logger.CISLogger;
import eu.driver.adapter.properties.ClientProperties;
import eu.driver.api.IAvroReceiver;

public class CISAdapter {

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
		heartbeatProducer = new HeartbeatProducer();
		configurationProducer = new ConfigurationProducer();
		startHeartbeats();
		configurationProducer.sendConfiguration();
		logger.info("CISAdapter initialized");
	}

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
		GenericPollableConsumer consumer = new GenericPollableConsumer(topic);
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
		GenericCallbackConsumer consumer = new GenericCallbackConsumer(topic);
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
		GenericProducer producer = new GenericProducer(topic);
		ClientProperties.getInstance().addProducedTopic(topic);
		configurationProducer.sendConfiguration();
		return producer;
	}


}
