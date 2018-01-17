package eu.driver.adapter.core.producer;

import org.apache.avro.generic.IndexedRecord;
import org.apache.kafka.clients.producer.Producer;

import eu.driver.model.core.GenericKey;

public class GenericProducer extends AbstractProducer {

	public GenericProducer(Producer<IndexedRecord, IndexedRecord> producer, String targetTopic) {
		super(producer, targetTopic);
	}
	
	protected GenericKey createKey() {
		GenericKey key = new GenericKey();
		key.setId(getClientId());
		return key;
	}

}
