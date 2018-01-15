package eu.driver.adapter.properties;

import java.util.Arrays;
import java.util.List;
import java.util.Properties;

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
	}

	private void setDefaults() {
		setProperty(HEARTBEAT_INTERVAL, "" + 5000);
		setProperty(CONSUMED_TOPICS, "");
		setProperty(PRODUCED_TOPICS, "heartbeat,configuration"); // TODO: add own logging topic
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
