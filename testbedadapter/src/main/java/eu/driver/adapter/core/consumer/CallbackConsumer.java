package eu.driver.adapter.core.consumer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import org.apache.avro.generic.IndexedRecord;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;

import eu.driver.api.IAvroReceiver;

public abstract class CallbackConsumer<Key extends IndexedRecord, Value extends IndexedRecord> extends AbstractConsumer<Key, Value> {
	
	private Collection<IAvroReceiver<Key,Value>> receivers;

	public CallbackConsumer(Consumer<Key, Value> consumer, String topic) {
		super(consumer, topic);
		receivers = Collections.synchronizedCollection(new ArrayList<>());
	}

	@Override
	public void run() {
		consumer.subscribe(Collections.singletonList(getTopic()));
		logger.debug("Callback Consumer thread started for topic: " + getTopic());
		while (true) {
			ConsumerRecords<Key, Value> records = consumer.poll(1000);
			for (ConsumerRecord<Key, Value> record : records) {
				Key key = record.key();
				Value message = record.value();
				sendMessageToReceivers(key, message);
			}
		}
	}
	
	public void addReceiver(IAvroReceiver<Key,Value> receiver) {
		receivers.add(receiver);
	}
	
	private void sendMessageToReceivers(Key key, Value message) {
		receivers.forEach(r -> r.receiveMessage(key, message));
	}

}
