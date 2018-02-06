package eu.driver.gateway.edxlem;

import eu.driver.adapter.core.CISAdapter;
import eu.driver.adapter.core.producer.GenericProducer;
import eu.driver.api.IAvroReceiver;

public class GatewayConverter {
	
	private CISAdapter adapter;
	
	public GatewayConverter() {
		adapter = new CISAdapter();
		GenericProducer producer = adapter.getProducer("ambulance-edxl-rm");
		IAvroReceiver converter = new AmbulanceToEDXLRMConverter(producer);
		adapter.addAvroReceiver("css-demo", converter);
	}
	
	

}
