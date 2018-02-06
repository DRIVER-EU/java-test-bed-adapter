package eu.driver.gateway.edxlem;

import org.apache.avro.generic.IndexedRecord;

import eu.driver.adapter.core.producer.GenericProducer;
import eu.driver.api.IAvroReceiver;

public class AmbulanceToEDXLRMConverter implements IAvroReceiver {
	
	private GenericProducer outputProducer;
	
	public AmbulanceToEDXLRMConverter(GenericProducer producer) {
		outputProducer = producer;
	}

	@Override
	public void receiveMessage(IndexedRecord message) {
		// TODO convert message to Item, convert to EXDL-RM Avro object and send via output producer
	}

}
