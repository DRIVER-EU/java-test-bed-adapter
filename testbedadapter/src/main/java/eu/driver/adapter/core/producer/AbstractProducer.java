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

public abstract class AbstractProducer {
	
	private final String topic;
	private final Producer<IndexedRecord, IndexedRecord> producer;
	private static final AtomicInteger counter = new AtomicInteger();
	protected static final Logger logger = CISLogger.logger(AbstractConsumer.class);

	public AbstractProducer(Producer<IndexedRecord, IndexedRecord> producer, String topic) {
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
	
	public EDXLDistribution createKey() {
		EDXLDistribution key = new EDXLDistribution();
		key.setDateTimeSent(System.currentTimeMillis());
		key.setDateTimeExpires(Long.MAX_VALUE);
		key.setDistributionID(getClientId() + "-" + getMessageNumber());
		key.setSenderID(getClientId());
		key.setDistributionKind(DistributionKind.Unknown);
		key.setDistributionStatus(DistributionStatus.Unknown);
		key = setEDXLDEValues(key);
		return key;
	}
	
	/**
	 * This function must be implemented to override values in the EDXL-DE based key
	 * for each message. For example the expiration time, the distribution kind and the distribution status
	 * can be specified. This is called automatically by the createKey method after setting the default values.
	 * @param standardKey
	 * @return
	 */
	abstract protected EDXLDistribution setEDXLDEValues(EDXLDistribution standardKey);

}
