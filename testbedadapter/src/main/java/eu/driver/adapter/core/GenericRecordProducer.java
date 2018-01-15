package eu.driver.adapter.core;

import org.apache.avro.generic.GenericRecord;

import eu.driver.adapter.properties.ProducerProperties;
import eu.driver.model.core.GenericKey;

public class GenericRecordProducer extends AbstractProducer<GenericKey, GenericRecord> {

	public GenericRecordProducer(String targetTopic) {
		super(targetTopic);
	}
	
	protected GenericKey createKey() {
		GenericKey key = new GenericKey();
		ProducerProperties props = ProducerProperties.getInstance();
		key.setId(props.getProperty(ProducerProperties.CLIENT_ID));
		return key;
	}

}
