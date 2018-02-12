package eu.driver.adapter.core.producer;

import org.apache.avro.generic.IndexedRecord;
import org.apache.kafka.clients.producer.Producer;

import eu.driver.model.edxl.EDXLDistribution;

public class GenericProducer extends AbstractEDXLDEProducer {

	public GenericProducer(Producer<EDXLDistribution, IndexedRecord> producer, String targetTopic) {
		super(producer, targetTopic);
	}

	@Override
	protected EDXLDistribution setEDXLDEValues(EDXLDistribution standardKey) {
		return standardKey;
	}


}
