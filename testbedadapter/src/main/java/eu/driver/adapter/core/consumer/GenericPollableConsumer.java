package eu.driver.adapter.core.consumer;

import org.apache.avro.generic.GenericRecord;

import eu.driver.model.core.GenericKey;

public class GenericPollableConsumer extends PollableConsumer<GenericKey, GenericRecord> {

	public GenericPollableConsumer(String targetTopic) {
		super(targetTopic);
	}

}
