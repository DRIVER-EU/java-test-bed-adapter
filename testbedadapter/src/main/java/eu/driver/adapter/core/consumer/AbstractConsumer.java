package eu.driver.adapter.core.consumer;

import org.apache.avro.generic.IndexedRecord;
import org.apache.kafka.clients.consumer.Consumer;
import org.slf4j.Logger;

import eu.driver.adapter.logger.CISLogger;
import eu.driver.adapter.properties.ClientProperties;

public abstract class AbstractConsumer<Key extends IndexedRecord, Message extends IndexedRecord> implements Runnable {

	private final String topic;
	protected final Consumer<Key, Message> consumer;
	protected static final Logger logger = CISLogger.logger(AbstractConsumer.class);

	public AbstractConsumer(Consumer<Key, Message> consumer, String topic) {
		this.consumer = consumer;
		this.topic = topic;
	}

	public String getTopic() {
		return topic;
	}

	public String getClientId() {
		return ClientProperties.getInstance().getProperty(ClientProperties.CLIENT_ID);
	}
}
