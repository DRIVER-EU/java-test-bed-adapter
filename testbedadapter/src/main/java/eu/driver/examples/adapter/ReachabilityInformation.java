/**
 * Autogenerated by Avro
 *
 * DO NOT EDIT DIRECTLY
 */
package eu.driver.examples.adapter;

import org.apache.avro.specific.SpecificData;

@SuppressWarnings("all")
@org.apache.avro.specific.AvroGenerated
public class ReachabilityInformation extends org.apache.avro.specific.SpecificRecordBase implements org.apache.avro.specific.SpecificRecord {
  private static final long serialVersionUID = 7518031569236832287L;
  public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"record\",\"name\":\"ReachabilityInformation\",\"namespace\":\"eu.driver.model\",\"fields\":[{\"name\":\"travelTimeInSec\",\"type\":\"int\",\"doc\":\"The travel time until this edge is reached\"},{\"name\":\"shape\",\"type\":{\"type\":\"array\",\"items\":\"string\"},\"doc\":\"A list of coordinates in the shape of this route.\"}]}");
  public static org.apache.avro.Schema getClassSchema() { return SCHEMA$; }
  /** The travel time until this edge is reached */
  @Deprecated public int travelTimeInSec;
  /** A list of coordinates in the shape of this route. */
  @Deprecated public java.util.List<java.lang.CharSequence> shape;

  /**
   * Default constructor.  Note that this does not initialize fields
   * to their default values from the schema.  If that is desired then
   * one should use <code>newBuilder()</code>.
   */
  public ReachabilityInformation() {}

  /**
   * All-args constructor.
   * @param travelTimeInSec The travel time until this edge is reached
   * @param shape A list of coordinates in the shape of this route.
   */
  public ReachabilityInformation(java.lang.Integer travelTimeInSec, java.util.List<java.lang.CharSequence> shape) {
    this.travelTimeInSec = travelTimeInSec;
    this.shape = shape;
  }

  public org.apache.avro.Schema getSchema() { return SCHEMA$; }
  // Used by DatumWriter.  Applications should not call.
  public java.lang.Object get(int field$) {
    switch (field$) {
    case 0: return travelTimeInSec;
    case 1: return shape;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }

  // Used by DatumReader.  Applications should not call.
  @SuppressWarnings(value="unchecked")
  public void put(int field$, java.lang.Object value$) {
    switch (field$) {
    case 0: travelTimeInSec = (java.lang.Integer)value$; break;
    case 1: shape = (java.util.List<java.lang.CharSequence>)value$; break;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }

  /**
   * Gets the value of the 'travelTimeInSec' field.
   * @return The travel time until this edge is reached
   */
  public java.lang.Integer getTravelTimeInSec() {
    return travelTimeInSec;
  }

  /**
   * Sets the value of the 'travelTimeInSec' field.
   * The travel time until this edge is reached
   * @param value the value to set.
   */
  public void setTravelTimeInSec(java.lang.Integer value) {
    this.travelTimeInSec = value;
  }

  /**
   * Gets the value of the 'shape' field.
   * @return A list of coordinates in the shape of this route.
   */
  public java.util.List<java.lang.CharSequence> getShape() {
    return shape;
  }

  /**
   * Sets the value of the 'shape' field.
   * A list of coordinates in the shape of this route.
   * @param value the value to set.
   */
  public void setShape(java.util.List<java.lang.CharSequence> value) {
    this.shape = value;
  }

  /**
   * Creates a new ReachabilityInformation RecordBuilder.
   * @return A new ReachabilityInformation RecordBuilder
   */
  public static ReachabilityInformation.Builder newBuilder() {
    return new ReachabilityInformation.Builder();
  }

  /**
   * Creates a new ReachabilityInformation RecordBuilder by copying an existing Builder.
   * @param other The existing builder to copy.
   * @return A new ReachabilityInformation RecordBuilder
   */
  public static ReachabilityInformation.Builder newBuilder(ReachabilityInformation.Builder other) {
    return new ReachabilityInformation.Builder(other);
  }

  /**
   * Creates a new ReachabilityInformation RecordBuilder by copying an existing ReachabilityInformation instance.
   * @param other The existing instance to copy.
   * @return A new ReachabilityInformation RecordBuilder
   */
  public static ReachabilityInformation.Builder newBuilder(ReachabilityInformation other) {
    return new ReachabilityInformation.Builder(other);
  }

  /**
   * RecordBuilder for ReachabilityInformation instances.
   */
  public static class Builder extends org.apache.avro.specific.SpecificRecordBuilderBase<ReachabilityInformation>
    implements org.apache.avro.data.RecordBuilder<ReachabilityInformation> {

    /** The travel time until this edge is reached */
    private int travelTimeInSec;
    /** A list of coordinates in the shape of this route. */
    private java.util.List<java.lang.CharSequence> shape;

    /** Creates a new Builder */
    private Builder() {
      super(SCHEMA$);
    }

    /**
     * Creates a Builder by copying an existing Builder.
     * @param other The existing Builder to copy.
     */
    private Builder(ReachabilityInformation.Builder other) {
      super(other);
      if (isValidValue(fields()[0], other.travelTimeInSec)) {
        this.travelTimeInSec = data().deepCopy(fields()[0].schema(), other.travelTimeInSec);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.shape)) {
        this.shape = data().deepCopy(fields()[1].schema(), other.shape);
        fieldSetFlags()[1] = true;
      }
    }

    /**
     * Creates a Builder by copying an existing ReachabilityInformation instance
     * @param other The existing instance to copy.
     */
    private Builder(ReachabilityInformation other) {
            super(SCHEMA$);
      if (isValidValue(fields()[0], other.travelTimeInSec)) {
        this.travelTimeInSec = data().deepCopy(fields()[0].schema(), other.travelTimeInSec);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.shape)) {
        this.shape = data().deepCopy(fields()[1].schema(), other.shape);
        fieldSetFlags()[1] = true;
      }
    }

    /**
      * Gets the value of the 'travelTimeInSec' field.
      * The travel time until this edge is reached
      * @return The value.
      */
    public java.lang.Integer getTravelTimeInSec() {
      return travelTimeInSec;
    }

    /**
      * Sets the value of the 'travelTimeInSec' field.
      * The travel time until this edge is reached
      * @param value The value of 'travelTimeInSec'.
      * @return This builder.
      */
    public ReachabilityInformation.Builder setTravelTimeInSec(int value) {
      validate(fields()[0], value);
      this.travelTimeInSec = value;
      fieldSetFlags()[0] = true;
      return this;
    }

    /**
      * Checks whether the 'travelTimeInSec' field has been set.
      * The travel time until this edge is reached
      * @return True if the 'travelTimeInSec' field has been set, false otherwise.
      */
    public boolean hasTravelTimeInSec() {
      return fieldSetFlags()[0];
    }


    /**
      * Clears the value of the 'travelTimeInSec' field.
      * The travel time until this edge is reached
      * @return This builder.
      */
    public ReachabilityInformation.Builder clearTravelTimeInSec() {
      fieldSetFlags()[0] = false;
      return this;
    }

    /**
      * Gets the value of the 'shape' field.
      * A list of coordinates in the shape of this route.
      * @return The value.
      */
    public java.util.List<java.lang.CharSequence> getShape() {
      return shape;
    }

    /**
      * Sets the value of the 'shape' field.
      * A list of coordinates in the shape of this route.
      * @param value The value of 'shape'.
      * @return This builder.
      */
    public ReachabilityInformation.Builder setShape(java.util.List<java.lang.CharSequence> value) {
      validate(fields()[1], value);
      this.shape = value;
      fieldSetFlags()[1] = true;
      return this;
    }

    /**
      * Checks whether the 'shape' field has been set.
      * A list of coordinates in the shape of this route.
      * @return True if the 'shape' field has been set, false otherwise.
      */
    public boolean hasShape() {
      return fieldSetFlags()[1];
    }


    /**
      * Clears the value of the 'shape' field.
      * A list of coordinates in the shape of this route.
      * @return This builder.
      */
    public ReachabilityInformation.Builder clearShape() {
      shape = null;
      fieldSetFlags()[1] = false;
      return this;
    }

    @Override
    public ReachabilityInformation build() {
      try {
        ReachabilityInformation record = new ReachabilityInformation();
        record.travelTimeInSec = fieldSetFlags()[0] ? this.travelTimeInSec : (java.lang.Integer) defaultValue(fields()[0]);
        record.shape = fieldSetFlags()[1] ? this.shape : (java.util.List<java.lang.CharSequence>) defaultValue(fields()[1]);
        return record;
      } catch (Exception e) {
        throw new org.apache.avro.AvroRuntimeException(e);
      }
    }
  }

  private static final org.apache.avro.io.DatumWriter
    WRITER$ = new org.apache.avro.specific.SpecificDatumWriter(SCHEMA$);

  @Override public void writeExternal(java.io.ObjectOutput out)
    throws java.io.IOException {
    WRITER$.write(this, SpecificData.getEncoder(out));
  }

  private static final org.apache.avro.io.DatumReader
    READER$ = new org.apache.avro.specific.SpecificDatumReader(SCHEMA$);

  @Override public void readExternal(java.io.ObjectInput in)
    throws java.io.IOException {
    READER$.read(this, SpecificData.getDecoder(in));
  }

}