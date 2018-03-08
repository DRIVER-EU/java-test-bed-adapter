/**
 * Autogenerated by Avro
 *
 * DO NOT EDIT DIRECTLY
 */
package eu.driver.model.core;

import org.apache.avro.specific.SpecificData;
import org.apache.avro.message.BinaryMessageEncoder;
import org.apache.avro.message.BinaryMessageDecoder;
import org.apache.avro.message.SchemaStore;

@SuppressWarnings("all")
/** TimingControl message to distribute the trial time changes. */
@org.apache.avro.specific.AvroGenerated
public class TimingControl extends org.apache.avro.specific.SpecificRecordBase implements org.apache.avro.specific.SpecificRecord {
  private static final long serialVersionUID = -8155383874288737194L;
  public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"record\",\"name\":\"TimingControl\",\"namespace\":\"eu.driver.model.core\",\"doc\":\"TimingControl message to distribute the trial time changes.\",\"fields\":[{\"name\":\"id\",\"type\":\"long\",\"doc\":\"sequence ID\"},{\"name\":\"trialTime\",\"type\":\"long\",\"doc\":\"The date and time the distribution message was sent as the number of milliseconds from the unix epoch, 1 January 1970 00:00:00.000 UTC.\",\"logicalType\":\"timestamp-millis\"},{\"name\":\"trialTimeSpeed\",\"type\":\"long\",\"doc\":\"The Trialtime speed factor.\"}]}");
  public static org.apache.avro.Schema getClassSchema() { return SCHEMA$; }

  private static SpecificData MODEL$ = new SpecificData();

  private static final BinaryMessageEncoder<TimingControl> ENCODER =
      new BinaryMessageEncoder<TimingControl>(MODEL$, SCHEMA$);

  private static final BinaryMessageDecoder<TimingControl> DECODER =
      new BinaryMessageDecoder<TimingControl>(MODEL$, SCHEMA$);

  /**
   * Return the BinaryMessageDecoder instance used by this class.
   */
  public static BinaryMessageDecoder<TimingControl> getDecoder() {
    return DECODER;
  }

  /**
   * Create a new BinaryMessageDecoder instance for this class that uses the specified {@link SchemaStore}.
   * @param resolver a {@link SchemaStore} used to find schemas by fingerprint
   */
  public static BinaryMessageDecoder<TimingControl> createDecoder(SchemaStore resolver) {
    return new BinaryMessageDecoder<TimingControl>(MODEL$, SCHEMA$, resolver);
  }

  /** Serializes this TimingControl to a ByteBuffer. */
  public java.nio.ByteBuffer toByteBuffer() throws java.io.IOException {
    return ENCODER.encode(this);
  }

  /** Deserializes a TimingControl from a ByteBuffer. */
  public static TimingControl fromByteBuffer(
      java.nio.ByteBuffer b) throws java.io.IOException {
    return DECODER.decode(b);
  }

  /** sequence ID */
  @Deprecated public long id;
  /** The date and time the distribution message was sent as the number of milliseconds from the unix epoch, 1 January 1970 00:00:00.000 UTC. */
  @Deprecated public long trialTime;
  /** The Trialtime speed factor. */
  @Deprecated public long trialTimeSpeed;

  /**
   * Default constructor.  Note that this does not initialize fields
   * to their default values from the schema.  If that is desired then
   * one should use <code>newBuilder()</code>.
   */
  public TimingControl() {}

  /**
   * All-args constructor.
   * @param id sequence ID
   * @param trialTime The date and time the distribution message was sent as the number of milliseconds from the unix epoch, 1 January 1970 00:00:00.000 UTC.
   * @param trialTimeSpeed The Trialtime speed factor.
   */
  public TimingControl(java.lang.Long id, java.lang.Long trialTime, java.lang.Long trialTimeSpeed) {
    this.id = id;
    this.trialTime = trialTime;
    this.trialTimeSpeed = trialTimeSpeed;
  }

  public org.apache.avro.Schema getSchema() { return SCHEMA$; }
  // Used by DatumWriter.  Applications should not call.
  public java.lang.Object get(int field$) {
    switch (field$) {
    case 0: return id;
    case 1: return trialTime;
    case 2: return trialTimeSpeed;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }

  // Used by DatumReader.  Applications should not call.
  @SuppressWarnings(value="unchecked")
  public void put(int field$, java.lang.Object value$) {
    switch (field$) {
    case 0: id = (java.lang.Long)value$; break;
    case 1: trialTime = (java.lang.Long)value$; break;
    case 2: trialTimeSpeed = (java.lang.Long)value$; break;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }

  /**
   * Gets the value of the 'id' field.
   * @return sequence ID
   */
  public java.lang.Long getId() {
    return id;
  }

  /**
   * Sets the value of the 'id' field.
   * sequence ID
   * @param value the value to set.
   */
  public void setId(java.lang.Long value) {
    this.id = value;
  }

  /**
   * Gets the value of the 'trialTime' field.
   * @return The date and time the distribution message was sent as the number of milliseconds from the unix epoch, 1 January 1970 00:00:00.000 UTC.
   */
  public java.lang.Long getTrialTime() {
    return trialTime;
  }

  /**
   * Sets the value of the 'trialTime' field.
   * The date and time the distribution message was sent as the number of milliseconds from the unix epoch, 1 January 1970 00:00:00.000 UTC.
   * @param value the value to set.
   */
  public void setTrialTime(java.lang.Long value) {
    this.trialTime = value;
  }

  /**
   * Gets the value of the 'trialTimeSpeed' field.
   * @return The Trialtime speed factor.
   */
  public java.lang.Long getTrialTimeSpeed() {
    return trialTimeSpeed;
  }

  /**
   * Sets the value of the 'trialTimeSpeed' field.
   * The Trialtime speed factor.
   * @param value the value to set.
   */
  public void setTrialTimeSpeed(java.lang.Long value) {
    this.trialTimeSpeed = value;
  }

  /**
   * Creates a new TimingControl RecordBuilder.
   * @return A new TimingControl RecordBuilder
   */
  public static eu.driver.model.core.TimingControl.Builder newBuilder() {
    return new eu.driver.model.core.TimingControl.Builder();
  }

  /**
   * Creates a new TimingControl RecordBuilder by copying an existing Builder.
   * @param other The existing builder to copy.
   * @return A new TimingControl RecordBuilder
   */
  public static eu.driver.model.core.TimingControl.Builder newBuilder(eu.driver.model.core.TimingControl.Builder other) {
    return new eu.driver.model.core.TimingControl.Builder(other);
  }

  /**
   * Creates a new TimingControl RecordBuilder by copying an existing TimingControl instance.
   * @param other The existing instance to copy.
   * @return A new TimingControl RecordBuilder
   */
  public static eu.driver.model.core.TimingControl.Builder newBuilder(eu.driver.model.core.TimingControl other) {
    return new eu.driver.model.core.TimingControl.Builder(other);
  }

  /**
   * RecordBuilder for TimingControl instances.
   */
  public static class Builder extends org.apache.avro.specific.SpecificRecordBuilderBase<TimingControl>
    implements org.apache.avro.data.RecordBuilder<TimingControl> {

    /** sequence ID */
    private long id;
    /** The date and time the distribution message was sent as the number of milliseconds from the unix epoch, 1 January 1970 00:00:00.000 UTC. */
    private long trialTime;
    /** The Trialtime speed factor. */
    private long trialTimeSpeed;

    /** Creates a new Builder */
    private Builder() {
      super(SCHEMA$);
    }

    /**
     * Creates a Builder by copying an existing Builder.
     * @param other The existing Builder to copy.
     */
    private Builder(eu.driver.model.core.TimingControl.Builder other) {
      super(other);
      if (isValidValue(fields()[0], other.id)) {
        this.id = data().deepCopy(fields()[0].schema(), other.id);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.trialTime)) {
        this.trialTime = data().deepCopy(fields()[1].schema(), other.trialTime);
        fieldSetFlags()[1] = true;
      }
      if (isValidValue(fields()[2], other.trialTimeSpeed)) {
        this.trialTimeSpeed = data().deepCopy(fields()[2].schema(), other.trialTimeSpeed);
        fieldSetFlags()[2] = true;
      }
    }

    /**
     * Creates a Builder by copying an existing TimingControl instance
     * @param other The existing instance to copy.
     */
    private Builder(eu.driver.model.core.TimingControl other) {
            super(SCHEMA$);
      if (isValidValue(fields()[0], other.id)) {
        this.id = data().deepCopy(fields()[0].schema(), other.id);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.trialTime)) {
        this.trialTime = data().deepCopy(fields()[1].schema(), other.trialTime);
        fieldSetFlags()[1] = true;
      }
      if (isValidValue(fields()[2], other.trialTimeSpeed)) {
        this.trialTimeSpeed = data().deepCopy(fields()[2].schema(), other.trialTimeSpeed);
        fieldSetFlags()[2] = true;
      }
    }

    /**
      * Gets the value of the 'id' field.
      * sequence ID
      * @return The value.
      */
    public java.lang.Long getId() {
      return id;
    }

    /**
      * Sets the value of the 'id' field.
      * sequence ID
      * @param value The value of 'id'.
      * @return This builder.
      */
    public eu.driver.model.core.TimingControl.Builder setId(long value) {
      validate(fields()[0], value);
      this.id = value;
      fieldSetFlags()[0] = true;
      return this;
    }

    /**
      * Checks whether the 'id' field has been set.
      * sequence ID
      * @return True if the 'id' field has been set, false otherwise.
      */
    public boolean hasId() {
      return fieldSetFlags()[0];
    }


    /**
      * Clears the value of the 'id' field.
      * sequence ID
      * @return This builder.
      */
    public eu.driver.model.core.TimingControl.Builder clearId() {
      fieldSetFlags()[0] = false;
      return this;
    }

    /**
      * Gets the value of the 'trialTime' field.
      * The date and time the distribution message was sent as the number of milliseconds from the unix epoch, 1 January 1970 00:00:00.000 UTC.
      * @return The value.
      */
    public java.lang.Long getTrialTime() {
      return trialTime;
    }

    /**
      * Sets the value of the 'trialTime' field.
      * The date and time the distribution message was sent as the number of milliseconds from the unix epoch, 1 January 1970 00:00:00.000 UTC.
      * @param value The value of 'trialTime'.
      * @return This builder.
      */
    public eu.driver.model.core.TimingControl.Builder setTrialTime(long value) {
      validate(fields()[1], value);
      this.trialTime = value;
      fieldSetFlags()[1] = true;
      return this;
    }

    /**
      * Checks whether the 'trialTime' field has been set.
      * The date and time the distribution message was sent as the number of milliseconds from the unix epoch, 1 January 1970 00:00:00.000 UTC.
      * @return True if the 'trialTime' field has been set, false otherwise.
      */
    public boolean hasTrialTime() {
      return fieldSetFlags()[1];
    }


    /**
      * Clears the value of the 'trialTime' field.
      * The date and time the distribution message was sent as the number of milliseconds from the unix epoch, 1 January 1970 00:00:00.000 UTC.
      * @return This builder.
      */
    public eu.driver.model.core.TimingControl.Builder clearTrialTime() {
      fieldSetFlags()[1] = false;
      return this;
    }

    /**
      * Gets the value of the 'trialTimeSpeed' field.
      * The Trialtime speed factor.
      * @return The value.
      */
    public java.lang.Long getTrialTimeSpeed() {
      return trialTimeSpeed;
    }

    /**
      * Sets the value of the 'trialTimeSpeed' field.
      * The Trialtime speed factor.
      * @param value The value of 'trialTimeSpeed'.
      * @return This builder.
      */
    public eu.driver.model.core.TimingControl.Builder setTrialTimeSpeed(long value) {
      validate(fields()[2], value);
      this.trialTimeSpeed = value;
      fieldSetFlags()[2] = true;
      return this;
    }

    /**
      * Checks whether the 'trialTimeSpeed' field has been set.
      * The Trialtime speed factor.
      * @return True if the 'trialTimeSpeed' field has been set, false otherwise.
      */
    public boolean hasTrialTimeSpeed() {
      return fieldSetFlags()[2];
    }


    /**
      * Clears the value of the 'trialTimeSpeed' field.
      * The Trialtime speed factor.
      * @return This builder.
      */
    public eu.driver.model.core.TimingControl.Builder clearTrialTimeSpeed() {
      fieldSetFlags()[2] = false;
      return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public TimingControl build() {
      try {
        TimingControl record = new TimingControl();
        record.id = fieldSetFlags()[0] ? this.id : (java.lang.Long) defaultValue(fields()[0]);
        record.trialTime = fieldSetFlags()[1] ? this.trialTime : (java.lang.Long) defaultValue(fields()[1]);
        record.trialTimeSpeed = fieldSetFlags()[2] ? this.trialTimeSpeed : (java.lang.Long) defaultValue(fields()[2]);
        return record;
      } catch (java.lang.Exception e) {
        throw new org.apache.avro.AvroRuntimeException(e);
      }
    }
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumWriter<TimingControl>
    WRITER$ = (org.apache.avro.io.DatumWriter<TimingControl>)MODEL$.createDatumWriter(SCHEMA$);

  @Override public void writeExternal(java.io.ObjectOutput out)
    throws java.io.IOException {
    WRITER$.write(this, SpecificData.getEncoder(out));
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumReader<TimingControl>
    READER$ = (org.apache.avro.io.DatumReader<TimingControl>)MODEL$.createDatumReader(SCHEMA$);

  @Override public void readExternal(java.io.ObjectInput in)
    throws java.io.IOException {
    READER$.read(this, SpecificData.getDecoder(in));
  }

}