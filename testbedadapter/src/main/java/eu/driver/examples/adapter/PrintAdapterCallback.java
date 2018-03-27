package eu.driver.examples.adapter;

import org.apache.avro.generic.IndexedRecord;

import eu.driver.api.IAdaptorCallback;

public class PrintAdapterCallback implements IAdaptorCallback {
	
	public void messageReceived(IndexedRecord key, IndexedRecord receivedMessage) {
		System.out.println("Message Received: " + receivedMessage.toString());
	}
}
