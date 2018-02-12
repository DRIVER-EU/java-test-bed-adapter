package eu.driver.adaptor.mapper.cap;

import java.io.File;
import java.io.IOException;

import ly.stealth.xmlavro.DatumBuilder;

import org.apache.avro.Schema;
import org.apache.avro.generic.GenericRecord;
import org.apache.log4j.Logger;
import org.xml.sax.SAXParseException;

import com.google.publicalerts.cap.Alert;
import com.google.publicalerts.cap.CapException;
import com.google.publicalerts.cap.CapXmlParser;
import com.google.publicalerts.cap.NotCapException;

public class XMLToAVROMapper {
	
	private Logger log = Logger.getLogger(this.getClass());
	
	public XMLToAVROMapper() {
		log.debug("XMLToAVROMapper");
	}
	
	public GenericRecord convertCapToAvro(String capMsg, Boolean validate) {
		log.info("--> convertCapToAvro");
		log.debug(capMsg);
		GenericRecord avroAlert = null;
		boolean valid = true;
		
		if (validate) {
			// Parse it, with validation
		    CapXmlParser parser = new CapXmlParser(true);
		    try {
				Alert parsedAlert = parser.parseFrom(capMsg);
			} catch (NotCapException e) {
				log.error("Error parsing the CAPMessage!", e);
				valid = false;
			} catch (SAXParseException e) {
				log.error("Error parsing the CAPMessage!", e);
				valid = false;
			} catch (CapException e) {
				log.error("Error parsing the CAPMessage!", e);
				valid = false;
			}
		}
		if (valid) {
			try {
				String avscFile = XMLToAVROMapper.class.getResource("/avro/other/cap/cap-value.avsc").getPath();
				Schema schema = new Schema.Parser().parse(new File(avscFile));
				DatumBuilder datumBuilder = new DatumBuilder(schema);
				avroAlert = datumBuilder.createDatum(capMsg);
			} catch (IOException e) {
				log.error("Error creating AVRO Message");
			}
		}
		
		log.info("convertCapToAvro -->");
		return avroAlert;
	}

}
