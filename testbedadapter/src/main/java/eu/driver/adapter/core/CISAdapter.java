package eu.driver.adapter.core;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;

import eu.driver.adapter.logger.CISLogger;
import eu.driver.adapter.properties.ClientProperties;

public class CISAdapter {

	private HeartbeatProducer heartbeatProducer;
	private ConfigurationProducer configurationProducer;
	
	private Map<String, GenericRecordProducer> producerMap;
	
	private Logger logger = CISLogger.logger(CISAdapter.class);

	public CISAdapter() {
		producerMap = new HashMap<>();
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
	
	public GenericRecordProducer getProducer(String topic) {
		GenericRecordProducer producer = producerMap.get(topic);
		if(producer == null) {
			producer = createProducer(topic);
			producerMap.put(topic, producer);
			logger.info("New producer created for topic: " + topic);
		}
		return producer;
	}
	
	private GenericRecordProducer createProducer(String topic) {
		GenericRecordProducer producer = new GenericRecordProducer(topic);
		ClientProperties.getInstance().addConsumedTopic(topic);
		configurationProducer.sendConfiguration();
		return producer;
	}


}
