package eu.driver.adapter.core.consumer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import org.apache.avro.generic.IndexedRecord;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;

import eu.driver.api.IAvroReceiver;

public abstract class CallbackConsumer extends AbstractConsumer {
	
	private Collection<IAvroReceiver> receivers;

	public CallbackConsumer(Consumer<IndexedRecord, IndexedRecord> consumer, String topic) {
		super(consumer, topic);
		receivers = Collections.synchronizedCollection(new ArrayList<>());
	}

	@Override
	public void run() {
		consumer.subscribe(Collections.singletonList(getTopic()));
		logger.debug("Callback Consumer thread started for topic: " + getTopic());
		while (true) {
			ConsumerRecords<IndexedRecord, IndexedRecord> records = consumer.poll(1000);
			for (ConsumerRecord<IndexedRecord, IndexedRecord> record : records) {
				IndexedRecord message = record.value();
				sendMessageToReceivers(message);
			}
		}
	}
	
	public void addReceiver(IAvroReceiver receiver) {
		receivers.add(receiver);
	}
	
	private void sendMessageToReceivers(IndexedRecord message) {
		receivers.forEach(r -> r.receiveMessage(message));
	}

}
