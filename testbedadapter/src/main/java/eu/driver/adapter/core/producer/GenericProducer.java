package eu.driver.adapter.core.producer;

import org.apache.avro.generic.GenericRecord;

import eu.driver.model.core.GenericKey;

public class GenericProducer extends AbstractProducer<GenericKey, GenericRecord> {

	public GenericProducer(String targetTopic) {
		super(targetTopic);
	}
	
	protected GenericKey createKey() {
		GenericKey key = new GenericKey();
		key.setId(getClientId());
		return key;
	}

}
