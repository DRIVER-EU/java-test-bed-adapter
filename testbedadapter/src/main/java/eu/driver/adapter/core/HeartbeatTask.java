package eu.driver.adapter.core;

import org.slf4j.Logger;

import eu.driver.adapter.logger.CISLogger;
import eu.driver.adapter.time.ISO8601TimestampProvider;
import eu.driver.adapter.time.ITimestampProvider;
import eu.driver.model.core.Heartbeat;

public class HeartbeatTask implements Runnable {

	private final HeartbeatProducer producer;
	private final ITimestampProvider timestampProvider;
	private static final Logger logger = CISLogger.logger(HeartbeatTask.class);

	public HeartbeatTask(HeartbeatProducer heartbeatProducer) {
		producer = heartbeatProducer;
		timestampProvider = new ISO8601TimestampProvider();
	}

	@Override
	public void run() {
		Heartbeat heartbeat = new Heartbeat();
		heartbeat.setId(producer.getClientId());
		heartbeat.setAlive(timestampProvider.getTimestamp());
		producer.send(heartbeat);
		logger.debug("Sent heartbeat: " + heartbeat);
	}

}
