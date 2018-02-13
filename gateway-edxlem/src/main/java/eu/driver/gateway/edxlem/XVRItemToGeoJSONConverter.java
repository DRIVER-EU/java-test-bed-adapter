package eu.driver.gateway.edxlem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.xvrsim.model.entity.Item;

import eu.driver.adapter.core.producer.GenericProducer;
import eu.driver.api.IAvroReceiver;
import eu.driver.model.geojson.Feature;
import eu.driver.model.geojson.FeatureCollection;
import eu.driver.model.geojson.Point;
import eu.driver.model.geojson.PointType;
import eu.driver.model.geojson.XVRItemProperties;

public class XVRItemToGeoJSONConverter implements IAvroReceiver<Item> {
	
	private GenericProducer outputProducer;
	
	public XVRItemToGeoJSONConverter(GenericProducer producer) {
		outputProducer = producer;
	}

	@Override
	public void receiveMessage(Item message) {
		FeatureCollection fc = itemToFeatureCollection(message);
		System.out.println(fc);
		outputProducer.send(fc);
	}
	
	private FeatureCollection itemToFeatureCollection(Item item) {
		FeatureCollection.Builder builder = FeatureCollection.newBuilder();
		
		Feature.Builder featureBuilder = Feature.newBuilder();
		
		List<Double> lonLatAlt = new ArrayList<>(3);
		lonLatAlt.add(item.getLocation().getLongitude());
		lonLatAlt.add(item.getLocation().getLatitude());
		lonLatAlt.add(item.getLocation().getAltitude());
		
		featureBuilder.setGeometry(new Point(PointType.Point, lonLatAlt));
		
		XVRItemProperties.Builder xvrItemBuilder = XVRItemProperties.newBuilder();
		xvrItemBuilder.setGuid(item.getGuid());
		xvrItemBuilder.setYaw(item.getOrientation().getYaw());
		xvrItemBuilder.setPitch(item.getOrientation().getPitch());
		xvrItemBuilder.setRoll(item.getOrientation().getRoll());
		xvrItemBuilder.setSpeed(item.getVelocity().getMagnitude());
		
		featureBuilder.setProperties(xvrItemBuilder.build());
		
		featureBuilder.build();
		List<Feature> features = new ArrayList<>();
		features.add(featureBuilder.build());
		builder.setFeatures(features);
		return builder.build();
	}

}
