package eu.driver.adapter.core.consumer;

import java.util.ArrayList;
import java.util.List;

import org.apache.avro.generic.IndexedRecord;

import eu.driver.api.GenericAvroReceiver;
import eu.driver.api.IAdaptorCallback;

public class AdapterCallbackConsumer extends GenericAvroReceiver {

	private List<IAdaptorCallback> callbacks = null;
	
	
	public AdapterCallbackConsumer(IAdaptorCallback callback) {
		super();
		if (this.callbacks == null) {
			this.callbacks = new ArrayList<IAdaptorCallback>();
		}
		this.callbacks.add(callback);
	}
	
	public AdapterCallbackConsumer(List<IAdaptorCallback> callbacks) {
		super();
		this.callbacks = callbacks;
	}
	
	public void setCallbacks(List<IAdaptorCallback> callbacks) {
		this.callbacks = callbacks;
	}

	@Override
	public void receiveMessage(IndexedRecord key, IndexedRecord message) {
		if (this.callbacks != null) {
			for (IAdaptorCallback callback : callbacks) {
				callback.messageReceived(key, message);	
			}
		}
	}

}