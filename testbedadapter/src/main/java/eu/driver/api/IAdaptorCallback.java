package eu.driver.api;

import org.apache.avro.generic.IndexedRecord;

public interface IAdaptorCallback {
	
	public void messageReceived(IndexedRecord receivedMessage);

}
