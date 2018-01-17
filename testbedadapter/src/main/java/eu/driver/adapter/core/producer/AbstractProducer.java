package eu.driver.adapter.core.producer;

import org.apache.avro.generic.IndexedRecord;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.errors.SerializationException;
import org.slf4j.Logger;

import eu.driver.adapter.core.consumer.AbstractConsumer;
import eu.driver.adapter.logger.CISLogger;
import eu.driver.adapter.properties.ClientProperties;

public abstract class AbstractProducer {
	
	private final String topic;
	private final Producer<IndexedRecord, IndexedRecord> producer;
	protected static final Logger logger = CISLogger.logger(AbstractConsumer.class);

	public AbstractProducer(Producer<IndexedRecord, IndexedRecord> producer, String topic) {
		this.producer = producer;
		this.topic = topic;
	}
	
	public String getTopic() {
		return topic;
	}
	
	public String getClientId() {
		return ClientProperties.getInstance().getProperty(ClientProperties.CLIENT_ID);
	}
	
	public void send(IndexedRecord message) {
		try {
			IndexedRecord key = createKey();
			ProducerRecord<IndexedRecord, IndexedRecord> record = new ProducerRecord<>(topic, key, message);
			producer.send(record);
		} catch (SerializationException ex) {
			// TODO: proper logging
			ex.printStackTrace();
			System.err.print("Error while serializing message: " + message);
		}
	}
	
	abstract protected IndexedRecord createKey();

}
