package eu.driver.adapter.core.consumer;

import org.apache.avro.generic.IndexedRecord;
import org.apache.kafka.clients.consumer.Consumer;

public class SpecificCallbackConsumer<Key extends IndexedRecord, Value extends IndexedRecord> extends CallbackConsumer<Key, Value> {

	public SpecificCallbackConsumer(Consumer<Key, Value> consumer, String topic) {
		super(consumer, topic);
	}
	
}
