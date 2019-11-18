package eu.driver.adapter.core.consumer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import org.apache.avro.generic.IndexedRecord;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;

import eu.driver.adapter.constants.AdapterMode;
import eu.driver.adapter.constants.TopicConstants;
import eu.driver.adapter.core.CISAdapter;
import eu.driver.api.IAvroReceiver;

public abstract class CallbackConsumer<Key extends IndexedRecord, Value extends IndexedRecord> extends AbstractConsumer<Key, Value> {
	
	private Collection<IAvroReceiver<Key,Value>> receivers;
	
	private boolean run = true;

	public CallbackConsumer(Consumer<Key, Value> consumer, String topic) {
		super(consumer, topic);
		receivers = Collections.synchronizedCollection(new ArrayList<>());
	}

	@Override
	public void run() {
		try {
			consumer.subscribe(Collections.singletonList(getTopic()));
			logger.debug("Callback Consumer thread started for topic: " + getTopic());
			while (this.run) {
				ConsumerRecords<Key, Value> records = consumer.poll(1000);
				for (ConsumerRecord<Key, Value> record : records) {
					Key key = record.key();
					Value message = record.value();
					sendMessageToReceivers(key, message);
				}
			}
			consumer.unsubscribe();
		} catch (Exception e) {
			logger.error("Error in the Callback Consumer for topic: " + getTopic() + ": " + e.getMessage());
			if (getTopic().equalsIgnoreCase(TopicConstants.ADMIN_HEARTBEAT_TOPIC)) {
				// got an exception on the AdminHeartbeatTopic, seems to be in SEC_TRIAL_MODE
				if (CISAdapter.getInstance().getAdapterMode().equals(AdapterMode.TRIAL_MODE)) {
					CISAdapter.getInstance().reestablishAdminHeartbeatConsumer();
				}
			}
		}
		logger.debug("Callback Consumer thread stopped for topic: " + getTopic());
	}
	
	public void stop(){
		this.run = false;
	}
	
	public void addReceiver(IAvroReceiver<Key,Value> receiver) {
		receivers.add(receiver);
	}
	
	private void sendMessageToReceivers(Key key, Value message) {
		receivers.forEach(r -> r.receiveMessage(key, message));
	}

}
