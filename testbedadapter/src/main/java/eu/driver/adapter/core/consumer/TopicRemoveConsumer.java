package eu.driver.adapter.core.consumer;

import org.apache.avro.generic.IndexedRecord;
import org.apache.avro.specific.SpecificData;
import org.slf4j.Logger;

import eu.driver.adapter.core.CISAdapter;
import eu.driver.adapter.logger.CISLogger;
import eu.driver.api.GenericAvroReceiver;

public class TopicRemoveConsumer extends GenericAvroReceiver {

	private Logger logger = CISLogger.logger(CISAdapter.class);
	
	@Override
	public void receiveMessage(IndexedRecord key, IndexedRecord receivedMessage) {
		if (receivedMessage.getSchema().getName().equalsIgnoreCase("TopicRemove")) {
			try {
				eu.driver.model.core.TopicRemove removeMsg = (eu.driver.model.core.TopicRemove) SpecificData.get().deepCopy(eu.driver.model.core.TopicRemove.SCHEMA$, receivedMessage);
				if (removeMsg.getId().toString().equalsIgnoreCase(CISAdapter.getInstance().getClientID())) {
					logger.info("The Adaptor received a message to remove all publisher/subsriber from Topic: " + removeMsg.getTopicName());
					CISAdapter.getInstance().topicRemoveReceived(removeMsg);
				}
			} catch (Exception e) {
				logger.error("Error processing the TopicRemove message received!", e);
			}
		}
	}

}
