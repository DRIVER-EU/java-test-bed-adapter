package eu.driver.adapter.core.consumer;

import org.apache.avro.generic.GenericRecord;
import org.apache.kafka.clients.consumer.Consumer;

public class GenericCallbackConsumer extends CallbackConsumer<GenericRecord, GenericRecord> {

	public GenericCallbackConsumer(Consumer<GenericRecord, GenericRecord> consumer, String topic) {
		super(consumer, topic);
	}

}
