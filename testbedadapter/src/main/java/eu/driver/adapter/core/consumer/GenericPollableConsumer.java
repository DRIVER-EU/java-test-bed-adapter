package eu.driver.adapter.core.consumer;

import org.apache.avro.generic.GenericRecord;
import org.apache.kafka.clients.consumer.Consumer;

public class GenericPollableConsumer extends PollableConsumer<GenericRecord, GenericRecord>{

	public GenericPollableConsumer(Consumer<GenericRecord, GenericRecord> consumer, String topic) {
		super(consumer, topic);
	}

}
