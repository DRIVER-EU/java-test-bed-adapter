package eu.driver.examples.adapter;

import java.io.File;
import java.io.IOException;

import org.apache.avro.Schema;
import org.apache.avro.generic.GenericRecord;

import eu.driver.adapter.constants.TopicConstants;
import eu.driver.adapter.core.CISAdapter;
import eu.driver.adapter.excpetion.CommunicationException;
import ly.stealth.xmlavro.DatumBuilder;

public class CISAdapterCAPExample {

	public static void main(String[] args) throws InterruptedException, IOException {
		// created the CIS Adapter that will send heartbeats, log
<<<<<<< HEAD
		CISAdapter adapter = new CISAdapter();

		// Add an Avro Receiver (callback for receiving Avro Records) for topic 'cap'
		adapter.addAvroReceiver("cap", new PrintingAvroReceiver());
		adapter.addAvroReceiver("connect-status-heartbeat", new PrintingAvroReceiver());
		
		// Create a general purpose producer for sending an Avro GenericRecord
		GenericProducer producer = adapter.getProducer("cap");
		
		// Generate a CAP Avro message from an XML source
		GenericRecord capAvro = generateAvroCapFromXML();
		// Use the producer to send a CAP message. This will be received and printed via
		// the Callback above.

		while (true) {
			producer.send(capAvro);
			Thread.sleep(5000);
=======
		CISAdapter adapter = CISAdapter.getInstance();
		
		// Add callbacks for received messages
		adapter.addCallback(new PrintAdapterCallback(), TopicConstants.STANDARD_TOPIC_CAP);
		adapter.addCallback(new PrintAdapterCallback(), TopicConstants.HEARTBEAT_TOPIC);

		try {
			while (true) {
				adapter.sendMessage(generateAvroCapFromXML());
				Thread.sleep(5000);
			}
		} catch (CommunicationException cEx) {
			
>>>>>>> remotes/origin/Develop
		}
	}

	private static GenericRecord generateAvroCapFromXML() throws IOException {
		// TODO: read XML from String and InputStream as well
		String avscFile = TestSchemaProducer.class.getResource("/avro/other/cap/cap-value.avsc").getPath();
		String xmlFile = TestSchemaProducer.class.getResource("/data/examples/cap/earthquake.xml").getPath();

		Schema schema = new Schema.Parser().parse(new File(avscFile));
		DatumBuilder datumBuilder = new DatumBuilder(schema);
		GenericRecord datum = datumBuilder.createDatum(new File(xmlFile));

		return datum;
	}

}
