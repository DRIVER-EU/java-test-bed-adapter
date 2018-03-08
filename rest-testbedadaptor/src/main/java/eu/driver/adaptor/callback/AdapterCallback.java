package eu.driver.adaptor.callback;

import org.apache.avro.generic.IndexedRecord;

import eu.driver.adaptor.ws.CallbackController;
import eu.driver.api.IAdaptorCallback;

public class AdapterCallback implements IAdaptorCallback {

	@Override
	public void messageReceived(IndexedRecord receivedMessage) {
		CallbackController.getInstance().sendMessage(receivedMessage.toString());

	}

}
