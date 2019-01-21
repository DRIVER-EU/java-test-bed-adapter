package eu.driver.adapter.core.consumer;

import org.apache.avro.generic.IndexedRecord;
import org.apache.avro.specific.SpecificData;
import org.slf4j.Logger;

import eu.driver.adapter.core.AdminAdapter;
import eu.driver.adapter.core.CISAdapter;
import eu.driver.adapter.logger.CISLogger;
import eu.driver.api.GenericAvroReceiver;

public class AdminTimeConsumer extends GenericAvroReceiver {

	private Logger logger = CISLogger.logger(CISAdapter.class);
	
	@Override
	public void receiveMessage(IndexedRecord key, IndexedRecord message) {
		if (message.getSchema().getName().equalsIgnoreCase("Timing")) {
			try {
				eu.driver.model.core.Timing timing = (eu.driver.model.core.Timing) SpecificData.get().deepCopy(eu.driver.model.core.Timing.SCHEMA$, message);
				AdminAdapter.getInstance().setCurrentTiming(timing);
			} catch (Exception e) {
				
			}
		}
	}

}
