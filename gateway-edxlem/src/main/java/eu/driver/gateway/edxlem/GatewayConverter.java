package eu.driver.gateway.edxlem;

import com.xvrsim.model.entity.Item;

import eu.driver.adapter.core.CISAdapter;
import eu.driver.adapter.core.producer.GenericProducer;
import eu.driver.api.IAvroReceiver;

public class GatewayConverter {
	
	private CISAdapter adapter;
	
	public static void main(String[] args) {
		GatewayConverter converter = new GatewayConverter();
	}
	
	public GatewayConverter() {
		adapter = new CISAdapter();
		GenericProducer producer = adapter.getProducer("ambulance-edxl-rm");
		IAvroReceiver<Item> converter = new AmbulanceToEDXLRMConverter(producer);
		adapter.addSpecificReceiver("css-demo", converter);
	}
}
