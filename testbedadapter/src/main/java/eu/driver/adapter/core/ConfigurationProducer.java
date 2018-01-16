package eu.driver.adapter.core;

import java.util.List;

import org.apache.kafka.clients.CommonClientConfigs;
import org.slf4j.Logger;

import eu.driver.adapter.logger.CISLogger;
import eu.driver.adapter.properties.ClientProperties;
import eu.driver.adapter.properties.ProducerProperties;
import eu.driver.model.core.Configuration;
import eu.driver.model.core.ConfigurationKey;

/**
 * Producer for the CIS Adapter Core Configuration messages. The configuration
 * topic and the Configuration message key and value type are fixed to comply
 * with the CIS requirements.
 * 
 * @author hameetepa
 */
public class ConfigurationProducer extends AbstractProducer<ConfigurationKey, Configuration> {

	private static final String CONFIGURATION_TOPIC = "connect-status-configuration";
	
	private static final Logger logger = CISLogger.logger(ConfigurationProducer.class);

	public ConfigurationProducer() {
		super(CONFIGURATION_TOPIC);
	}
	
	protected ConfigurationKey createKey() {
		ConfigurationKey key = new ConfigurationKey();
		key.setId(getClientId());
		return key;
	}
	
	/**
	 * Fills a Configuration message based on the current CIS Adapter configuration and sends
	 * it to the 
	 */
	public void sendConfiguration() {
		ClientProperties clientProps = ClientProperties.getInstance();
		ProducerProperties producerProps = ProducerProperties.getInstance();
		
		Configuration configMessage = new Configuration();

		configMessage.setClientId(clientProps.getProperty(ClientProperties.CLIENT_ID));

		String schemaRegistryUrl = producerProps.getProperty(ProducerProperties.SCHEMA_REGISTRY_URL);
		configMessage.setSchemaRegistry(schemaRegistryUrl);

		String kafkaBrokers = producerProps.getProperty(CommonClientConfigs.BOOTSTRAP_SERVERS_CONFIG);
		configMessage.setKafkaHost(kafkaBrokers);

		int heartbeatInterval = Integer.parseInt(clientProps.getProperty(ClientProperties.HEARTBEAT_INTERVAL));
		configMessage.setHeartbeatInterval(heartbeatInterval);
		
		List<CharSequence> consumedTopics = clientProps.getConsumedTopics();
		configMessage.setConsume(consumedTopics);
		
		List<CharSequence> producedTopics = clientProps.getProducedTopics();
		configMessage.setProduce(producedTopics);

		// TODO: fill with actual log settings
//		LogSettings logSettings = new LogSettings();
//		configMessage.setLogging(logSettings);
		
		send(configMessage);
		logger.debug("Sent configuration message: " + configMessage);
	}
}
