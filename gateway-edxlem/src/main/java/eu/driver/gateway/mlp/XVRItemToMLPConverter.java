package eu.driver.gateway.mlp;

import com.xvrsim.model.entity.Item;

import eu.driver.adapter.core.producer.GenericProducer;
import eu.driver.api.IAvroReceiver;
import eu.driver.model.mlp.Alt;
import eu.driver.model.mlp.AttrEnc;
import eu.driver.model.mlp.AttrType;
import eu.driver.model.mlp.Coord;
import eu.driver.model.mlp.Msid;
import eu.driver.model.mlp.Pd;
import eu.driver.model.mlp.Point;
import eu.driver.model.mlp.Pos;
import eu.driver.model.mlp.SlRep;
import eu.driver.model.mlp.Time;
import eu.driver.position.PositionParser;

public class XVRItemToMLPConverter implements IAvroReceiver<Item> {
	
	private GenericProducer outputProducer;
	
	public XVRItemToMLPConverter(GenericProducer producer) {
		outputProducer = producer;
	}

	public void receiveMessage(Item message) {
		SlRep locationReport = itemToStandardLocationReport(message);
		System.out.println("Converted to MLP: " + locationReport);
	}
	
	private SlRep itemToStandardLocationReport(Item item) {
		SlRep.Builder builder = SlRep.newBuilder();
		Pos.Builder posBuilder = Pos.newBuilder();
		
		posBuilder.setMsid(new Msid(item.getGuid(), AttrType.OPE_ID, AttrEnc.ASC)); // ASCI encoded operator specific entity
		
		Pd.Builder pdBuilder = Pd.newBuilder();
		
		int altitude = (int) Math.round(item.getLocation().getAltitude());
		double latRads = Math.toRadians(item.getLocation().getLatitude());
		double lonRads = Math.toRadians(item.getLocation().getLongitude());
		String latDMS = PositionParser.convertLatRadToDMS(latRads);
		String lonDMS = PositionParser.convertLonRadToDMS(lonRads);
		pdBuilder.setAlt(new Alt(altitude));
		
		Point p = new Point();
		Coord c = new Coord();
		c.setX(latDMS);
		c.setY(lonDMS);
		p.setCoord(c);
		pdBuilder.setShape(p);
		
		int yaw = (int) Math.round(item.getVelocity().getYaw());
		pdBuilder.setDirection(yaw);
		
		int speed = (int) Math.round(item.getVelocity().getMagnitude());
		pdBuilder.setSpeed(speed);
		
		pdBuilder.setTime(new Time(System.currentTimeMillis(), "0000"));
		
		posBuilder.setPd(pdBuilder.build());
		
		builder.setPos(posBuilder.build());
		return builder.build();
	}

}
