package eu.driver.adapter.core.producer;

import java.util.Date;

import eu.driver.model.core.Heartbeat;

public class HeartbeatTask implements Runnable {

	private final HeartbeatProducer producer;

	public HeartbeatTask(HeartbeatProducer heartbeatProducer) {
		producer = heartbeatProducer;
	}

	@Override
	public void run() {
		Heartbeat heartbeat = new Heartbeat();
		heartbeat.setId(producer.getClientId());
		heartbeat.setAlive(new Date().getTime());
		producer.send(heartbeat);
	}

}
