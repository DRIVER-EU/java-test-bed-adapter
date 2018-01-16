package eu.driver.adapter.core.consumer;

import java.util.Collections;

import org.apache.avro.generic.IndexedRecord;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.errors.SerializationException;
import org.slf4j.Logger;

import eu.driver.adapter.logger.CISLogger;
import eu.driver.adapter.properties.ClientProperties;
import eu.driver.adapter.properties.ConsumerProperties;
import eu.driver.adapter.properties.ProducerProperties;

public abstract class AbstractConsumer<Key extends IndexedRecord, Message extends IndexedRecord> implements Runnable {

	private final String topic;
	protected final Consumer<Key, Message> consumer;
	protected static final Logger logger = CISLogger.logger(AbstractConsumer.class);

	public AbstractConsumer(String targetTopic) {
		ConsumerProperties props = ConsumerProperties.getInstance();
		consumer = new KafkaConsumer<>(props);
		topic = targetTopic;
	}

	public String getTopic() {
		return topic;
	}

	public String getClientId() {
		return ClientProperties.getInstance().getProperty(ClientProperties.CLIENT_ID);
	}
}
