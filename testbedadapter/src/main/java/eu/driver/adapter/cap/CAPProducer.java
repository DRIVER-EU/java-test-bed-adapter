package eu.driver.adapter.cap;

import eu.driver.adapter.core.producer.AbstractProducer;
import eu.driver.model.cap.Alert;
import eu.driver.model.cap.CapKey;

public class CAPProducer extends AbstractProducer<CapKey, Alert> {

	public CAPProducer(String topic) {
		super(topic);
	}

	@Override
	protected CapKey createKey() {
		CapKey key = new CapKey();
		key.setId(getClientId());
		return key;
	}

}
