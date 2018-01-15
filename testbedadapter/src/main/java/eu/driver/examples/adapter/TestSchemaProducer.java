package eu.driver.examples.adapter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.apache.avro.Schema;
import org.apache.avro.Schema.Field;
import org.apache.avro.file.DataFileReader;
import org.apache.avro.file.DataFileWriter;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericDatumReader;
import org.apache.avro.generic.GenericRecord;
import org.apache.avro.io.DatumReader;
import org.apache.avro.io.DatumWriter;
import org.apache.avro.io.EncoderFactory;
import org.apache.avro.specific.SpecificDatumReader;
import org.apache.avro.specific.SpecificDatumWriter;

import eu.driver.model.cap.Alert;
import ly.stealth.xmlavro.DatumBuilder;
import ly.stealth.xmlavro.simple.Avro2XmlConverter;
import ly.stealth.xmlavro.simple.Converter;

public class TestSchemaProducer {

	public static void main(String[] args) throws IOException {
		String avscFile = TestSchemaProducer.class.getResource("/avro/other/cap/cap-value.avsc").getPath();
		String xmlFile = TestSchemaProducer.class.getResource("/data/examples/cap/amber.xml").getPath();

		Schema schema = new Schema.Parser().parse(new File(avscFile));
		DatumBuilder datumBuilder = new DatumBuilder(schema, "alert");
		Object datum = datumBuilder.createDatum(new File(xmlFile));

		GenericRecord writeAlert = (GenericRecord) datum;
		DatumWriter<Object> datumWriter = new SpecificDatumWriter<>(schema);
		DataFileWriter<Object> dataFileWriter = new DataFileWriter<>(datumWriter);
		dataFileWriter.create(writeAlert.getSchema(), new File("avrotest.avro"));
		dataFileWriter.append(writeAlert);
		dataFileWriter.close();

		// Deserialize users from disk
		DatumReader<GenericRecord> datumReader = new GenericDatumReader<GenericRecord>(schema);
		DataFileReader<GenericRecord> dataFileReader = new DataFileReader<GenericRecord>(new File("avrotest.avro"),
				datumReader);
		GenericRecord alertRead = null;
		while (dataFileReader.hasNext()) {
			// Reuse user object by passing it to next(). This saves us from
			// allocating and garbage collecting many objects for files with
			// many items.
			alertRead = dataFileReader.next(alertRead);
			System.out.println(alertRead);
		}
		dataFileReader.close();

		// Deserialize Users from disk
		DatumReader<Alert> alertDatumReader = new SpecificDatumReader<Alert>(Alert.class);
		DataFileReader<Alert> alertDataFileReader = new DataFileReader<Alert>(new File("avrotest.avro"),
				alertDatumReader);
		Alert alert = null;
		while (alertDataFileReader.hasNext()) {
			// Reuse user object by passing it to next(). This saves us from
			// allocating and garbage collecting many objects for files with
			// many items.
			alert = alertDataFileReader.next(alert);
			System.out.println(alert);
		}

		Avro2XmlConverter.avroToXml(new File("avrotest.avro"), new File("testcap.xml"), schema);

	}

}
