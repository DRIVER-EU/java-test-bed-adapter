package eu.driver.adapter.core;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import eu.driver.adapter.properties.ProducerProperties;
import eu.driver.model.core.Heartbeat;

public class HeartbeatTask implements Runnable {

	private final HeartbeatProducer producer;
	private TimeZone timeZone;
	private DateFormat utcDateFormat;

	public HeartbeatTask(HeartbeatProducer heartbeatProducer) {
		producer = heartbeatProducer;
		configureTimezone();
	}

	private void configureTimezone() {
		timeZone = TimeZone.getTimeZone("UTC");
		utcDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
		utcDateFormat.setTimeZone(timeZone);
	}

	@Override
	public void run() {
		Heartbeat heartbeat = new Heartbeat();
		ProducerProperties props = ProducerProperties.getInstance();
		heartbeat.setId(props.getProperty(ProducerProperties.CLIENT_ID));
		String nowAsIso = utcDateFormat.format(new Date());
		heartbeat.setAlive(nowAsIso);

		producer.send(heartbeat);
		System.out.println("Sent heartbeat: " + heartbeat); // TODO: replace with logging
	}

}
