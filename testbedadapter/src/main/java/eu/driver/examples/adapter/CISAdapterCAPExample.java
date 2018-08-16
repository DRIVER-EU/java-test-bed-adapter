package eu.driver.examples.adapter;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

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

		String topic = "test-pieter4";

		KeepOperationalReachabilityResponse msg = new KeepOperationalReachabilityResponse();
		ArrayList<CharSequence> test = new ArrayList<>();
		test.add("a");
		test.add("b");
		msg.setErrorMessages(test);
		ArrayList<ReachabilityInformation> isochroneInfo = new ArrayList<>();

		for (int i = 0; i < 5000; i++) {
			isochroneInfo.add(new ReachabilityInformation(i, test));
		}
		msg.setListOfIsochroneInformation(isochroneInfo);

		adapter.createProducer(topic);
		try {
			adapter.sendMessage(msg, topic);
		} catch (CommunicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("SENT ! ");

		// Add callbacks for received messages
		adapter.addCallback(new PrintAdapterCallback(), topic);
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
