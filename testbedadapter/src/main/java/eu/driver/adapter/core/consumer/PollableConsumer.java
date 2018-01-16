package eu.driver.adapter.core.consumer;

import java.util.Collections;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import org.apache.avro.generic.IndexedRecord;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;

public abstract class PollableConsumer<Key extends IndexedRecord, Message extends IndexedRecord> extends AbstractConsumer<Key, Message> {
	
	private final Queue<Message> messages; // TODO: prevent Queue overflowing

	public PollableConsumer(String targetTopic) {
		super(targetTopic);
		messages = new ConcurrentLinkedQueue<>();
	}
	
	public boolean hasMessages() {
		return !messages.isEmpty();
	}
	
	public Message poll() {
		return messages.poll();
	}

	@Override
	public void run() {
		consumer.subscribe(Collections.singletonList(getTopic()));
		logger.debug("Pollable Consumer thread started for topic: " + getTopic());
		while (true) {
			ConsumerRecords<Key, Message> records = consumer.poll(1000);
			for (ConsumerRecord<Key, Message> record : records) {
				Message message = record.value();
				messages.add(message);
			}
		}
	}

}
