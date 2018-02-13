package eu.driver.adapter.core.producer;

import org.apache.avro.generic.IndexedRecord;
import org.apache.kafka.clients.producer.Producer;

import eu.driver.model.edxl.DistributionKind;
import eu.driver.model.edxl.DistributionStatus;
import eu.driver.model.edxl.EDXLDistribution;

public abstract class AbstractEDXLDEProducer extends AbstractProducer<EDXLDistribution, IndexedRecord> {

	public AbstractEDXLDEProducer(Producer<EDXLDistribution, IndexedRecord> producer, String topic) {
		super(producer, topic);
	}

	@Override
	public EDXLDistribution createKey() {
		EDXLDistribution key = new EDXLDistribution();
		key.setDateTimeSent(System.currentTimeMillis());
		key.setDateTimeExpires(System.currentTimeMillis());
		key.setDistributionID(getClientId() + "-" + getMessageNumber());
		key.setSenderID(getClientId());
		key.setDistributionKind(DistributionKind.Unknown);
		key.setDistributionStatus(DistributionStatus.Unknown);
		key = setEDXLDEValues(key);
		return key;
	}

}
