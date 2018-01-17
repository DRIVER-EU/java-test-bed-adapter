package eu.driver.adapter.properties;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Properties object that contains extends the standard Kafka properties with
 * properties used specifically by the Producers. Sets default values for the
 * local test-bed upon creation.
 * 
 * @author hameetepa
 *
 */
public class ProducerProperties extends KafkaProperties {

	private static final long serialVersionUID = -7988826780301880736L;

	// Configuration Keys for Kafka Producers
	public static final String KEY_SERIALIZER = ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG;
	public static final String VALUE_SERIALIZER = ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG;
	public static final String COMPRESSION_TYPE = ProducerConfig.COMPRESSION_TYPE_CONFIG;
	
	private static final Logger logger = LoggerFactory.getLogger(ProducerProperties.class);

	private static ProducerProperties instance;

	/**
	 * 
	 * @return The Singleton Producer Properties object containing all Kafka produer
	 *         related configuration.
	 */
	public static ProducerProperties getInstance() {
		if (instance == null) {
			instance = new ProducerProperties();
		}
		return instance;
	}

	private ProducerProperties() {
		super();
		setDefaults();
		loadConfigFile();
	}
	
	private void loadConfigFile() {
		try {
			FileInputStream fis = new FileInputStream("config/producer.properties");
			load(fis);
			fis.close();
		} catch (IOException e) {
			logger.error("Could not read Client Properties file client.properties in config folder");
		}
	}

	private void setDefaults() {
		setProperty(KEY_SERIALIZER, "io.confluent.kafka.serializers.KafkaAvroSerializer");
		setProperty(VALUE_SERIALIZER, "io.confluent.kafka.serializers.KafkaAvroSerializer");
		setProperty(COMPRESSION_TYPE, "none");
	}

}
