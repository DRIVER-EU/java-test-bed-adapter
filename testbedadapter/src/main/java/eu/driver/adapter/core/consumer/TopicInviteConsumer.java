package eu.driver.adapter.core.consumer;

import org.apache.avro.generic.IndexedRecord;
import org.slf4j.Logger;

import eu.driver.adapter.core.CISAdapter;
import eu.driver.adapter.logger.CISLogger;
import eu.driver.api.GenericAvroReceiver;
import eu.driver.model.system.TopicInvite;

public class TopicInviteConsumer extends GenericAvroReceiver {

	private Logger logger = CISLogger.logger(CISAdapter.class);
	
	@Override
	public void receiveMessage(IndexedRecord message) {
		if (message instanceof TopicInvite) {
			TopicInvite inviteMsg = (TopicInvite)message;
			logger.info("The Adaptor received a message to join the topic: " + inviteMsg.getTopicName());
			CISAdapter.getInstance().topicInviteReceived(inviteMsg);
		}
	}

}
