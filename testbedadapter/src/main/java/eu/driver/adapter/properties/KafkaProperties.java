package eu.driver.adapter.properties;

import java.util.Properties;

import org.apache.kafka.clients.CommonClientConfigs;

/**
 * Properties object that contains Kafka properties used by both Consumers as
 * well as Producers. Sets default values for the local test-bed upon creation.
 * 
 * @author hameetepa
 *
 */
public abstract class KafkaProperties extends Properties {

	private static final long serialVersionUID = 7448007850457739782L;
	
	public static final String BOOTSTRAP_SERVERS = CommonClientConfigs.BOOTSTRAP_SERVERS_CONFIG;
	public static final String SCHEMA_REGISTRY_URL = "schema.registry.url";

	protected KafkaProperties() {
		super();
		setDefaults();
	}

	private void setDefaults() {
		setProperty(SCHEMA_REGISTRY_URL, "http://localhost:3502");
		setProperty(BOOTSTRAP_SERVERS, "localhost:3501");
	}

}
