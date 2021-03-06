package eu.driver.adapter.core.producer.admin;

import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import org.apache.avro.generic.IndexedRecord;
import org.apache.kafka.clients.producer.Producer;

import eu.driver.adapter.core.producer.AbstractEDXLDEProducer;
import eu.driver.adapter.core.producer.HeartbeatTask;
import eu.driver.adapter.excpetion.CommunicationException;
import eu.driver.model.core.AdminHeartbeat;
import eu.driver.model.core.Heartbeat;
import eu.driver.model.edxl.DistributionKind;
import eu.driver.model.edxl.DistributionStatus;
import eu.driver.model.edxl.EDXLDistribution;

public class AdminHeartbeatProducer extends AbstractEDXLDEProducer {

	private ScheduledExecutorService heartbeatScheduler;
	private ScheduledFuture<?> taskReference = null;
	

	public AdminHeartbeatProducer(Producer<EDXLDistribution, IndexedRecord> producer, String topicName) throws Exception {
		super(producer, topicName);
		heartbeatScheduler = Executors.newScheduledThreadPool(1);
	}
	
	public void sendInitialHeartbeat() throws CommunicationException {
		AdminHeartbeat heartbeat = new AdminHeartbeat();
		heartbeat.setId(this.getClientId());
		heartbeat.setAlive(new Date().getTime());
		try {
			this.sendCheckConnection(heartbeat);
		} catch (Exception e) {
			throw new CommunicationException();
		} catch (Throwable th) {
			throw new CommunicationException();
		}
	}

	/**
	 * Starts automatically sending Heartbeats to the heartbeat topic every provided
	 * number of milliseconds.
	 * 
	 * @param intervalInMilliseconds number of milliseconds between heartbeats
	 */
	public void startHeartbeats(int intervalInMilliseconds) {
		AdminHeartbeatTask task = new AdminHeartbeatTask(this);
		taskReference = heartbeatScheduler.scheduleAtFixedRate(task, 0, intervalInMilliseconds, TimeUnit.MILLISECONDS);
		logger.info("Started periodic heartbeats every " + intervalInMilliseconds + " milliseconds");
	}

	/**
	 * Stops sending of heartbeats if they are currently active. This will not
	 * interrupt the heartbeat if it is currently sending one, but instead safely
	 * stop after sending is completed.
	 */
	public void stopHeartbeats() {
		if (taskReference != null) {
			taskReference.cancel(false);
			taskReference = null;
			logger.info("Stopped periodic heartbeats");
		}
	}
	
	public void send(AdminHeartbeat heartbeat) {
		try { 
			super.send(heartbeat);
		logger.debug("Sent heartbeat: " + heartbeat);
		} catch (Exception serEx) {
			logger.error("Could not send heartbeat, because could not register Heartbeat Schema. Is the local schema different from the schema in the registry?");
		}
	}

	@Override
	protected EDXLDistribution setEDXLDEValues(EDXLDistribution standardKey) {
		standardKey.setDistributionKind(DistributionKind.Report);
		standardKey.setDistributionStatus(DistributionStatus.System);
		return standardKey;
	}

}
