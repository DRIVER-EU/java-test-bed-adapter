package eu.driver.api;

import org.apache.avro.generic.IndexedRecord;

public class CallbackReceiver extends GenericAvroReceiver {
	
	private IAdaptorCallback callback = null;
	private String topicName = null;
	
	public CallbackReceiver(String topicName, IAdaptorCallback callback) {
		this.callback = callback;
		this.topicName = topicName;
	}

	@Override
	public void receiveMessage(IndexedRecord key, IndexedRecord message) {
		if (this.callback != null) {
			callback.messageReceived(key, message, this.topicName);
		}
	}
}
