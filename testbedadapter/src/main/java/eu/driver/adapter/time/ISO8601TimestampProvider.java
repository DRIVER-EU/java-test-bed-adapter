package eu.driver.adapter.time;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class ISO8601TimestampProvider implements ITimestampProvider {
	
	private TimeZone timeZone;
	private DateFormat utcDateFormat;
	
	public ISO8601TimestampProvider() {
		timeZone = TimeZone.getTimeZone("UTC");
		utcDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
		utcDateFormat.setTimeZone(timeZone);
	}

	@Override
	public String getTimestamp() {
		return utcDateFormat.format(new Date());
	}

}
