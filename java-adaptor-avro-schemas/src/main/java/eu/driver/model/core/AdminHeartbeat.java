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
/** Heartbeat message, indicating that the is up and alive. */
@org.apache.avro.specific.AvroGenerated
public class AdminHeartbeat extends org.apache.avro.specific.SpecificRecordBase implements org.apache.avro.specific.SpecificRecord {
  private static final long serialVersionUID = 4276140953737321709L;
  public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"record\",\"name\":\"AdminHeartbeat\",\"namespace\":\"eu.driver.model.core\",\"doc\":\"Heartbeat message, indicating that the is up and alive.\",\"fields\":[{\"name\":\"id\",\"type\":\"string\",\"doc\":\"ID of the adin tool\"},{\"name\":\"alive\",\"type\":\"long\",\"doc\":\"The date and time the distribution message was sent as the number of milliseconds from the unix epoch, 1 January 1970 00:00:00.000 UTC.\",\"logicalType\":\"timestamp-millis\"}]}");
  public static org.apache.avro.Schema getClassSchema() { return SCHEMA$; }

  private static SpecificData MODEL$ = new SpecificData();

  private static final BinaryMessageEncoder<AdminHeartbeat> ENCODER =
      new BinaryMessageEncoder<AdminHeartbeat>(MODEL$, SCHEMA$);

  private static final BinaryMessageDecoder<AdminHeartbeat> DECODER =
      new BinaryMessageDecoder<AdminHeartbeat>(MODEL$, SCHEMA$);

  /**
   * Return the BinaryMessageDecoder instance used by this class.
   */
  public static BinaryMessageDecoder<AdminHeartbeat> getDecoder() {
    return DECODER;
  }

  /**
   * Create a new BinaryMessageDecoder instance for this class that uses the specified {@link SchemaStore}.
   * @param resolver a {@link SchemaStore} used to find schemas by fingerprint
   */
  public static BinaryMessageDecoder<AdminHeartbeat> createDecoder(SchemaStore resolver) {
    return new BinaryMessageDecoder<AdminHeartbeat>(MODEL$, SCHEMA$, resolver);
  }

  /** Serializes this AdminHeartbeat to a ByteBuffer. */
  public java.nio.ByteBuffer toByteBuffer() throws java.io.IOException {
    return ENCODER.encode(this);
  }

  /** Deserializes a AdminHeartbeat from a ByteBuffer. */
  public static AdminHeartbeat fromByteBuffer(
      java.nio.ByteBuffer b) throws java.io.IOException {
    return DECODER.decode(b);
  }

  /** ID of the adin tool */
  @Deprecated public java.lang.CharSequence id;
  /** The date and time the distribution message was sent as the number of milliseconds from the unix epoch, 1 January 1970 00:00:00.000 UTC. */
  @Deprecated public long alive;

  /**
   * Default constructor.  Note that this does not initialize fields
   * to their default values from the schema.  If that is desired then
   * one should use <code>newBuilder()</code>.
   */
  public AdminHeartbeat() {}

  /**
   * All-args constructor.
   * @param id ID of the adin tool
   * @param alive The date and time the distribution message was sent as the number of milliseconds from the unix epoch, 1 January 1970 00:00:00.000 UTC.
   */
  public AdminHeartbeat(java.lang.CharSequence id, java.lang.Long alive) {
    this.id = id;
    this.alive = alive;
  }

  public org.apache.avro.Schema getSchema() { return SCHEMA$; }
  // Used by DatumWriter.  Applications should not call.
  public java.lang.Object get(int field$) {
    switch (field$) {
    case 0: return id;
    case 1: return alive;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }

  // Used by DatumReader.  Applications should not call.
  @SuppressWarnings(value="unchecked")
  public void put(int field$, java.lang.Object value$) {
    switch (field$) {
    case 0: id = (java.lang.CharSequence)value$; break;
    case 1: alive = (java.lang.Long)value$; break;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }

  /**
   * Gets the value of the 'id' field.
   * @return ID of the adin tool
   */
  public java.lang.CharSequence getId() {
    return id;
  }

  /**
   * Sets the value of the 'id' field.
   * ID of the adin tool
   * @param value the value to set.
   */
  public void setId(java.lang.CharSequence value) {
    this.id = value;
  }

  /**
   * Gets the value of the 'alive' field.
   * @return The date and time the distribution message was sent as the number of milliseconds from the unix epoch, 1 January 1970 00:00:00.000 UTC.
   */
  public java.lang.Long getAlive() {
    return alive;
  }

  /**
   * Sets the value of the 'alive' field.
   * The date and time the distribution message was sent as the number of milliseconds from the unix epoch, 1 January 1970 00:00:00.000 UTC.
   * @param value the value to set.
   */
  public void setAlive(java.lang.Long value) {
    this.alive = value;
  }

  /**
   * Creates a new AdminHeartbeat RecordBuilder.
   * @return A new AdminHeartbeat RecordBuilder
   */
  public static eu.driver.model.core.AdminHeartbeat.Builder newBuilder() {
    return new eu.driver.model.core.AdminHeartbeat.Builder();
  }

  /**
   * Creates a new AdminHeartbeat RecordBuilder by copying an existing Builder.
   * @param other The existing builder to copy.
   * @return A new AdminHeartbeat RecordBuilder
   */
  public static eu.driver.model.core.AdminHeartbeat.Builder newBuilder(eu.driver.model.core.AdminHeartbeat.Builder other) {
    return new eu.driver.model.core.AdminHeartbeat.Builder(other);
  }

  /**
   * Creates a new AdminHeartbeat RecordBuilder by copying an existing AdminHeartbeat instance.
   * @param other The existing instance to copy.
   * @return A new AdminHeartbeat RecordBuilder
   */
  public static eu.driver.model.core.AdminHeartbeat.Builder newBuilder(eu.driver.model.core.AdminHeartbeat other) {
    return new eu.driver.model.core.AdminHeartbeat.Builder(other);
  }

  /**
   * RecordBuilder for AdminHeartbeat instances.
   */
  public static class Builder extends org.apache.avro.specific.SpecificRecordBuilderBase<AdminHeartbeat>
    implements org.apache.avro.data.RecordBuilder<AdminHeartbeat> {

    /** ID of the adin tool */
    private java.lang.CharSequence id;
    /** The date and time the distribution message was sent as the number of milliseconds from the unix epoch, 1 January 1970 00:00:00.000 UTC. */
    private long alive;

    /** Creates a new Builder */
    private Builder() {
      super(SCHEMA$);
    }

    /**
     * Creates a Builder by copying an existing Builder.
     * @param other The existing Builder to copy.
     */
    private Builder(eu.driver.model.core.AdminHeartbeat.Builder other) {
      super(other);
      if (isValidValue(fields()[0], other.id)) {
        this.id = data().deepCopy(fields()[0].schema(), other.id);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.alive)) {
        this.alive = data().deepCopy(fields()[1].schema(), other.alive);
        fieldSetFlags()[1] = true;
      }
    }

    /**
     * Creates a Builder by copying an existing AdminHeartbeat instance
     * @param other The existing instance to copy.
     */
    private Builder(eu.driver.model.core.AdminHeartbeat other) {
            super(SCHEMA$);
      if (isValidValue(fields()[0], other.id)) {
        this.id = data().deepCopy(fields()[0].schema(), other.id);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.alive)) {
        this.alive = data().deepCopy(fields()[1].schema(), other.alive);
        fieldSetFlags()[1] = true;
      }
    }

    /**
      * Gets the value of the 'id' field.
      * ID of the adin tool
      * @return The value.
      */
    public java.lang.CharSequence getId() {
      return id;
    }

    /**
      * Sets the value of the 'id' field.
      * ID of the adin tool
      * @param value The value of 'id'.
      * @return This builder.
      */
    public eu.driver.model.core.AdminHeartbeat.Builder setId(java.lang.CharSequence value) {
      validate(fields()[0], value);
      this.id = value;
      fieldSetFlags()[0] = true;
      return this;
    }

    /**
      * Checks whether the 'id' field has been set.
      * ID of the adin tool
      * @return True if the 'id' field has been set, false otherwise.
      */
    public boolean hasId() {
      return fieldSetFlags()[0];
    }


    /**
      * Clears the value of the 'id' field.
      * ID of the adin tool
      * @return This builder.
      */
    public eu.driver.model.core.AdminHeartbeat.Builder clearId() {
      id = null;
      fieldSetFlags()[0] = false;
      return this;
    }

    /**
      * Gets the value of the 'alive' field.
      * The date and time the distribution message was sent as the number of milliseconds from the unix epoch, 1 January 1970 00:00:00.000 UTC.
      * @return The value.
      */
    public java.lang.Long getAlive() {
      return alive;
    }

    /**
      * Sets the value of the 'alive' field.
      * The date and time the distribution message was sent as the number of milliseconds from the unix epoch, 1 January 1970 00:00:00.000 UTC.
      * @param value The value of 'alive'.
      * @return This builder.
      */
    public eu.driver.model.core.AdminHeartbeat.Builder setAlive(long value) {
      validate(fields()[1], value);
      this.alive = value;
      fieldSetFlags()[1] = true;
      return this;
    }

    /**
      * Checks whether the 'alive' field has been set.
      * The date and time the distribution message was sent as the number of milliseconds from the unix epoch, 1 January 1970 00:00:00.000 UTC.
      * @return True if the 'alive' field has been set, false otherwise.
      */
    public boolean hasAlive() {
      return fieldSetFlags()[1];
    }


    /**
      * Clears the value of the 'alive' field.
      * The date and time the distribution message was sent as the number of milliseconds from the unix epoch, 1 January 1970 00:00:00.000 UTC.
      * @return This builder.
      */
    public eu.driver.model.core.AdminHeartbeat.Builder clearAlive() {
      fieldSetFlags()[1] = false;
      return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public AdminHeartbeat build() {
      try {
        AdminHeartbeat record = new AdminHeartbeat();
        record.id = fieldSetFlags()[0] ? this.id : (java.lang.CharSequence) defaultValue(fields()[0]);
        record.alive = fieldSetFlags()[1] ? this.alive : (java.lang.Long) defaultValue(fields()[1]);
        return record;
      } catch (java.lang.Exception e) {
        throw new org.apache.avro.AvroRuntimeException(e);
      }
    }
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumWriter<AdminHeartbeat>
    WRITER$ = (org.apache.avro.io.DatumWriter<AdminHeartbeat>)MODEL$.createDatumWriter(SCHEMA$);

  @Override public void writeExternal(java.io.ObjectOutput out)
    throws java.io.IOException {
    WRITER$.write(this, SpecificData.getEncoder(out));
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumReader<AdminHeartbeat>
    READER$ = (org.apache.avro.io.DatumReader<AdminHeartbeat>)MODEL$.createDatumReader(SCHEMA$);

  @Override public void readExternal(java.io.ObjectInput in)
    throws java.io.IOException {
    READER$.read(this, SpecificData.getDecoder(in));
  }

}
