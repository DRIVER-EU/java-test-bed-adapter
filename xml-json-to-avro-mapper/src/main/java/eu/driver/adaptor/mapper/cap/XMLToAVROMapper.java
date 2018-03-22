package eu.driver.adaptor.mapper.cap;

import java.io.File;
import java.io.IOException;

import ly.stealth.xmlavro.DatumBuilder;

import org.apache.avro.Schema;
import org.apache.avro.generic.GenericRecord;
import org.apache.log4j.Logger;
import org.xml.sax.SAXParseException;

public class XMLToAVROMapper {
	
	private Logger log = Logger.getLogger(this.getClass());
	
	public XMLToAVROMapper() {
		log.debug("XMLToAVROMapper");
	}
	
	public GenericRecord convertCapToAvro(String capMsg) {
		log.info("--> convertCapToAvro");
		log.debug(capMsg);
		GenericRecord avroAlert = null;
		
		try {
			String avscFile = XMLToAVROMapper.class.getResource("/avro/other/cap/cap-value.avsc").getPath();
			Schema schema = new Schema.Parser().parse(new File(avscFile));
			DatumBuilder datumBuilder = new DatumBuilder(schema);
			avroAlert = datumBuilder.createDatum(capMsg);
		} catch (IOException e) {
			log.error("Error creating AVRO Message");
		}
		
		log.info("convertCapToAvro -->");
		return avroAlert;
	}
	
	public GenericRecord convertMlpToAvro(String capMsg) {
		log.info("--> convertMlpToAvro");
		log.debug(capMsg);
		GenericRecord avroAlert = null;
		
		try {
			String avscFile = XMLToAVROMapper.class.getResource("/avro/other/mlp/mlp-slr-value.avsc").getPath();
			Schema schema = new Schema.Parser().parse(new File(avscFile));
			DatumBuilder datumBuilder = new DatumBuilder(schema);
			avroAlert = datumBuilder.createDatum(capMsg);
		} catch (IOException e) {
			log.error("Error creating AVRO Message");
		}
		
		log.info("convertMlpToAvro -->");
		return avroAlert;
	}
	
	public GenericRecord convertGeoJsonToAvro(String capMsg) {
		log.info("--> convertGeoJsonToAvro");
		log.debug(capMsg);
		GenericRecord avroAlert = null;
		
		try {
			String avscFile = XMLToAVROMapper.class.getResource("/avro/other/geojson/geojson-value.avsc").getPath();
			Schema schema = new Schema.Parser().parse(new File(avscFile));
			DatumBuilder datumBuilder = new DatumBuilder(schema);
			avroAlert = datumBuilder.createDatum(capMsg);
		} catch (IOException e) {
			log.error("Error creating AVRO Message");
		}
		
		log.info("convertGeoJsonToAvro -->");
		return avroAlert;
	}
	
	public String convertAvroToCap(eu.driver.model.cap.Alert alert) {
		log.info("--> convertAvroToCap");
		String xmlCap = null;
		try {
			String avscFile = XMLToAVROMapper.class.getResource("/avro/other/cap/cap-value.avsc").getPath();
			Schema schema = new Schema.Parser().parse(new File(avscFile));
			DatumBuilder datumBuilder = new DatumBuilder(schema);
			// ToDo: AVRO To XML converter needs to be implemented
			//xmlCap = datumBuilder.createDatum(alert);
		} catch (IOException e) {
			log.error("Error creating AVRO Message");
		}
		
		log.info("convertAvroToCap -->");
		return xmlCap;
	}

}
