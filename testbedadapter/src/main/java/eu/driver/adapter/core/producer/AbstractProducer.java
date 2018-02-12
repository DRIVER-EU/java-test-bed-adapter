package eu.driver.adapter.core.producer;

import java.util.concurrent.atomic.AtomicInteger;

import org.apache.avro.generic.IndexedRecord;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.errors.SerializationException;
import org.slf4j.Logger;

import eu.driver.adapter.core.consumer.AbstractConsumer;
import eu.driver.adapter.logger.CISLogger;
import eu.driver.adapter.properties.ClientProperties;
import eu.driver.model.edxl.DistributionKind;
import eu.driver.model.edxl.DistributionStatus;
import eu.driver.model.edxl.EDXLDistribution;
import io.confluent.kafka.schemaregistry.client.rest.exceptions.RestClientException;

public abstract class AbstractProducer<Key extends IndexedRecord, Value extends IndexedRecord> {
	
	private final String topic;
	private final Producer<Key, Value> producer;
	private static final AtomicInteger counter = new AtomicInteger();
	protected static final Logger logger = CISLogger.logger(AbstractConsumer.class);

	public AbstractProducer(Producer<Key, Value> producer, String topic) {
		this.producer = producer;
		this.topic = topic;
	}
	
	public static final int getMessageNumber() {
		return counter.incrementAndGet();
	}
	
	public String getTopic() {
		return topic;
	}
	
	public String getClientId() {
		return ClientProperties.getInstance().getProperty(ClientProperties.CLIENT_ID);
	}
	
	public void send(Value message) {
		try {
			Key key = createKey();
			ProducerRecord<Key, Value> record = new ProducerRecord<>(topic, key, message);
			producer.send(record);
		} catch (SerializationException ex) {
			// TODO: proper logging
			ex.printStackTrace();
			System.err.print("Error while serializing message: " + message);
		}
	}
	
	
	public abstract Key createKey();
	
	/**
	 * This function must be implemented to override values in the EDXL-DE based key
	 * for each message. For example the expiration time, the distribution kind and the distribution status
	 * can be specified. This is called automatically by the createKey method after setting the default values.
	 * @param standardKey
	 * @return
	 */
	abstract protected EDXLDistribution setEDXLDEValues(EDXLDistribution standardKey);

}
