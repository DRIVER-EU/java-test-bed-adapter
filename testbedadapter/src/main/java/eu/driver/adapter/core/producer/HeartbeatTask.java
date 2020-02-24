package eu.driver.adapter.core.producer;

import java.util.Date;

import eu.driver.model.core.Heartbeat;

public class HeartbeatTask implements Runnable {

	private final HeartbeatProducer producer;
	private String origin;

	public HeartbeatTask(HeartbeatProducer heartbeatProducer, String origin) {
		producer = heartbeatProducer;
		this.origin = origin;
	}

	@Override
	public void run() {
		Heartbeat heartbeat = new Heartbeat();
		heartbeat.setId(producer.getClientId());
		heartbeat.setAlive(new Date().getTime());
		heartbeat.setOrigin(this.origin);
		producer.send(heartbeat);
	}

}
