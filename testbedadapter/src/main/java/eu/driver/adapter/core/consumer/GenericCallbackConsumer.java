package eu.driver.adapter.core.consumer;

import org.apache.avro.generic.IndexedRecord;
import org.apache.kafka.clients.consumer.Consumer;

public class GenericCallbackConsumer extends CallbackConsumer<IndexedRecord, IndexedRecord> {

	public GenericCallbackConsumer(Consumer<IndexedRecord, IndexedRecord> consumer, String topic) {
		super(consumer, topic);
	}

}
