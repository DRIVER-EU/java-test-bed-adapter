/**
 * Autogenerated by Avro
 *
 * DO NOT EDIT DIRECTLY
 */
package eu.driver.model.sim.geo;

import org.apache.avro.specific.SpecificData;
import org.apache.avro.message.BinaryMessageEncoder;
import org.apache.avro.message.BinaryMessageDecoder;
import org.apache.avro.message.SchemaStore;

@SuppressWarnings("all")
/** WGS84/Aviation-based representation of an orientation on earth - Right-handed item-specific reference system, with in base-setting heading/yaw-axis pointing down (to the centre of the earth), pitch-axis pointing to the right, roll/bank-axis pointing forward */
@org.apache.avro.specific.AvroGenerated
public class Orientation extends org.apache.avro.specific.SpecificRecordBase implements org.apache.avro.specific.SpecificRecord {
  private static final long serialVersionUID = -2291262983178347928L;
  public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"record\",\"name\":\"Orientation\",\"namespace\":\"eu.driver.model.sim.geo\",\"doc\":\"WGS84/Aviation-based representation of an orientation on earth - Right-handed item-specific reference system, with in base-setting heading/yaw-axis pointing down (to the centre of the earth), pitch-axis pointing to the right, roll/bank-axis pointing forward\",\"fields\":[{\"name\":\"yaw\",\"type\":\"double\",\"doc\":\"yaw or heading in degrees [0, 360) - 0 is pointing towards geographic north - yaw of 90 is EAST, yaw of 270 is WEST\"},{\"name\":\"pitch\",\"type\":\"double\",\"doc\":\"pitch in degrees (-90, 90] - 0 is perpendicular to line [origin of item - centre of WGS84-based ellipsoid] - pitch of +45 is 45 degrees pointing upwards, -45 is 45 degrees pointing downwards\"},{\"name\":\"roll\",\"type\":\"double\",\"doc\":\"roll or bank in degrees (-180, 180] - 0 is perpendicular to line [origin of item - centre of WGS84-based ellipsoid] - bank of +45 is 45 degrees roll to the right, -45 is 45 degrees roll to the left\"}]}");
  public static org.apache.avro.Schema getClassSchema() { return SCHEMA$; }

  private static SpecificData MODEL$ = new SpecificData();

  private static final BinaryMessageEncoder<Orientation> ENCODER =
      new BinaryMessageEncoder<Orientation>(MODEL$, SCHEMA$);

  private static final BinaryMessageDecoder<Orientation> DECODER =
      new BinaryMessageDecoder<Orientation>(MODEL$, SCHEMA$);

  /**
   * Return the BinaryMessageDecoder instance used by this class.
   */
  public static BinaryMessageDecoder<Orientation> getDecoder() {
    return DECODER;
  }

  /**
   * Create a new BinaryMessageDecoder instance for this class that uses the specified {@link SchemaStore}.
   * @param resolver a {@link SchemaStore} used to find schemas by fingerprint
   */
  public static BinaryMessageDecoder<Orientation> createDecoder(SchemaStore resolver) {
    return new BinaryMessageDecoder<Orientation>(MODEL$, SCHEMA$, resolver);
  }

  /** Serializes this Orientation to a ByteBuffer. */
  public java.nio.ByteBuffer toByteBuffer() throws java.io.IOException {
    return ENCODER.encode(this);
  }

  /** Deserializes a Orientation from a ByteBuffer. */
  public static Orientation fromByteBuffer(
      java.nio.ByteBuffer b) throws java.io.IOException {
    return DECODER.decode(b);
  }

  /** yaw or heading in degrees [0, 360) - 0 is pointing towards geographic north - yaw of 90 is EAST, yaw of 270 is WEST */
  @Deprecated public double yaw;
  /** pitch in degrees (-90, 90] - 0 is perpendicular to line [origin of item - centre of WGS84-based ellipsoid] - pitch of +45 is 45 degrees pointing upwards, -45 is 45 degrees pointing downwards */
  @Deprecated public double pitch;
  /** roll or bank in degrees (-180, 180] - 0 is perpendicular to line [origin of item - centre of WGS84-based ellipsoid] - bank of +45 is 45 degrees roll to the right, -45 is 45 degrees roll to the left */
  @Deprecated public double roll;

  /**
   * Default constructor.  Note that this does not initialize fields
   * to their default values from the schema.  If that is desired then
   * one should use <code>newBuilder()</code>.
   */
  public Orientation() {}

  /**
   * All-args constructor.
   * @param yaw yaw or heading in degrees [0, 360) - 0 is pointing towards geographic north - yaw of 90 is EAST, yaw of 270 is WEST
   * @param pitch pitch in degrees (-90, 90] - 0 is perpendicular to line [origin of item - centre of WGS84-based ellipsoid] - pitch of +45 is 45 degrees pointing upwards, -45 is 45 degrees pointing downwards
   * @param roll roll or bank in degrees (-180, 180] - 0 is perpendicular to line [origin of item - centre of WGS84-based ellipsoid] - bank of +45 is 45 degrees roll to the right, -45 is 45 degrees roll to the left
   */
  public Orientation(java.lang.Double yaw, java.lang.Double pitch, java.lang.Double roll) {
    this.yaw = yaw;
    this.pitch = pitch;
    this.roll = roll;
  }

  public org.apache.avro.Schema getSchema() { return SCHEMA$; }
  // Used by DatumWriter.  Applications should not call.
  public java.lang.Object get(int field$) {
    switch (field$) {
    case 0: return yaw;
    case 1: return pitch;
    case 2: return roll;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }

  // Used by DatumReader.  Applications should not call.
  @SuppressWarnings(value="unchecked")
  public void put(int field$, java.lang.Object value$) {
    switch (field$) {
    case 0: yaw = (java.lang.Double)value$; break;
    case 1: pitch = (java.lang.Double)value$; break;
    case 2: roll = (java.lang.Double)value$; break;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }

  /**
   * Gets the value of the 'yaw' field.
   * @return yaw or heading in degrees [0, 360) - 0 is pointing towards geographic north - yaw of 90 is EAST, yaw of 270 is WEST
   */
  public java.lang.Double getYaw() {
    return yaw;
  }

  /**
   * Sets the value of the 'yaw' field.
   * yaw or heading in degrees [0, 360) - 0 is pointing towards geographic north - yaw of 90 is EAST, yaw of 270 is WEST
   * @param value the value to set.
   */
  public void setYaw(java.lang.Double value) {
    this.yaw = value;
  }

  /**
   * Gets the value of the 'pitch' field.
   * @return pitch in degrees (-90, 90] - 0 is perpendicular to line [origin of item - centre of WGS84-based ellipsoid] - pitch of +45 is 45 degrees pointing upwards, -45 is 45 degrees pointing downwards
   */
  public java.lang.Double getPitch() {
    return pitch;
  }

  /**
   * Sets the value of the 'pitch' field.
   * pitch in degrees (-90, 90] - 0 is perpendicular to line [origin of item - centre of WGS84-based ellipsoid] - pitch of +45 is 45 degrees pointing upwards, -45 is 45 degrees pointing downwards
   * @param value the value to set.
   */
  public void setPitch(java.lang.Double value) {
    this.pitch = value;
  }

  /**
   * Gets the value of the 'roll' field.
   * @return roll or bank in degrees (-180, 180] - 0 is perpendicular to line [origin of item - centre of WGS84-based ellipsoid] - bank of +45 is 45 degrees roll to the right, -45 is 45 degrees roll to the left
   */
  public java.lang.Double getRoll() {
    return roll;
  }

  /**
   * Sets the value of the 'roll' field.
   * roll or bank in degrees (-180, 180] - 0 is perpendicular to line [origin of item - centre of WGS84-based ellipsoid] - bank of +45 is 45 degrees roll to the right, -45 is 45 degrees roll to the left
   * @param value the value to set.
   */
  public void setRoll(java.lang.Double value) {
    this.roll = value;
  }

  /**
   * Creates a new Orientation RecordBuilder.
   * @return A new Orientation RecordBuilder
   */
  public static eu.driver.model.sim.geo.Orientation.Builder newBuilder() {
    return new eu.driver.model.sim.geo.Orientation.Builder();
  }

  /**
   * Creates a new Orientation RecordBuilder by copying an existing Builder.
   * @param other The existing builder to copy.
   * @return A new Orientation RecordBuilder
   */
  public static eu.driver.model.sim.geo.Orientation.Builder newBuilder(eu.driver.model.sim.geo.Orientation.Builder other) {
    return new eu.driver.model.sim.geo.Orientation.Builder(other);
  }

  /**
   * Creates a new Orientation RecordBuilder by copying an existing Orientation instance.
   * @param other The existing instance to copy.
   * @return A new Orientation RecordBuilder
   */
  public static eu.driver.model.sim.geo.Orientation.Builder newBuilder(eu.driver.model.sim.geo.Orientation other) {
    return new eu.driver.model.sim.geo.Orientation.Builder(other);
  }

  /**
   * RecordBuilder for Orientation instances.
   */
  public static class Builder extends org.apache.avro.specific.SpecificRecordBuilderBase<Orientation>
    implements org.apache.avro.data.RecordBuilder<Orientation> {

    /** yaw or heading in degrees [0, 360) - 0 is pointing towards geographic north - yaw of 90 is EAST, yaw of 270 is WEST */
    private double yaw;
    /** pitch in degrees (-90, 90] - 0 is perpendicular to line [origin of item - centre of WGS84-based ellipsoid] - pitch of +45 is 45 degrees pointing upwards, -45 is 45 degrees pointing downwards */
    private double pitch;
    /** roll or bank in degrees (-180, 180] - 0 is perpendicular to line [origin of item - centre of WGS84-based ellipsoid] - bank of +45 is 45 degrees roll to the right, -45 is 45 degrees roll to the left */
    private double roll;

    /** Creates a new Builder */
    private Builder() {
      super(SCHEMA$);
    }

    /**
     * Creates a Builder by copying an existing Builder.
     * @param other The existing Builder to copy.
     */
    private Builder(eu.driver.model.sim.geo.Orientation.Builder other) {
      super(other);
      if (isValidValue(fields()[0], other.yaw)) {
        this.yaw = data().deepCopy(fields()[0].schema(), other.yaw);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.pitch)) {
        this.pitch = data().deepCopy(fields()[1].schema(), other.pitch);
        fieldSetFlags()[1] = true;
      }
      if (isValidValue(fields()[2], other.roll)) {
        this.roll = data().deepCopy(fields()[2].schema(), other.roll);
        fieldSetFlags()[2] = true;
      }
    }

    /**
     * Creates a Builder by copying an existing Orientation instance
     * @param other The existing instance to copy.
     */
    private Builder(eu.driver.model.sim.geo.Orientation other) {
            super(SCHEMA$);
      if (isValidValue(fields()[0], other.yaw)) {
        this.yaw = data().deepCopy(fields()[0].schema(), other.yaw);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.pitch)) {
        this.pitch = data().deepCopy(fields()[1].schema(), other.pitch);
        fieldSetFlags()[1] = true;
      }
      if (isValidValue(fields()[2], other.roll)) {
        this.roll = data().deepCopy(fields()[2].schema(), other.roll);
        fieldSetFlags()[2] = true;
      }
    }

    /**
      * Gets the value of the 'yaw' field.
      * yaw or heading in degrees [0, 360) - 0 is pointing towards geographic north - yaw of 90 is EAST, yaw of 270 is WEST
      * @return The value.
      */
    public java.lang.Double getYaw() {
      return yaw;
    }

    /**
      * Sets the value of the 'yaw' field.
      * yaw or heading in degrees [0, 360) - 0 is pointing towards geographic north - yaw of 90 is EAST, yaw of 270 is WEST
      * @param value The value of 'yaw'.
      * @return This builder.
      */
    public eu.driver.model.sim.geo.Orientation.Builder setYaw(double value) {
      validate(fields()[0], value);
      this.yaw = value;
      fieldSetFlags()[0] = true;
      return this;
    }

    /**
      * Checks whether the 'yaw' field has been set.
      * yaw or heading in degrees [0, 360) - 0 is pointing towards geographic north - yaw of 90 is EAST, yaw of 270 is WEST
      * @return True if the 'yaw' field has been set, false otherwise.
      */
    public boolean hasYaw() {
      return fieldSetFlags()[0];
    }


    /**
      * Clears the value of the 'yaw' field.
      * yaw or heading in degrees [0, 360) - 0 is pointing towards geographic north - yaw of 90 is EAST, yaw of 270 is WEST
      * @return This builder.
      */
    public eu.driver.model.sim.geo.Orientation.Builder clearYaw() {
      fieldSetFlags()[0] = false;
      return this;
    }

    /**
      * Gets the value of the 'pitch' field.
      * pitch in degrees (-90, 90] - 0 is perpendicular to line [origin of item - centre of WGS84-based ellipsoid] - pitch of +45 is 45 degrees pointing upwards, -45 is 45 degrees pointing downwards
      * @return The value.
      */
    public java.lang.Double getPitch() {
      return pitch;
    }

    /**
      * Sets the value of the 'pitch' field.
      * pitch in degrees (-90, 90] - 0 is perpendicular to line [origin of item - centre of WGS84-based ellipsoid] - pitch of +45 is 45 degrees pointing upwards, -45 is 45 degrees pointing downwards
      * @param value The value of 'pitch'.
      * @return This builder.
      */
    public eu.driver.model.sim.geo.Orientation.Builder setPitch(double value) {
      validate(fields()[1], value);
      this.pitch = value;
      fieldSetFlags()[1] = true;
      return this;
    }

    /**
      * Checks whether the 'pitch' field has been set.
      * pitch in degrees (-90, 90] - 0 is perpendicular to line [origin of item - centre of WGS84-based ellipsoid] - pitch of +45 is 45 degrees pointing upwards, -45 is 45 degrees pointing downwards
      * @return True if the 'pitch' field has been set, false otherwise.
      */
    public boolean hasPitch() {
      return fieldSetFlags()[1];
    }


    /**
      * Clears the value of the 'pitch' field.
      * pitch in degrees (-90, 90] - 0 is perpendicular to line [origin of item - centre of WGS84-based ellipsoid] - pitch of +45 is 45 degrees pointing upwards, -45 is 45 degrees pointing downwards
      * @return This builder.
      */
    public eu.driver.model.sim.geo.Orientation.Builder clearPitch() {
      fieldSetFlags()[1] = false;
      return this;
    }

    /**
      * Gets the value of the 'roll' field.
      * roll or bank in degrees (-180, 180] - 0 is perpendicular to line [origin of item - centre of WGS84-based ellipsoid] - bank of +45 is 45 degrees roll to the right, -45 is 45 degrees roll to the left
      * @return The value.
      */
    public java.lang.Double getRoll() {
      return roll;
    }

    /**
      * Sets the value of the 'roll' field.
      * roll or bank in degrees (-180, 180] - 0 is perpendicular to line [origin of item - centre of WGS84-based ellipsoid] - bank of +45 is 45 degrees roll to the right, -45 is 45 degrees roll to the left
      * @param value The value of 'roll'.
      * @return This builder.
      */
    public eu.driver.model.sim.geo.Orientation.Builder setRoll(double value) {
      validate(fields()[2], value);
      this.roll = value;
      fieldSetFlags()[2] = true;
      return this;
    }

    /**
      * Checks whether the 'roll' field has been set.
      * roll or bank in degrees (-180, 180] - 0 is perpendicular to line [origin of item - centre of WGS84-based ellipsoid] - bank of +45 is 45 degrees roll to the right, -45 is 45 degrees roll to the left
      * @return True if the 'roll' field has been set, false otherwise.
      */
    public boolean hasRoll() {
      return fieldSetFlags()[2];
    }


    /**
      * Clears the value of the 'roll' field.
      * roll or bank in degrees (-180, 180] - 0 is perpendicular to line [origin of item - centre of WGS84-based ellipsoid] - bank of +45 is 45 degrees roll to the right, -45 is 45 degrees roll to the left
      * @return This builder.
      */
    public eu.driver.model.sim.geo.Orientation.Builder clearRoll() {
      fieldSetFlags()[2] = false;
      return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Orientation build() {
      try {
        Orientation record = new Orientation();
        record.yaw = fieldSetFlags()[0] ? this.yaw : (java.lang.Double) defaultValue(fields()[0]);
        record.pitch = fieldSetFlags()[1] ? this.pitch : (java.lang.Double) defaultValue(fields()[1]);
        record.roll = fieldSetFlags()[2] ? this.roll : (java.lang.Double) defaultValue(fields()[2]);
        return record;
      } catch (java.lang.Exception e) {
        throw new org.apache.avro.AvroRuntimeException(e);
      }
    }
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumWriter<Orientation>
    WRITER$ = (org.apache.avro.io.DatumWriter<Orientation>)MODEL$.createDatumWriter(SCHEMA$);

  @Override public void writeExternal(java.io.ObjectOutput out)
    throws java.io.IOException {
    WRITER$.write(this, SpecificData.getEncoder(out));
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumReader<Orientation>
    READER$ = (org.apache.avro.io.DatumReader<Orientation>)MODEL$.createDatumReader(SCHEMA$);

  @Override public void readExternal(java.io.ObjectInput in)
    throws java.io.IOException {
    READER$.read(this, SpecificData.getDecoder(in));
  }

}