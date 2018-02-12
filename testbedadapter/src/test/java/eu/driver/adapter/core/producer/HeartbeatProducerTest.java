package eu.driver.adapter.core.producer;

import java.util.Date;

import org.apache.avro.generic.IndexedRecord;
import org.apache.kafka.clients.producer.MockProducer;

import eu.driver.adapter.time.ISO8601TimestampProvider;
import eu.driver.adapter.time.ITimestampProvider;
import eu.driver.model.core.Heartbeat;
import eu.driver.model.edxl.EDXLDistribution;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class HeartbeatProducerTest extends TestCase {
	
	public HeartbeatProducerTest(String testName) {
		super(testName);
	}

	/**
	 * @return the suite of tests being tested
	 */
	public static Test suite() {
		return new TestSuite(HeartbeatProducerTest.class);
	}

	public void testHeartbeatIntervals() throws InterruptedException {
		MockProducer<EDXLDistribution, IndexedRecord> prod = new MockProducer<>();
		HeartbeatProducer producer = new HeartbeatProducer(prod);
		producer.startHeartbeats(50);
		Thread.sleep(500); // for some reason it takes ~250ms before the scheduled executor becomes 'stable'
		producer.stopHeartbeats();
		
		Heartbeat last = (Heartbeat) prod.history().get(prod.history().size()-1).value();
		Heartbeat secondLast = (Heartbeat) prod.history().get(prod.history().size()-2).value();
		
		ITimestampProvider timeProvider = new ISO8601TimestampProvider();
		Date lastDate = timeProvider.parseDate(last.getAlive().toString()).get();
		Date secondLastDate = timeProvider.parseDate(secondLast.getAlive().toString()).get();
		
		assertEquals("Time between updates should be 50 ms apart (allowed error of 5 ms)", 50, lastDate.getTime() - secondLastDate.getTime(), 5);
		assertEquals("Should have sent 10 heartbeats in 500 ms with 50 ms update rate", 10, prod.history().size());
	}
}
