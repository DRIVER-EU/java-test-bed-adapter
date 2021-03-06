package eu.driver.adapter.core.consumer;

import org.apache.avro.generic.IndexedRecord;
import org.apache.avro.specific.SpecificData;
import org.slf4j.Logger;

import eu.driver.adapter.core.CISAdapter;
import eu.driver.adapter.logger.CISLogger;
import eu.driver.api.GenericAvroReceiver;

public class TopicInviteConsumer extends GenericAvroReceiver {

	private Logger logger = CISLogger.logger(CISAdapter.class);
	
	@Override
	public void receiveMessage(IndexedRecord key, IndexedRecord receivedMessage) {
		if (receivedMessage.getSchema().getName().equalsIgnoreCase("TopicInvite")) {
			try {
				eu.driver.model.core.TopicInvite inviteMsg = (eu.driver.model.core.TopicInvite) SpecificData.get().deepCopy(eu.driver.model.core.TopicInvite.SCHEMA$, receivedMessage);
				if (inviteMsg.getId().toString().equalsIgnoreCase(CISAdapter.getInstance().getClientID())) {
					logger.info("The Adaptor received a message to join the topic: " + inviteMsg.getTopicName());
					CISAdapter.getInstance().topicInviteReceived(inviteMsg);
				}
			} catch (Exception e) {
				logger.error("Error processing the TopicInvite message received!", e);
			}
		}
	}

}
