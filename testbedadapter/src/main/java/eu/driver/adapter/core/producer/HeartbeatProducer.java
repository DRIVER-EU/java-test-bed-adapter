package eu.driver.adapter.core.producer;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import org.apache.avro.generic.IndexedRecord;
import org.apache.kafka.clients.producer.Producer;

import eu.driver.adapter.properties.ClientProperties;
import eu.driver.model.core.Heartbeat;
import eu.driver.model.core.HeartbeatKey;

/**
 * Producer for the CIS Adapter Core Heartbeat messages. The heartbeat topic and
 * the Heartbeat message key and value type are fixed to comply with the CIS
 * requirements. Schedules sending a Heartbeat periodically (Default: 5
 * seconds).
 * 
 * @author hameetepa
 *
 */
public class HeartbeatProducer extends AbstractProducer {

	private ScheduledExecutorService heartbeatScheduler;
	private ScheduledFuture<?> taskReference = null;

	public HeartbeatProducer(Producer<IndexedRecord, IndexedRecord> producer) {
		super(producer, ClientProperties.getInstance().getProperty(ClientProperties.HEARTBEAT_TOPIC));
		heartbeatScheduler = Executors.newScheduledThreadPool(1);
	}

	/**
	 * Starts automatically sending Heartbeats to the heartbeat topic every provided
	 * number of milliseconds.
	 * 
	 * @param intervalInMilliseconds number of milliseconds between heartbeats
	 */
	public void startHeartbeats(int intervalInMilliseconds) {
		HeartbeatTask task = new HeartbeatTask(this);
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

	@Override
	protected HeartbeatKey createKey() {
		HeartbeatKey key = new HeartbeatKey();
		key.setId(getClientId());
		return key;
	}
	
	public void send(Heartbeat heartbeat) {
		super.send(heartbeat);
		logger.debug("Sent heartbeat: " + heartbeat);
	}

}
