package eu.driver.adapter.core.producer;

import eu.driver.adapter.time.ISO8601TimestampProvider;
import eu.driver.adapter.time.ITimestampProvider;
import eu.driver.model.core.Heartbeat;

public class HeartbeatTask implements Runnable {

	private final HeartbeatProducer producer;
	private final ITimestampProvider timestampProvider;

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
	}

}
