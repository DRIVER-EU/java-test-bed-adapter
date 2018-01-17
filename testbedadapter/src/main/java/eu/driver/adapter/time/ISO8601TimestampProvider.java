package eu.driver.adapter.time;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;
import java.util.TimeZone;

import org.slf4j.Logger;

import eu.driver.adapter.logger.CISLogger;

public class ISO8601TimestampProvider implements ITimestampProvider {
	
	private TimeZone timeZone;
	private DateFormat utcDateFormat;
	private static final Logger logger = CISLogger.logger(ISO8601TimestampProvider.class);
	
	public ISO8601TimestampProvider() {
		timeZone = TimeZone.getTimeZone("UTC");
		utcDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
		utcDateFormat.setTimeZone(timeZone);
	}

	@Override
	public String getTimestamp() {
		return utcDateFormat.format(new Date());
	}
	
	/**
	 * Parses a String containing an ISO 8601 timestamp to a corresponding Java Date object.
	 * @param timeStamp ISO 8601 timestamp
	 * @return Optional Date. Will be empty if parse
	 */
	public Optional<Date> parseDate(String timeStamp) {
		Optional<Date> date;
		try {
			date = Optional.of(utcDateFormat.parse(timeStamp));
		} catch (ParseException e) {
			logger.error("Could not parse timestamp: " + timeStamp + " as an ISO 8601 timestamp");
			date = Optional.empty();
		}
		return date;
	}

}
