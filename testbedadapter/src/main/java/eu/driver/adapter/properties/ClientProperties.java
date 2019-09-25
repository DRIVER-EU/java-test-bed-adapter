package eu.driver.adapter.properties;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import eu.driver.adapter.core.CISAdapter;

/**
 * Properties object that contains test-bed specific properties that are not
 * Kafka consumer or producer properties. Sets default values upon creation.
 * 
 * @author hameetepa
 *
 */
public class ClientProperties extends Properties {

	private static final long serialVersionUID = -5082046368427979491L;

	/**
	 * Heartbeat interval in milliseconds
	 */
	public static final String HEARTBEAT_INTERVAL = "heartbeat.interval";

	/**
	 * Comma separated list of Consumed Topics
	 */
	public static final String CONSUMED_TOPICS = "consumed.topics";
	
	/**
	 * Comma separated list of Produced Topics
	 */
	public static final String PRODUCED_TOPICS = "produced.topics";
	
	public static final String HEARTBEAT_TOPIC = "heartbeat.topic";
	
	public static final String CONFIGURATION_TOPIC = "configuration.topic";
	
	public static final String LOG_TOPIC = "log.topic";
	
	public static final String CLIENT_ID = "client.id";
	
	private static final Logger logger = LoggerFactory.getLogger(ClientProperties.class);

	private static ClientProperties instance;

	/**
	 * @return The Singleton ClientProperties object containing all client
	 *         configuration.
	 */
	public static ClientProperties getInstance() {
		if (instance == null) {
			instance = new ClientProperties();
		}
		return instance;
	}

	private ClientProperties() {
		super();
		setDefaults();
		loadConfigFile();
	}
	
	private void loadConfigFile() {
		try {
			FileInputStream fis = null;
			if (CISAdapter.globalConfigPath != null) {
				fis = new FileInputStream(CISAdapter.globalConfigPath + "/client.properties");
			} else {
				fis = new FileInputStream("config/client.properties");	
			}
			
			load(fis);
			fis.close();
		} catch (IOException e) {
			logger.error("Could not read Client Properties file client.properties in config folder", e);
		}
	}

	private void setDefaults() {
		setProperty(HEARTBEAT_INTERVAL, "" + 5000);
		setProperty(CONSUMED_TOPICS, "");
		setProperty(PRODUCED_TOPICS, "heartbeat,configuration"); // TODO: add own logging topic
		setProperty(CLIENT_ID, "default_java_adapter");
		setProperty(HEARTBEAT_TOPIC, "connect-status-heartbeat");
		setProperty(CONFIGURATION_TOPIC, "connect-status-configuration");
		setProperty(LOG_TOPIC, "connect-status-log");
	}
	
	/**
	 * Returns a List of topics that this Client is producing for.
	 * 
	 * @return
	 */
	public List<CharSequence> getProducedTopics() {
		String topics = getProperty(PRODUCED_TOPICS);
		return Arrays.asList(topics.split(","));
	}

	/**
	 * Adds a produced topic to the Properties definition.
	 * 
	 * @param topicToAdd
	 */
	public void addProducedTopic(String topicToAdd) {
		String topics = getProperty(PRODUCED_TOPICS);
		topics += "," + topicToAdd;
		put(PRODUCED_TOPICS, topics);
	}

	/**
	 * Removes a topic from the produced topics property.
	 * 
	 * @param topicToRemove
	 */
	public void removeProducedTopic(String topicToRemove) {
		String newTopics = "";
		for (CharSequence existingTopic : getProducedTopics()) {
			if (!existingTopic.equals(topicToRemove)) {
				newTopics += "," + existingTopic;
			}
		}
		put(PRODUCED_TOPICS, newTopics);
	}

	/**
	 * Returns a List of topics that this Client is consuming.
	 * 
	 * @return
	 */
	public List<CharSequence> getConsumedTopics() {
		String topics = getProperty(CONSUMED_TOPICS);
		return Arrays.asList(topics.split(","));
	}

	/**
	 * Adds a consumed topic to the Properties definition.
	 * 
	 * @param topicToAdd
	 */
	public void addConsumedTopic(String topicToAdd) {
		String topics = getProperty(CONSUMED_TOPICS);
		topics += "," + topicToAdd;
		put(CONSUMED_TOPICS, topics);
	}

	/**
	 * Removes a topic from the consumed topics property.
	 * 
	 * @param topicToRemove
	 */
	public void removeConsumedTopic(String topicToRemove) {
		String newTopics = "";
		for (CharSequence existingTopic : getConsumedTopics()) {
			if (!existingTopic.equals(topicToRemove)) {
				newTopics += "," + existingTopic;
			}
		}
		put(CONSUMED_TOPICS, newTopics);
	}

}
