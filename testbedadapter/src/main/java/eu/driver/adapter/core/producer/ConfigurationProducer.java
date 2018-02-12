package eu.driver.adapter.core.producer;

import java.util.ArrayList;
import java.util.List;

import org.apache.avro.generic.IndexedRecord;
import org.apache.kafka.clients.CommonClientConfigs;
import org.apache.kafka.clients.producer.Producer;

import eu.driver.adapter.properties.ClientProperties;
import eu.driver.adapter.properties.ProducerProperties;
import eu.driver.model.core.Configuration;
import eu.driver.model.core.OffsetFetchRequest;
import eu.driver.model.edxl.EDXLDistribution;

/**
 * Producer for the CIS Adapter Core Configuration messages. The configuration
 * topic and the Configuration message key and value type are fixed to comply
 * with the CIS requirements.
 * 
 * @author hameetepa
 */
public class ConfigurationProducer extends AbstractEDXLDEProducer {

	public ConfigurationProducer(Producer<EDXLDistribution, IndexedRecord> producer) {
		super(producer, ClientProperties.getInstance().getProperty(ClientProperties.CONFIGURATION_TOPIC));
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
		List<OffsetFetchRequest> fetchRequests = new ArrayList<>();
		for(CharSequence topic : consumedTopics) {
			OffsetFetchRequest fr = new OffsetFetchRequest();
			fr.setTopic(topic);
			fetchRequests.add(fr);
		}
		configMessage.setConsume(fetchRequests);
		
		List<CharSequence> producedTopics = clientProps.getProducedTopics();
		configMessage.setProduce(producedTopics);

		// TODO: fill with actual log settings
//		LogSettings logSettings = new LogSettings();
//		configMessage.setLogging(logSettings);
		
		send(configMessage);
		logger.debug("Sent configuration message: " + configMessage);
	}

	@Override
	protected EDXLDistribution setEDXLDEValues(EDXLDistribution standardKey) {
		return standardKey;
	}
}
