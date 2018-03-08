package eu.driver.api;

import org.apache.avro.generic.IndexedRecord;

public class CallbackReceiver extends GenericAvroReceiver {
	
	private IAdaptorCallback callback = null;
	
	public CallbackReceiver(IAdaptorCallback callback) {
		this.callback = callback;
	}

	@Override
	public void receiveMessage(IndexedRecord message) {
		if (this.callback != null) {
			callback.messageReceived(message);
		}
	}
}
