/**
 * Autogenerated by Avro
 *
 * DO NOT EDIT DIRECTLY
 */
package eu.driver.model.geojson.sim;

import org.apache.avro.specific.SpecificData;
import org.apache.avro.message.BinaryMessageEncoder;
import org.apache.avro.message.BinaryMessageDecoder;
import org.apache.avro.message.SchemaStore;

@SuppressWarnings("all")
/** A GeoJSON FeatureCollection object. The properties are made specific to be filled in with information from Simulated Entity data. */
@org.apache.avro.specific.AvroGenerated
public class FeatureCollection extends org.apache.avro.specific.SpecificRecordBase implements org.apache.avro.specific.SpecificRecord {
  private static final long serialVersionUID = 4388751758175373250L;
  public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"record\",\"name\":\"FeatureCollection\",\"namespace\":\"eu.driver.model.geojson.sim\",\"doc\":\"A GeoJSON FeatureCollection object. The properties are made specific to be filled in with information from Simulated Entity data.\",\"fields\":[{\"name\":\"type\",\"type\":{\"type\":\"enum\",\"name\":\"FeatureCollectionType\",\"symbols\":[\"FeatureCollection\"]},\"default\":\"FeatureCollection\"},{\"name\":\"bbox\",\"type\":[\"null\",{\"type\":\"array\",\"items\":\"double\"}],\"default\":null},{\"name\":\"features\",\"type\":[\"null\",{\"type\":\"array\",\"items\":{\"type\":\"record\",\"name\":\"Feature\",\"doc\":\"A GeoJSON Feature object\",\"fields\":[{\"name\":\"type\",\"type\":{\"type\":\"enum\",\"name\":\"FeatureType\",\"symbols\":[\"Feature\"]},\"default\":\"Feature\"},{\"name\":\"bbox\",\"type\":[\"null\",{\"type\":\"array\",\"items\":\"double\"}],\"default\":null},{\"name\":\"geometry\",\"type\":[{\"type\":\"record\",\"name\":\"Point\",\"doc\":\"Describes a point geometry\",\"fields\":[{\"name\":\"type\",\"type\":{\"type\":\"enum\",\"name\":\"PointType\",\"symbols\":[\"Point\"]},\"default\":\"Point\"},{\"name\":\"coordinates\",\"type\":{\"type\":\"array\",\"items\":\"double\"}}]},{\"type\":\"record\",\"name\":\"LineString\",\"doc\":\"Describes a LineString geometry\",\"fields\":[{\"name\":\"type\",\"type\":{\"type\":\"enum\",\"name\":\"LineStringType\",\"symbols\":[\"LineString\"]},\"default\":\"LineString\"},{\"name\":\"coordinates\",\"type\":{\"type\":\"array\",\"items\":{\"type\":\"array\",\"items\":\"double\"}}}]},{\"type\":\"record\",\"name\":\"MultiLineString\",\"doc\":\"Describes a MultiLineString geometry\",\"fields\":[{\"name\":\"type\",\"type\":{\"type\":\"enum\",\"name\":\"MultiLineStringType\",\"symbols\":[\"MultiLineString\"]},\"default\":\"MultiLineString\"},{\"name\":\"coordinates\",\"type\":{\"type\":\"array\",\"items\":{\"type\":\"array\",\"items\":{\"type\":\"array\",\"items\":\"double\"}}}}]},{\"type\":\"record\",\"name\":\"Polygon\",\"doc\":\"Describes a Polygon geometry\",\"fields\":[{\"name\":\"type\",\"type\":{\"type\":\"enum\",\"name\":\"PolygonType\",\"symbols\":[\"Polygon\"]},\"default\":\"Polygon\"},{\"name\":\"coordinates\",\"type\":{\"type\":\"array\",\"items\":{\"type\":\"array\",\"items\":{\"type\":\"array\",\"items\":\"double\"}}}}]},{\"type\":\"record\",\"name\":\"MultiPolygon\",\"doc\":\"Describes a MultiPolygon geometry\",\"fields\":[{\"name\":\"type\",\"type\":{\"type\":\"enum\",\"name\":\"MultiPolygonType\",\"symbols\":[\"MultiPolygon\"]},\"default\":\"MultiPolygon\"},{\"name\":\"coordinates\",\"type\":{\"type\":\"array\",\"items\":{\"type\":\"array\",\"items\":{\"type\":\"array\",\"items\":{\"type\":\"array\",\"items\":\"double\"}}}}}]}]},{\"name\":\"properties\",\"type\":{\"type\":\"record\",\"name\":\"SimulatedEntityProperties\",\"doc\":\"Specfic properties for an Simulated entity\",\"fields\":[{\"name\":\"guid\",\"type\":\"string\",\"doc\":\"globally unique identifier for this entity\"},{\"name\":\"name\",\"type\":\"string\",\"doc\":\"name of this entity\"},{\"name\":\"speed\",\"type\":[\"null\",\"double\"],\"doc\":\"speed of the entity in m/s\",\"default\":null},{\"name\":\"type\",\"type\":{\"type\":\"enum\",\"name\":\"TypeEnum\",\"symbols\":[\"OBJECT\",\"PERSON\",\"CAR\",\"VAN\",\"TRUCK\",\"BOAT\",\"PLANE\",\"HELICOPTER\",\"MOTORCYCLE\",\"DRONE\",\"UNIT\",\"STATION\",\"UNITGROUP\",\"UNKNOWN\"]}},{\"name\":\"label\",\"type\":\"string\",\"doc\":\"Label that describes the domain of the entity. E.g. Police, Medical, Fire or Military.\"},{\"name\":\"subEntities\",\"type\":[\"null\",{\"type\":\"array\",\"items\":\"string\"}],\"doc\":\"Entities contained by this entity. Only used for Units, Stations and Unit Groups. Array of strings consists of guids.\",\"default\":null}]},\"doc\":\"Properties that provide additional specification of the Simulated entity in addition to its geographic information.\"}]}}]}]}");
  public static org.apache.avro.Schema getClassSchema() { return SCHEMA$; }

  private static SpecificData MODEL$ = new SpecificData();

  private static final BinaryMessageEncoder<FeatureCollection> ENCODER =
      new BinaryMessageEncoder<FeatureCollection>(MODEL$, SCHEMA$);

  private static final BinaryMessageDecoder<FeatureCollection> DECODER =
      new BinaryMessageDecoder<FeatureCollection>(MODEL$, SCHEMA$);

  /**
   * Return the BinaryMessageDecoder instance used by this class.
   */
  public static BinaryMessageDecoder<FeatureCollection> getDecoder() {
    return DECODER;
  }

  /**
   * Create a new BinaryMessageDecoder instance for this class that uses the specified {@link SchemaStore}.
   * @param resolver a {@link SchemaStore} used to find schemas by fingerprint
   */
  public static BinaryMessageDecoder<FeatureCollection> createDecoder(SchemaStore resolver) {
    return new BinaryMessageDecoder<FeatureCollection>(MODEL$, SCHEMA$, resolver);
  }

  /** Serializes this FeatureCollection to a ByteBuffer. */
  public java.nio.ByteBuffer toByteBuffer() throws java.io.IOException {
    return ENCODER.encode(this);
  }

  /** Deserializes a FeatureCollection from a ByteBuffer. */
  public static FeatureCollection fromByteBuffer(
      java.nio.ByteBuffer b) throws java.io.IOException {
    return DECODER.decode(b);
  }

  @Deprecated public eu.driver.model.geojson.sim.FeatureCollectionType type;
  @Deprecated public java.util.List<java.lang.Double> bbox;
  @Deprecated public java.util.List<eu.driver.model.geojson.sim.Feature> features;

  /**
   * Default constructor.  Note that this does not initialize fields
   * to their default values from the schema.  If that is desired then
   * one should use <code>newBuilder()</code>.
   */
  public FeatureCollection() {}

  /**
   * All-args constructor.
   * @param type The new value for type
   * @param bbox The new value for bbox
   * @param features The new value for features
   */
  public FeatureCollection(eu.driver.model.geojson.sim.FeatureCollectionType type, java.util.List<java.lang.Double> bbox, java.util.List<eu.driver.model.geojson.sim.Feature> features) {
    this.type = type;
    this.bbox = bbox;
    this.features = features;
  }

  public org.apache.avro.Schema getSchema() { return SCHEMA$; }
  // Used by DatumWriter.  Applications should not call.
  public java.lang.Object get(int field$) {
    switch (field$) {
    case 0: return type;
    case 1: return bbox;
    case 2: return features;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }

  // Used by DatumReader.  Applications should not call.
  @SuppressWarnings(value="unchecked")
  public void put(int field$, java.lang.Object value$) {
    switch (field$) {
    case 0: type = (eu.driver.model.geojson.sim.FeatureCollectionType)value$; break;
    case 1: bbox = (java.util.List<java.lang.Double>)value$; break;
    case 2: features = (java.util.List<eu.driver.model.geojson.sim.Feature>)value$; break;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }

  /**
   * Gets the value of the 'type' field.
   * @return The value of the 'type' field.
   */
  public eu.driver.model.geojson.sim.FeatureCollectionType getType() {
    return type;
  }

  /**
   * Sets the value of the 'type' field.
   * @param value the value to set.
   */
  public void setType(eu.driver.model.geojson.sim.FeatureCollectionType value) {
    this.type = value;
  }

  /**
   * Gets the value of the 'bbox' field.
   * @return The value of the 'bbox' field.
   */
  public java.util.List<java.lang.Double> getBbox() {
    return bbox;
  }

  /**
   * Sets the value of the 'bbox' field.
   * @param value the value to set.
   */
  public void setBbox(java.util.List<java.lang.Double> value) {
    this.bbox = value;
  }

  /**
   * Gets the value of the 'features' field.
   * @return The value of the 'features' field.
   */
  public java.util.List<eu.driver.model.geojson.sim.Feature> getFeatures() {
    return features;
  }

  /**
   * Sets the value of the 'features' field.
   * @param value the value to set.
   */
  public void setFeatures(java.util.List<eu.driver.model.geojson.sim.Feature> value) {
    this.features = value;
  }

  /**
   * Creates a new FeatureCollection RecordBuilder.
   * @return A new FeatureCollection RecordBuilder
   */
  public static eu.driver.model.geojson.sim.FeatureCollection.Builder newBuilder() {
    return new eu.driver.model.geojson.sim.FeatureCollection.Builder();
  }

  /**
   * Creates a new FeatureCollection RecordBuilder by copying an existing Builder.
   * @param other The existing builder to copy.
   * @return A new FeatureCollection RecordBuilder
   */
  public static eu.driver.model.geojson.sim.FeatureCollection.Builder newBuilder(eu.driver.model.geojson.sim.FeatureCollection.Builder other) {
    return new eu.driver.model.geojson.sim.FeatureCollection.Builder(other);
  }

  /**
   * Creates a new FeatureCollection RecordBuilder by copying an existing FeatureCollection instance.
   * @param other The existing instance to copy.
   * @return A new FeatureCollection RecordBuilder
   */
  public static eu.driver.model.geojson.sim.FeatureCollection.Builder newBuilder(eu.driver.model.geojson.sim.FeatureCollection other) {
    return new eu.driver.model.geojson.sim.FeatureCollection.Builder(other);
  }

  /**
   * RecordBuilder for FeatureCollection instances.
   */
  public static class Builder extends org.apache.avro.specific.SpecificRecordBuilderBase<FeatureCollection>
    implements org.apache.avro.data.RecordBuilder<FeatureCollection> {

    private eu.driver.model.geojson.sim.FeatureCollectionType type;
    private java.util.List<java.lang.Double> bbox;
    private java.util.List<eu.driver.model.geojson.sim.Feature> features;

    /** Creates a new Builder */
    private Builder() {
      super(SCHEMA$);
    }

    /**
     * Creates a Builder by copying an existing Builder.
     * @param other The existing Builder to copy.
     */
    private Builder(eu.driver.model.geojson.sim.FeatureCollection.Builder other) {
      super(other);
      if (isValidValue(fields()[0], other.type)) {
        this.type = data().deepCopy(fields()[0].schema(), other.type);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.bbox)) {
        this.bbox = data().deepCopy(fields()[1].schema(), other.bbox);
        fieldSetFlags()[1] = true;
      }
      if (isValidValue(fields()[2], other.features)) {
        this.features = data().deepCopy(fields()[2].schema(), other.features);
        fieldSetFlags()[2] = true;
      }
    }

    /**
     * Creates a Builder by copying an existing FeatureCollection instance
     * @param other The existing instance to copy.
     */
    private Builder(eu.driver.model.geojson.sim.FeatureCollection other) {
            super(SCHEMA$);
      if (isValidValue(fields()[0], other.type)) {
        this.type = data().deepCopy(fields()[0].schema(), other.type);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.bbox)) {
        this.bbox = data().deepCopy(fields()[1].schema(), other.bbox);
        fieldSetFlags()[1] = true;
      }
      if (isValidValue(fields()[2], other.features)) {
        this.features = data().deepCopy(fields()[2].schema(), other.features);
        fieldSetFlags()[2] = true;
      }
    }

    /**
      * Gets the value of the 'type' field.
      * @return The value.
      */
    public eu.driver.model.geojson.sim.FeatureCollectionType getType() {
      return type;
    }

    /**
      * Sets the value of the 'type' field.
      * @param value The value of 'type'.
      * @return This builder.
      */
    public eu.driver.model.geojson.sim.FeatureCollection.Builder setType(eu.driver.model.geojson.sim.FeatureCollectionType value) {
      validate(fields()[0], value);
      this.type = value;
      fieldSetFlags()[0] = true;
      return this;
    }

    /**
      * Checks whether the 'type' field has been set.
      * @return True if the 'type' field has been set, false otherwise.
      */
    public boolean hasType() {
      return fieldSetFlags()[0];
    }


    /**
      * Clears the value of the 'type' field.
      * @return This builder.
      */
    public eu.driver.model.geojson.sim.FeatureCollection.Builder clearType() {
      type = null;
      fieldSetFlags()[0] = false;
      return this;
    }

    /**
      * Gets the value of the 'bbox' field.
      * @return The value.
      */
    public java.util.List<java.lang.Double> getBbox() {
      return bbox;
    }

    /**
      * Sets the value of the 'bbox' field.
      * @param value The value of 'bbox'.
      * @return This builder.
      */
    public eu.driver.model.geojson.sim.FeatureCollection.Builder setBbox(java.util.List<java.lang.Double> value) {
      validate(fields()[1], value);
      this.bbox = value;
      fieldSetFlags()[1] = true;
      return this;
    }

    /**
      * Checks whether the 'bbox' field has been set.
      * @return True if the 'bbox' field has been set, false otherwise.
      */
    public boolean hasBbox() {
      return fieldSetFlags()[1];
    }


    /**
      * Clears the value of the 'bbox' field.
      * @return This builder.
      */
    public eu.driver.model.geojson.sim.FeatureCollection.Builder clearBbox() {
      bbox = null;
      fieldSetFlags()[1] = false;
      return this;
    }

    /**
      * Gets the value of the 'features' field.
      * @return The value.
      */
    public java.util.List<eu.driver.model.geojson.sim.Feature> getFeatures() {
      return features;
    }

    /**
      * Sets the value of the 'features' field.
      * @param value The value of 'features'.
      * @return This builder.
      */
    public eu.driver.model.geojson.sim.FeatureCollection.Builder setFeatures(java.util.List<eu.driver.model.geojson.sim.Feature> value) {
      validate(fields()[2], value);
      this.features = value;
      fieldSetFlags()[2] = true;
      return this;
    }

    /**
      * Checks whether the 'features' field has been set.
      * @return True if the 'features' field has been set, false otherwise.
      */
    public boolean hasFeatures() {
      return fieldSetFlags()[2];
    }


    /**
      * Clears the value of the 'features' field.
      * @return This builder.
      */
    public eu.driver.model.geojson.sim.FeatureCollection.Builder clearFeatures() {
      features = null;
      fieldSetFlags()[2] = false;
      return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public FeatureCollection build() {
      try {
        FeatureCollection record = new FeatureCollection();
        record.type = fieldSetFlags()[0] ? this.type : (eu.driver.model.geojson.sim.FeatureCollectionType) defaultValue(fields()[0]);
        record.bbox = fieldSetFlags()[1] ? this.bbox : (java.util.List<java.lang.Double>) defaultValue(fields()[1]);
        record.features = fieldSetFlags()[2] ? this.features : (java.util.List<eu.driver.model.geojson.sim.Feature>) defaultValue(fields()[2]);
        return record;
      } catch (java.lang.Exception e) {
        throw new org.apache.avro.AvroRuntimeException(e);
      }
    }
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumWriter<FeatureCollection>
    WRITER$ = (org.apache.avro.io.DatumWriter<FeatureCollection>)MODEL$.createDatumWriter(SCHEMA$);

  @Override public void writeExternal(java.io.ObjectOutput out)
    throws java.io.IOException {
    WRITER$.write(this, SpecificData.getEncoder(out));
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumReader<FeatureCollection>
    READER$ = (org.apache.avro.io.DatumReader<FeatureCollection>)MODEL$.createDatumReader(SCHEMA$);

  @Override public void readExternal(java.io.ObjectInput in)
    throws java.io.IOException {
    READER$.read(this, SpecificData.getDecoder(in));
  }

}
