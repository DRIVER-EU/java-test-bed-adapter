package eu.driver.examples.adapter;

import org.apache.avro.generic.GenericRecord;
import org.apache.avro.generic.IndexedRecord;

import eu.driver.api.IAvroReceiver;

/**
 * A simple example receiver for printing out a received Avro message
 * @author Pieter
 *
 */
public class PrintingAvroReceiver implements IAvroReceiver<GenericRecord> {

	@Override
	public void receiveMessage(IndexedRecord message) {
		System.out.println("Message Received: " + message);
	}

}
