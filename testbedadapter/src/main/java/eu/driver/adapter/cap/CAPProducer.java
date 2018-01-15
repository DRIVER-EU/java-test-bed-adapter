package eu.driver.adapter.cap;

import eu.driver.adapter.core.AbstractProducer;
import eu.driver.adapter.properties.ProducerProperties;
import eu.driver.model.cap.Alert;
import eu.driver.model.cap.CapKey;

public class CAPProducer extends AbstractProducer<CapKey, Alert> {

	public CAPProducer(String topic) {
		super(topic);
	}

	@Override
	protected CapKey createKey() {
		CapKey key = new CapKey();
		ProducerProperties props = ProducerProperties.getInstance();
		key.setId(props.getProperty(ProducerProperties.CLIENT_ID));
		return key;
	}

}
