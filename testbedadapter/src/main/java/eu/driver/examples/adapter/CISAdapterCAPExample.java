package eu.driver.examples.adapter;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.avro.Schema;
import org.apache.avro.generic.GenericRecord;

import eu.driver.adapter.constants.TopicConstants;
import eu.driver.adapter.core.CISAdapter;
import eu.driver.adapter.excpetion.CommunicationException;
import eu.driver.model.cap.Alert;
import ly.stealth.xmlavro.DatumBuilder;

public class CISAdapterCAPExample {

	public static void main(String[] args) throws InterruptedException, IOException {
		// created the CIS Adapter that will send heartbeats, log
		CISAdapter adapter = CISAdapter.getInstance();

		String topic = "standard_cap";

		adapter.createProducer(topic);
		try {
			adapter.sendMessage(generateAvroCapFromXML(), topic);
		} catch (CommunicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("SENT ! ");

		// Add callbacks for received messages
		adapter.addCallback(new PrintAdapterCallback(), topic);
	}

	private static GenericRecord generateAvroCapFromXML() throws IOException {
		String xmlFile = TestSchemaProducer.class.getResource("/data/examples/cap/earthquake.xml").getPath();

		DatumBuilder datumBuilder = new DatumBuilder(Alert.getClassSchema());
		GenericRecord datum = datumBuilder.createDatum(new File(xmlFile));

		return datum;
	}

}
