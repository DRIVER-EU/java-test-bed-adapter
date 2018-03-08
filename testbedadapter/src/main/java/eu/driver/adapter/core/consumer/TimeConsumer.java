package eu.driver.adapter.core.consumer;

import org.apache.avro.generic.IndexedRecord;
import org.slf4j.Logger;

import eu.driver.adapter.core.CISAdapter;
import eu.driver.adapter.logger.CISLogger;
import eu.driver.api.GenericAvroReceiver;
import eu.driver.model.core.Timing;

public class TimeConsumer extends GenericAvroReceiver {

	private Logger logger = CISLogger.logger(CISAdapter.class);
	
	@Override
	public void receiveMessage(IndexedRecord message) {
		if (message instanceof Timing) {
			logger.info("Timing message received!");
			CISAdapter.getInstance().setCurrentTiming((Timing)message);
		}
	}

}
