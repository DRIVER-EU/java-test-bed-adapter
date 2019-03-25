package eu.driver.adapter.properties;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import eu.driver.adapter.core.CISAdapter;

/**
 * Properties object that contains extends the standard Kafka properties with
 * properties used specifically by the Consumers. Sets default values for the
 * local test-bed upon creation.
 * 
 * @author hameetepa
 *
 */
public class ConsumerProperties extends KafkaProperties {

	private static final long serialVersionUID = -2740620439918784263L;

	public static final String GROUP_ID = ConsumerConfig.GROUP_ID_CONFIG;
	public static final String KEY_DESERIALIZER = ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG;
	public static final String VALUE_DESERIALIZER = ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG;
	
	private static final Logger logger = LoggerFactory.getLogger(ConsumerProperties.class);

	private static Boolean secured = false;
	private static ConsumerProperties instance;

	/**
	 * 
	 * @return The Singleton Consumer Properties object containing all Kafka
	 *         consumer related configuration.
	 */
	public static ConsumerProperties getInstance(Boolean secured) {
		if (ConsumerProperties.instance == null || ConsumerProperties.secured != secured) {
			instance = new ConsumerProperties(secured);
		}
		
		return instance;
	}

	private ConsumerProperties(Boolean secured) {
		super();
		setDefaults();
		loadConfigFile();
		if(secured) {
			loadSSLConfigFile();	
		}
		// the the GROUP_ID to the Client_ID as this has to be unique
		setProperty(GROUP_ID, ClientProperties.getInstance().getProperty("client.id", "default_java_adapter_group"));
		
		if (System.getenv().get("KAFKA_BROKER_URL") != null) {
			setProperty("bootstrap.servers", System.getenv().get("KAFKA_BROKER_URL"));
		}
		if (System.getenv().get("SCHEMA_REGISTRY_URL") != null) {
			setProperty("schema.registry.url", System.getenv().get("SCHEMA_REGISTRY_URL"));
		}
		
	}
	
	private void loadConfigFile() {
		try {
			FileInputStream fis = null;
			if (CISAdapter.globalConfigPath != null) {
				fis = new FileInputStream(CISAdapter.globalConfigPath + "/consumer.properties");
			} else {
				fis = new FileInputStream("config/consumer.properties");	
			}
			load(fis);
			fis.close();
		} catch (IOException e) {
			logger.error("Could not read Consumer Properties file consumer.properties in config folder");
		}
	}
	
	private void loadSSLConfigFile() {
		try {
			FileInputStream fis = null;
			if (CISAdapter.globalConfigPath != null) {
				fis = new FileInputStream(CISAdapter.globalConfigPath + "/ssl.properties");
			} else {
				fis = new FileInputStream("config/ssl.properties");	
			}
			load(fis);
			fis.close();
		} catch (IOException e) {
			logger.error("Could not read Client Properties file client.properties in config folder");
		}
	}

	private void setDefaults() {
		setProperty(GROUP_ID, "default_java_adapter_group");
		setProperty(KEY_DESERIALIZER, "io.confluent.kafka.serializers.KafkaAvroDeserializer");
		setProperty(VALUE_DESERIALIZER, "io.confluent.kafka.serializers.KafkaAvroDeserializer");
	}

}
