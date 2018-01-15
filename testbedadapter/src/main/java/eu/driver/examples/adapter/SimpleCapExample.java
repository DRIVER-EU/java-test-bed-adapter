package eu.driver.examples.adapter;

import java.io.File;
import java.io.IOException;

import org.apache.avro.Schema;
import org.apache.avro.generic.GenericRecord;

import eu.driver.adapter.core.CISAdapter;
import eu.driver.adapter.core.GenericRecordProducer;
import ly.stealth.xmlavro.DatumBuilder;

public class SimpleCapExample {

	public static void main(String[] args) throws InterruptedException, IOException {
		// created the CIS Adapter that will send heartbeats, log
		CISAdapter adapter = new CISAdapter();
			
		String avscFile = TestSchemaProducer.class.getResource("/avro/other/cap/cap-value.avsc").getPath();
		String xmlFile = TestSchemaProducer.class.getResource("/data/examples/cap/amber.xml").getPath();

		Schema schema = new Schema.Parser().parse(new File(avscFile));
		DatumBuilder datumBuilder = new DatumBuilder(schema, "alert");
		GenericRecord datum = datumBuilder.createDatum(new File(xmlFile));
		
		GenericRecordProducer producer = adapter.getProducer("test-cap");
		producer.send(datum);
	}
}
