package eu.driver.adaptor.mapper.cap;

import java.io.File;
import java.io.IOException;




import java.io.StringReader;

import javax.xml.XMLConstants;
import javax.xml.transform.Source;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.*;

import java.net.URL;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import ly.stealth.xmlavro.DatumBuilder;

import org.apache.avro.Schema;
import org.apache.avro.generic.GenericRecord;
import org.apache.log4j.Logger;
import org.w3c.dom.Document;

import eu.driver.model.cap.Alert;
import eu.driver.model.emsi.TSO_2_0;

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
			//Schema schema = new Schema.Parser().parse(new File("config/avro/standard/cap/standard_cap-value.avsc"));
			DatumBuilder datumBuilder = new DatumBuilder(new Alert().getSchema());
			avroAlert = datumBuilder.createDatum(capMsg);
		} catch (Exception e) {
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
	
	public GenericRecord convertEMSIToAvro(String emsiMsg) {
		log.info("--> convertEMSIToAvro");
		log.debug(emsiMsg);
		GenericRecord avroEMSI = null;
		
		try {
			DatumBuilder datumBuilder = new DatumBuilder(new TSO_2_0().getSchema());
			avroEMSI = datumBuilder.createDatum(emsiMsg);
		} catch (Exception e) {
			log.error("Error creating AVRO Message");
		}
		
		log.info("convertEMSIToAvro -->");
		return avroEMSI;
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
	
	public Boolean validateCAP(String capMessage) {
		try {
			// parse an XML document into a DOM tree
		    DocumentBuilder parser = DocumentBuilderFactory.newInstance().newDocumentBuilder();
		    InputSource is = new InputSource(new StringReader(capMessage));
		    Document document = parser.parse(is);

		    // create a SchemaFactory capable of understanding WXS schemas
		    SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);

		    // load a WXS schema, represented by a Schema instance
		    Source schemaFile = new StreamSource(new File("schema/xsd/cap1_2.xsd"));
		    javax.xml.validation.Schema schema = factory.newSchema(schemaFile);

		    // create a Validator instance, which can be used to validate an instance document
		    Validator validator = schema.newValidator();

		    // validate the DOM tree
		    try {
		        validator.validate(new DOMSource(document));
		    } catch (SAXException e) {
		        // instance document is invalid!
		    	log.error("Error validating the XML File:" , e);
		    }
		} catch (Exception e) {
			log.error("Error parsing the XML File!");
			return false;
		}
		return true;
	}

}
