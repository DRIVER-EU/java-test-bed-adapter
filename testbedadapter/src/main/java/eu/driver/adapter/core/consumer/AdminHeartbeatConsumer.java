package eu.driver.adapter.core.consumer;

import org.apache.avro.generic.IndexedRecord;
import org.apache.avro.specific.SpecificData;

import eu.driver.api.GenericAvroReceiver;

public class AdminHeartbeatConsumer extends GenericAvroReceiver {

	@Override
	public void receiveMessage(IndexedRecord key, IndexedRecord receivedMessage) {
		if (receivedMessage.getSchema().getName().equalsIgnoreCase("AdminHeartbeat")) {
			try {
				eu.driver.model.core.AdminHeartbeat logMsg = (eu.driver.model.core.AdminHeartbeat) SpecificData.get().deepCopy(eu.driver.model.core.AdminHeartbeat.SCHEMA$, receivedMessage);
			} catch (Exception e) {
				
			}
		}
	}

}
