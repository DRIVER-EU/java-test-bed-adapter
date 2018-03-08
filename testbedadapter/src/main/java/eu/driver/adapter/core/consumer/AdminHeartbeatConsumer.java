package eu.driver.adapter.core.consumer;

import org.apache.avro.generic.IndexedRecord;
import org.slf4j.Logger;

import eu.driver.adapter.core.CISAdapter;
import eu.driver.adapter.logger.CISLogger;
import eu.driver.api.GenericAvroReceiver;
import eu.driver.model.core.AdminHeartbeat;

public class AdminHeartbeatConsumer extends GenericAvroReceiver {

	private Logger logger = CISLogger.logger(CISAdapter.class);
	
	@Override
	public void receiveMessage(IndexedRecord message) {
		if (message instanceof AdminHeartbeat) {
			logger.info("AdminTool heartbeat received!");
			
		}
	}

}
