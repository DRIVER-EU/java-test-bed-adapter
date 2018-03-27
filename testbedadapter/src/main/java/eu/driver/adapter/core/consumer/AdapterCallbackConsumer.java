package eu.driver.adapter.core.consumer;

import org.apache.avro.generic.IndexedRecord;

import eu.driver.api.GenericAvroReceiver;
import eu.driver.api.IAdaptorCallback;

public class AdapterCallbackConsumer extends GenericAvroReceiver {

	private IAdaptorCallback callback = null;
	
	public AdapterCallbackConsumer(IAdaptorCallback callback) {
		super();
		this.callback = callback;
	}
	
	@Override
	public void receiveMessage(IndexedRecord key, IndexedRecord message) {
		if (this.callback != null) {
			this.callback.messageReceived(key, message);
		}
	}

}