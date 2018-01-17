package eu.driver.adapter.core.consumer;

import java.util.Collections;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import org.apache.avro.generic.IndexedRecord;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.slf4j.Logger;

import eu.driver.adapter.logger.CISLogger;

public abstract class PollableConsumer<Key extends IndexedRecord, Message extends IndexedRecord> extends AbstractConsumer<Key, Message> {
	
	private final Queue<Message> messages; // TODO: prevent Queue overflowing
	private static final int MAX_QUEUE_SIZE = 1000; // TODO: make configurable?
	
	private static final Logger logger = CISLogger.logger(PollableConsumer.class);

	public PollableConsumer(Consumer<Key, Message> consumer, String topic) {
		super(consumer, topic);
		messages = new ConcurrentLinkedQueue<>();
	}
	
	public boolean hasMessages() {
		return !messages.isEmpty();
	}
	
	private void addMessage(Message message) {
		if(messages.size() > MAX_QUEUE_SIZE) {
			messages.poll();
			logger.warn("Queue for Pollable Consumer consuming topic: " + getTopic() + " is overflowing! Oldest message replaced by latest message.");
		}
		messages.add(message);
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
				addMessage(message);
			}
		}
	}

}
