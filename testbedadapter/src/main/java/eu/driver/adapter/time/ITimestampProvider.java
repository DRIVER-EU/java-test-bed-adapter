package eu.driver.adapter.time;

import java.util.Date;
import java.util.Optional;

public interface ITimestampProvider {
	
	/**
	 * Returns a timestamp String for the current time
	 * @return
	 */
	public String getTimestamp();
	
	/**
	 * Parses a timestamp String to a corresponding Java Date object
	 * @param timeStamp
	 * @return
	 */
	public Optional<Date> parseDate(String timeStamp);

}
