package eu.driver.adapter.core.producer;

import org.apache.avro.generic.IndexedRecord;
import org.apache.kafka.clients.producer.Producer;

import eu.driver.adapter.properties.ClientProperties;
import eu.driver.model.edxl.EDXLDistribution;

/**
 * Producer for sending Log messages to the Common Information Space (CIS). The configuration
 * topic and the Log message key and value type are fixed to comply
 * with the CIS requirements.
 * 
 * @author hameetepa
 */
public class LogProducer extends AbstractEDXLDEProducer {

	public LogProducer(Producer<EDXLDistribution, IndexedRecord> producer) {
		super(producer, ClientProperties.getInstance().getProperty(ClientProperties.LOG_TOPIC));
	}

	@Override
	protected EDXLDistribution setEDXLDEValues(EDXLDistribution standardKey) {
		return standardKey;
	}

}
