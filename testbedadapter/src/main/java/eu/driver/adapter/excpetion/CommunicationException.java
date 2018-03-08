package eu.driver.adapter.excpetion;

public class CommunicationException extends Exception {

	private static final long serialVersionUID = -6159544601291668113L;

	public CommunicationException() {
		super();
	}
	
	public CommunicationException(String message) {
		super(message);
	}
	
	public CommunicationException(String message, Exception e) {
		super(message, e);
	}
}
