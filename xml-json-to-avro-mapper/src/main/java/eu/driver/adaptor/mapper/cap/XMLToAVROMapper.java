package eu.driver.adaptor.mapper.cap;

import java.io.File;
import java.io.IOException;

import ly.stealth.xmlavro.DatumBuilder;

import org.apache.avro.Schema;
import org.apache.avro.generic.GenericRecord;
import org.apache.log4j.Logger;

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
			Schema schema = new Schema.Parser().parse(new File("config/avro/standard/cap/standard_cap-value.avsc"));
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
			Schema schema = new Schema.Parser().parse(new File("config/avro/standard/mlp/standard_mlp-value.avsc"));
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
			Schema schema = new Schema.Parser().parse(new File("config/avro/standard/geojson/standard_geojson-value.avsc"));
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
			Schema schema = new Schema.Parser().parse(new File("config/avro/standard/cap/standard_cap-value.avsc"));
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
