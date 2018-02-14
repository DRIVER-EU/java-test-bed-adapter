package eu.driver.adaptor.ws;

import java.util.Date;
import java.util.Timer;

import org.apache.log4j.Logger;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import eu.driver.adaptor.ws.heartbeat.HBTimerTask;
import eu.driver.adaptor.ws.mapper.StringJSONMapper;
import eu.driver.adaptor.ws.object.WSHeartbeatResponse;


public class WebSocketServer extends TextWebSocketHandler {
	
	private Logger log = Logger.getLogger(this.getClass());
	private Timer timer = null;
	private StringJSONMapper mapper = new StringJSONMapper();
	
	private Date lastHBReceived = null;
	
	public WebSocketServer() {

	}
	 
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
    	log.info("The WebSocket has been closed!");
    	this.timer.cancel();
    	this.timer = null;
    }
 
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
    	log.info("The WebSocket has been opened!");
    	if (this.timer == null) {
    		this.timer = new Timer();
    	}
    	this.timer.schedule(new HBTimerTask(session), 5000, 5000);
    }
 
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage textMessage) throws Exception {
    	log.info("Message received: " + textMessage.getPayload());
    	
    	String message = textMessage.getPayload();
    	if (message.indexOf("eu.driver.adaptor.ws.response.heartbeat") != -1) {
    		WSHeartbeatResponse hbResponse = mapper.stringToHBMessage(textMessage.getPayload());
    		this.lastHBReceived = hbResponse.getSendTime();
    	}
    }
}
