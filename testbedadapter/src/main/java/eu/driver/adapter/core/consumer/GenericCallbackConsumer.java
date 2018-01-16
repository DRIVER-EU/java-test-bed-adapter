package eu.driver.adapter.core.consumer;

import org.apache.avro.generic.GenericRecord;

import eu.driver.model.core.GenericKey;

public class GenericCallbackConsumer extends CallbackConsumer<GenericKey, GenericRecord> {

	public GenericCallbackConsumer(String targetTopic) {
		super(targetTopic);
	}

}
