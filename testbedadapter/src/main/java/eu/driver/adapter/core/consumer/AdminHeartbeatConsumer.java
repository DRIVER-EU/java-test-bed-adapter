package eu.driver.adapter.core.consumer;

import org.apache.avro.generic.IndexedRecord;
import org.apache.avro.specific.SpecificData;

import eu.driver.adapter.core.CISAdapter;
import eu.driver.adapter.logger.CISLogger;
import eu.driver.api.GenericAvroReceiver;

public class AdminHeartbeatConsumer extends GenericAvroReceiver {
	
	private CISAdapter adapter = null;
	
	private CISLogger logger = new CISLogger(AdminHeartbeatConsumer.class);
	
	public AdminHeartbeatConsumer(CISAdapter adapter) {
		this.adapter = adapter;
	}

	@Override
	public void receiveMessage(IndexedRecord key, IndexedRecord receivedMessage) {
		if (receivedMessage.getSchema().getName().equalsIgnoreCase("AdminHeartbeat")) {
			try {
				eu.driver.model.core.AdminHeartbeat heartbeatMsg = (eu.driver.model.core.AdminHeartbeat) SpecificData.get().deepCopy(eu.driver.model.core.AdminHeartbeat.SCHEMA$, receivedMessage);
				if (adapter != null) {
					logger.info("The Adapter received the AdminHeartbeat, continue with Adapter Initialization!");
					adapter.initCoreTopics();	
				}
			} catch (Exception e) {
				
			}
		}
	}

}
