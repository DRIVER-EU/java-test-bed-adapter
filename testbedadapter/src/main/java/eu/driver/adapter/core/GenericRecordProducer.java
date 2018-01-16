package eu.driver.adapter.core;

import org.apache.avro.generic.GenericRecord;

import eu.driver.model.core.GenericKey;

public class GenericRecordProducer extends AbstractProducer<GenericKey, GenericRecord> {

	public GenericRecordProducer(String targetTopic) {
		super(targetTopic);
	}
	
	protected GenericKey createKey() {
		GenericKey key = new GenericKey();
		key.setId(getClientId());
		return key;
	}

}
