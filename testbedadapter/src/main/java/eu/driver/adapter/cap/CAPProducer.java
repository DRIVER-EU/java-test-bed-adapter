package eu.driver.adapter.cap;

import org.apache.avro.generic.IndexedRecord;
import org.apache.kafka.clients.producer.Producer;

import eu.driver.adapter.core.producer.AbstractProducer;
import eu.driver.model.cap.CapKey;

public class CAPProducer extends AbstractProducer {

	public CAPProducer(Producer<IndexedRecord, IndexedRecord> producer, String topic) {
		super(producer, topic);
	}

	@Override
	protected CapKey createKey() {
		CapKey key = new CapKey();
		key.setId(getClientId());
		return key;
	}

}
