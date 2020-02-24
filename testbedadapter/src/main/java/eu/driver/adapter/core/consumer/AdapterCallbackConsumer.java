package eu.driver.adapter.core.consumer;

import java.util.ArrayList;
import java.util.List;

import org.apache.avro.generic.IndexedRecord;

import eu.driver.api.GenericAvroReceiver;
import eu.driver.api.IAdaptorCallback;

public class AdapterCallbackConsumer extends GenericAvroReceiver {

	private List<IAdaptorCallback> callbacks = null;
	private String topicName = null;
	
	
	public AdapterCallbackConsumer(String topicName, IAdaptorCallback callback) {
		super();
		if (this.callbacks == null) {
			this.callbacks = new ArrayList<IAdaptorCallback>();
		}
		this.callbacks.add(callback);
		this.topicName = topicName;
	}
	
	public AdapterCallbackConsumer(String topicName, List<IAdaptorCallback> callbacks) {
		super();
		this.callbacks = callbacks;
		this.topicName = topicName;
	}
	
	public void setCallbacks(List<IAdaptorCallback> callbacks) {
		this.callbacks = callbacks;
	}
	
	public void clearCallbacks() {
		this.callbacks = new ArrayList<IAdaptorCallback>();
	}

	@Override
	public void receiveMessage(IndexedRecord key, IndexedRecord message) {
		if (this.callbacks != null) {
			for (IAdaptorCallback callback : callbacks) {
				callback.messageReceived(key, message, this.topicName);	
			}
		}
	}

}