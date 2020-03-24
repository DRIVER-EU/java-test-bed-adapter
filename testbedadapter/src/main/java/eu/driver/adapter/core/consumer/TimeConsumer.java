package eu.driver.adapter.core.consumer;

import org.apache.avro.generic.IndexedRecord;
import org.apache.avro.specific.SpecificData;

import eu.driver.adapter.core.CISAdapter;
import eu.driver.api.GenericAvroReceiver;
import eu.driver.model.sim.config.TimeManagement;

public class TimeConsumer extends GenericAvroReceiver {

	@Override
	public void receiveMessage(IndexedRecord key, IndexedRecord message) {
		if (message.getSchema().getName().equalsIgnoreCase("TimeManagement")) {
			try {
				TimeManagement timing = (TimeManagement) SpecificData.get().deepCopy(TimeManagement.SCHEMA$, message);
				CISAdapter.getInstance().setCurrentTiming(timing);
			} catch (Exception e) {
				
			}
		}
	}

}
