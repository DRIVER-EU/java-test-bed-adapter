package eu.driver.adapter.core.producer.admin;

import java.util.Date;

import eu.driver.model.core.AdminHeartbeat;

public class AdminHeartbeatTask implements Runnable {

	private final AdminHeartbeatProducer producer;

	public AdminHeartbeatTask(AdminHeartbeatProducer heartbeatProducer) {
		producer = heartbeatProducer;
	}

	@Override
	public void run() {
		AdminHeartbeat heartbeat = new AdminHeartbeat();
		heartbeat.setId(producer.getClientId());
		heartbeat.setAlive(new Date().getTime());
		producer.send(heartbeat);
	}

}