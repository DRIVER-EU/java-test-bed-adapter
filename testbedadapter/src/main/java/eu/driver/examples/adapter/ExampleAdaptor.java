package eu.driver.examples.adapter;

import java.io.IOException;

import eu.driver.adapter.constants.TopicConstants;
import eu.driver.adapter.core.CISAdapter;

public class ExampleAdaptor {

	public static void main(String[] args) throws InterruptedException, IOException {
		// created the CIS Adapter that will send heartbeats, log
		CISAdapter adapter = CISAdapter.getInstance();
		
		// add a callback which is called when a message was received in the correspinding topic
		adapter.addCallback(new PrintAdapterCallback(), TopicConstants.HEARTBEAT_TOPIC);
	}

}
