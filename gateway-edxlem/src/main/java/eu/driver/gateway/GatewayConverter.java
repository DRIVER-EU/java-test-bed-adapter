package eu.driver.gateway;

import org.slf4j.Logger;

import eu.driver.adapter.core.CISAdapter;
import eu.driver.adapter.core.producer.GenericProducer;
import eu.driver.adapter.logger.CISLogger;
import eu.driver.gateway.geojson.XVRItemToGeoJSONConverter;

public class GatewayConverter {
	
	private CISAdapter adapter;
	
	private static Logger logger = CISLogger.logger(GatewayConverter.class);
	
	public static void main(String[] args) {
		new GatewayConverter();
		logger.info("GatewayConverter started!");
	}
	
	public GatewayConverter() {
		adapter = CISAdapter.getInstance();
		String outputTopic = GatewayProperties.getInstance().getProperty(GatewayProperties.OUTPUT_TOPIC);
		String inputTopic = GatewayProperties.getInstance().getProperty(GatewayProperties.INPUT_TOPIC);
		GenericProducer producer = adapter.createProducer(outputTopic);
		XVRItemToGeoJSONConverter xvrToMLPConverter = new XVRItemToGeoJSONConverter(producer);
		
		adapter.addCallback(xvrToMLPConverter, inputTopic);
	}
}
