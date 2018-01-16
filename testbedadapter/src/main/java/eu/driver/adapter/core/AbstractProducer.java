package eu.driver.adapter.core;

import org.apache.avro.generic.IndexedRecord;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.errors.SerializationException;

import eu.driver.adapter.properties.ClientProperties;
import eu.driver.adapter.properties.ProducerProperties;

public abstract class AbstractProducer<Key extends IndexedRecord, Message extends IndexedRecord> {
	
	private final String topic;
	private final Producer<Key, Message> producer;

	public AbstractProducer(String targetTopic) {
		ProducerProperties props = ProducerProperties.getInstance();
		producer = new KafkaProducer<>(props);
		topic = targetTopic;
	}
	
	public String getTopic() {
		return topic;
	}
	
	public String getClientId() {
		return ClientProperties.getInstance().getProperty(ClientProperties.CLIENT_ID);
	}
	
	public void send(Message message) {
		try {
			Key key = createKey();
			ProducerRecord<Key, Message> record = new ProducerRecord<>(topic, key, message);
			producer.send(record);
		} catch (SerializationException ex) {
			// TODO: proper logging
			ex.printStackTrace();
			System.err.print("Error while serializing message: " + message);
		}
	}
	
	abstract protected Key createKey();

}
