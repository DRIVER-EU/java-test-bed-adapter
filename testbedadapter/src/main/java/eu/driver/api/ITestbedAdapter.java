package eu.driver.api;

import java.util.List;

import org.apache.avro.generic.IndexedRecord;

public interface ITestbedAdapter {
	
	public void sendAvro(String topic, IndexedRecord data);
	
	public void sendText(String topic, String text, CISStandard standard);
	
	public void addReceiver(String topic, ITestbedReceiver receiver);
	
	public void removeReceiver(ITestbedReceiver receiver);
	
	public List<Object> pollMessages();
	
	public List<Object> pollMessages(String topic);

}
