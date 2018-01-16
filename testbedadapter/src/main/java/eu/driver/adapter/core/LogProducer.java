package eu.driver.adapter.core;

import eu.driver.model.core.Log;
import eu.driver.model.core.LogKey;

/**
 * Producer for sending Log messages to the Common Information Space (CIS). The configuration
 * topic and the Log message key and value type are fixed to comply
 * with the CIS requirements.
 * 
 * @author hameetepa
 */
public class LogProducer extends AbstractProducer<LogKey, Log> {

	private static final String LOGGING_TOPIC = "connect-status-log";

	public LogProducer() {
		super(LOGGING_TOPIC);
	}
	
	protected LogKey createKey() {
		LogKey key = new LogKey();
		key.setId(getClientId());
		return key;
	}
}
