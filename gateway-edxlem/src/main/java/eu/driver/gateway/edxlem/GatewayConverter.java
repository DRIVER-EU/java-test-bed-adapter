package eu.driver.gateway.edxlem;

import org.apache.avro.generic.IndexedRecord;
import org.slf4j.Logger;

import eu.driver.adapter.core.CISAdapter;
import eu.driver.adapter.core.producer.GenericProducer;
import eu.driver.adapter.logger.CISLogger;
import eu.driver.api.GenericAvroReceiver;

public class GatewayConverter {
	
	private CISAdapter adapter;
	
	private static Logger logger = CISLogger.logger(GatewayConverter.class);
	
	public static void main(String[] args) {
		GatewayConverter converter = new GatewayConverter();
		logger.info("GatewayConverter started!");
	}
	
	public GatewayConverter() {
		adapter = new CISAdapter();
//		GenericProducer producer = adapter.getProducer("css-demo-mlp");
//		XVRItemToMLPConverter xvrToMLPConverter = new XVRItemToMLPConverter(producer);
//		adapter.addSpecificReceiver("css-demo", xvrToMLPConverter);
		adapter.addAvroReceiver("css-demo", new GenericAvroReceiver() {
			@Override
			public void receiveMessage(IndexedRecord message) {
				System.out.println("MSG RECEIVED: " + message);
			}
		});
	}
}
