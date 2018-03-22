package eu.driver.adapter.core.producer.admin;

import org.apache.avro.generic.IndexedRecord;
import org.apache.kafka.clients.producer.Producer;
import org.slf4j.Logger;

import eu.driver.adapter.constants.TopicConstants;
import eu.driver.adapter.core.CISAdapter;
import eu.driver.adapter.core.producer.AbstractEDXLDEProducer;
import eu.driver.adapter.logger.CISLogger;
import eu.driver.model.edxl.EDXLDistribution;
import eu.driver.model.core.TopicInvite;

public class TopicInviteProducer  extends AbstractEDXLDEProducer {
	
	private Logger logger = CISLogger.logger(CISAdapter.class);

	public TopicInviteProducer(Producer<EDXLDistribution, IndexedRecord> producer) throws Exception {
		super(producer, TopicConstants.TOPIC_INVITE_TOPIC);
		logger.info("TopicInviteProducer");
	}
	
	public void sendInviteMessage(String topicName, String clientId, Boolean publishAllowed, Boolean subscribeAllowed) {
		logger.info("-->sendInviteMessage");
		
		TopicInvite inviteMsg = new TopicInvite();
		inviteMsg.setTopicName(topicName);
		inviteMsg.setId(clientId);
		inviteMsg.setPublishAllowed(publishAllowed);
		inviteMsg.setSubscribeAllowed(subscribeAllowed);
		
		this.send(inviteMsg);
		logger.info("sendInviteMessage-->");
	}

	@Override
	protected EDXLDistribution setEDXLDEValues(EDXLDistribution standardKey) {
		return standardKey;
	}
}
