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
		CISAdapter adapter = CISAdapter.getInstance();
		
		// Add callbacks for received messages
		adapter.addCallback(new PrintAdapterCallback(), TopicConstants.STANDARD_TOPIC_CAP);
		adapter.addCallback(new PrintAdapterCallback(), TopicConstants.ADMIN_HEARTBEAT_TOPIC);

		try {
			while (true) {
				adapter.sendMessage(generateAvroCapFromXML());
				Thread.sleep(5000);
			}
		} catch (CommunicationException cEx) {
			
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
