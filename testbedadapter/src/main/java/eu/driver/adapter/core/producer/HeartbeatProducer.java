package eu.driver.adapter.core.producer;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import org.apache.avro.generic.IndexedRecord;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.common.errors.SerializationException;

import eu.driver.adapter.constants.TopicConstants;
import eu.driver.adapter.core.CISAdapter;
import eu.driver.adapter.excpetion.CommunicationException;
import eu.driver.adapter.logger.CISLogger;
import eu.driver.adapter.properties.ClientProperties;
import eu.driver.adapter.time.ISO8601TimestampProvider;
import eu.driver.adapter.time.ITimestampProvider;
import eu.driver.model.core.Heartbeat;
import eu.driver.model.edxl.DistributionKind;
import eu.driver.model.edxl.DistributionStatus;
import eu.driver.model.edxl.EDXLDistribution;
import io.confluent.kafka.schemaregistry.client.rest.exceptions.RestClientException;

/**
 * Producer for the CIS Adapter Core Heartbeat messages. The heartbeat topic and
 * the Heartbeat message key and value type are fixed to comply with the CIS
 * requirements. Schedules sending a Heartbeat periodically (Default: 5
 * seconds).
 * 
 * @author hameetepa
 *
 */
public class HeartbeatProducer extends AbstractEDXLDEProducer {

	private CISLogger logger = new CISLogger(HeartbeatProducer.class);
	
	private ScheduledExecutorService heartbeatScheduler;
	private ScheduledFuture<?> taskReference = null;
	
	private String origin = "";
	

	public HeartbeatProducer(Producer<EDXLDistribution, IndexedRecord> producer, String topicName) throws Exception {
		super(producer, topicName);
		heartbeatScheduler = Executors.newScheduledThreadPool(1);
		
		InetAddress ip;
        try {
            ip = InetAddress.getLocalHost();
            if (ip != null && ip.getHostName() != null) {
            	origin += ip.getHostName();
            }
            if (ip != null) {
            	if (origin.length() > 0) {
            		origin += "/";
            	}
            	origin += ip.getHostAddress();
            }
        } catch (UnknownHostException e) {
        	logger.error("Error optaining IP and Hostname!");
        }
	}
	
	public void sendInitialHeartbeat() throws CommunicationException {
		Heartbeat heartbeat = new Heartbeat();
		heartbeat.setId(this.getClientId());
		heartbeat.setAlive(new Date().getTime());
		heartbeat.setOrigin(this.origin);
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
		HeartbeatTask task = new HeartbeatTask(this, this.origin);
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
	
	public void send(Heartbeat heartbeat) {
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
