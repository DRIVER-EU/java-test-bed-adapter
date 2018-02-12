package eu.driver.examples.adapter;

import org.apache.avro.generic.IndexedRecord;

import eu.driver.api.GenericAvroReceiver;
import eu.driver.api.IAvroReceiver;

/**
 * A simple example receiver for printing out a received Avro message
 * @author Pieter
 *
 */
public class PrintingAvroReceiver extends GenericAvroReceiver {

	@Override
	public void receiveMessage(IndexedRecord message) {
		System.out.println("Message Received: " + message);
	}

}
