package eu.driver.adapter.core.producer;

import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.avro.generic.IndexedRecord;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.errors.TimeoutException;
import org.slf4j.Logger;

import eu.driver.adapter.core.consumer.AbstractConsumer;
import eu.driver.adapter.logger.CISLogger;
import eu.driver.adapter.properties.ClientProperties;
import eu.driver.model.edxl.EDXLDistribution;

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
		if (producer == null) {
			System.err.print("Producer is null, cannot send message: " + message);
			return;
		}
		try {
			Key key = createKey();
			ProducerRecord<Key, Value> record = new ProducerRecord<>(topic, key, message);
			Future<RecordMetadata> result = producer.send(record);
			RecordMetadata resRecord = result.get();
		} catch (SerializationException ex) {
			System.err.print("Error while serializing message: " + message);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void sendCheckConnection(Value message) throws Throwable {
		if (producer == null) {
			System.err.print("Producer is null, cannot send message: " + message);
			return;
		}
		try {
			Key key = createKey();
			ProducerRecord<Key, Value> record = new ProducerRecord<>(topic, key, message);
			Future<RecordMetadata> result = producer.send(record);
			RecordMetadata resRecord = result.get();
		} catch (SerializationException ex) {
			System.err.print("Error while serializing message: " + message);
		} catch (Exception e) {
			e.printStackTrace();
			if (e.getCause() instanceof TimeoutException) {
				throw e.getCause();
			}
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
