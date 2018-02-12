package eu.driver.api;

import org.apache.avro.generic.IndexedRecord;

public abstract class GenericAvroReceiver implements IAvroReceiver<IndexedRecord> {
	
	/**
	 * Receives a message of type Message from the CIS Adapter.
	 * It is strongly advised to place received messages on a queue to allow
	 * quick returning of this function by the Thread performing the callback.
	 * 
	 * The received messages on the queue can then be processed later by a Thread owned
	 * by the Client application that uses the CIS Adapter.
	 * @param message
	 */
	public abstract void receiveMessage(IndexedRecord message);

}
