package eu.driver.gateway.geojson;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;

import com.xvrsim.model.entity.Item;
import com.xvrsim.model.entity.item.EnvironmentLabel;
import com.xvrsim.model.entity.item.IncidentLabel;
import com.xvrsim.model.entity.item.ObjectType;
import com.xvrsim.model.entity.item.PersonType;
import com.xvrsim.model.entity.item.RescueLabel;
import com.xvrsim.model.entity.item.VehicleType;

import eu.driver.adapter.core.producer.GenericProducer;
import eu.driver.adapter.logger.CISLogger;
import eu.driver.api.IAvroReceiver;
import eu.driver.gateway.GatewayProperties;
import eu.driver.model.geojson.Feature;
import eu.driver.model.geojson.FeatureCollection;
import eu.driver.model.geojson.Point;
import eu.driver.model.geojson.PointType;
import eu.driver.model.geojson.XVRItemProperties;

public class XVRItemToGeoJSONConverter implements IAvroReceiver<Item> {

	private GenericProducer outputProducer;

	private Map<CharSequence, Item> items;
	private ScheduledExecutorService reportingScheduler;

	private static Logger logger = CISLogger.logger(XVRItemToGeoJSONConverter.class);

	public XVRItemToGeoJSONConverter(GenericProducer producer) {
		outputProducer = producer;
		items = new ConcurrentHashMap<>();
		reportingScheduler = Executors.newScheduledThreadPool(1);
		long freq = Long.parseLong(GatewayProperties.getInstance().getProperty(GatewayProperties.OUTPUT_FREQUENCY));
		reportingScheduler.scheduleAtFixedRate(new ReportingTask(), 0, freq, TimeUnit.MILLISECONDS);
	}

	public void receiveMessage(Item message) {
		items.put(message.getGuid(), message);
	}

	private class ReportingTask implements Runnable {
		@Override
		public void run() {
			if (items.size() > 0) {
				FeatureCollection.Builder builder = FeatureCollection.newBuilder();
				List<Feature> features = new ArrayList<>();

				for (Item item : items.values()) {

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

					xvrItemBuilder = setItemType(xvrItemBuilder, item);

					featureBuilder.setProperties(xvrItemBuilder.build());

					featureBuilder.build();

					features.add(featureBuilder.build());
				}
				
				builder.setFeatures(features);
				FeatureCollection fc = builder.build();
				outputProducer.send(fc);
				logger.info("Reported " + features.size() + " XVR Items as GeoJSON Features");
				items.clear();
			}
		}

		private XVRItemProperties.Builder setItemType(XVRItemProperties.Builder properties, Item item) {
			Object type = item.getItemType();
			if (type instanceof ObjectType) {
				ObjectType ot = (ObjectType) type;
				properties.setType(ot.getClass().getSimpleName());
				properties.setSubType(ot.getSubType().name());
			}
			if (type instanceof VehicleType) {
				VehicleType ot = (VehicleType) type;
				properties.setType(ot.getClass().getSimpleName());
				properties.setSubType(ot.getSubType().name());
			}
			if (type instanceof PersonType) {
				PersonType ot = (PersonType) type;
				properties.setType(ot.getClass().getSimpleName());
				properties.setSubType(ot.getGender().name());
			}
			Object label = item.getScenarioLabel();
			if (label instanceof EnvironmentLabel) {
				EnvironmentLabel el = (EnvironmentLabel) label;
				properties.setLabel(el.getClass().getSimpleName());
				properties.setSubLabel(el.getSubLabel().name());
			}
			if (label instanceof IncidentLabel) {
				IncidentLabel el = (IncidentLabel) label;
				properties.setLabel(el.getClass().getSimpleName());
				properties.setSubLabel(el.getSubLabel().name());
			}
			if (label instanceof RescueLabel) {
				RescueLabel el = (RescueLabel) label;
				properties.setLabel(el.getClass().getSimpleName());
				properties.setSubLabel(el.getSubLabel().name());
			}
			return properties;
		}
	}
}