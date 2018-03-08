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
/** Configuration message, mainly for inspecting what a client is producing and consuming. */
@org.apache.avro.specific.AvroGenerated
public class Configuration extends org.apache.avro.specific.SpecificRecordBase implements org.apache.avro.specific.SpecificRecord {
  private static final long serialVersionUID = 2562302679384508610L;
  public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"record\",\"name\":\"Configuration\",\"namespace\":\"eu.driver.model.core\",\"doc\":\"Configuration message, mainly for inspecting what a client is producing and consuming.\",\"fields\":[{\"name\":\"clientId\",\"type\":\"string\",\"doc\":\"ID of the client\"},{\"name\":\"kafkaHost\",\"type\":\"string\",\"doc\":\"URI of the Kafka broker\"},{\"name\":\"schemaRegistry\",\"type\":\"string\",\"doc\":\"URI of the schema registry\"},{\"name\":\"heartbeatInterval\",\"type\":[\"null\",\"int\"],\"doc\":\"Time in msec how often you send out a heartbeat. Default 5000\",\"default\":null},{\"name\":\"consume\",\"type\":[\"null\",{\"type\":\"array\",\"items\":{\"type\":\"record\",\"name\":\"OffsetFetchRequest\",\"fields\":[{\"name\":\"topic\",\"type\":\"string\"},{\"name\":\"offset\",\"type\":[\"null\",\"int\"],\"default\":null},{\"name\":\"partition\",\"type\":[\"null\",\"int\"],\"default\":null}]}}],\"doc\":\"Topics you are consuming\",\"default\":null},{\"name\":\"produce\",\"type\":[\"null\",{\"type\":\"array\",\"items\":\"string\"}],\"doc\":\"Topics you are producing\",\"default\":null},{\"name\":\"logging\",\"type\":[\"null\",{\"type\":\"record\",\"name\":\"LogSettings\",\"fields\":[{\"name\":\"logToFile\",\"type\":[\"null\",\"int\"],\"doc\":\"If set [0..5], log to file as specified in logFile\",\"default\":null},{\"name\":\"logFile\",\"type\":[\"null\",\"string\"],\"doc\":\"Name of the log file\",\"default\":null},{\"name\":\"logToConsole\",\"type\":[\"null\",\"int\"],\"doc\":\"If set [0..5], log to console. Number indicates logging level\",\"default\":null},{\"name\":\"logToKafka\",\"type\":[\"null\",\"int\"],\"doc\":\"If set [0..5], log to Kafka\",\"default\":null}]}],\"doc\":\"Logging details, may be replaced by an enum\",\"default\":null}]}");
  public static org.apache.avro.Schema getClassSchema() { return SCHEMA$; }

  private static SpecificData MODEL$ = new SpecificData();

  private static final BinaryMessageEncoder<Configuration> ENCODER =
      new BinaryMessageEncoder<Configuration>(MODEL$, SCHEMA$);

  private static final BinaryMessageDecoder<Configuration> DECODER =
      new BinaryMessageDecoder<Configuration>(MODEL$, SCHEMA$);

  /**
   * Return the BinaryMessageDecoder instance used by this class.
   */
  public static BinaryMessageDecoder<Configuration> getDecoder() {
    return DECODER;
  }

  /**
   * Create a new BinaryMessageDecoder instance for this class that uses the specified {@link SchemaStore}.
   * @param resolver a {@link SchemaStore} used to find schemas by fingerprint
   */
  public static BinaryMessageDecoder<Configuration> createDecoder(SchemaStore resolver) {
    return new BinaryMessageDecoder<Configuration>(MODEL$, SCHEMA$, resolver);
  }

  /** Serializes this Configuration to a ByteBuffer. */
  public java.nio.ByteBuffer toByteBuffer() throws java.io.IOException {
    return ENCODER.encode(this);
  }

  /** Deserializes a Configuration from a ByteBuffer. */
  public static Configuration fromByteBuffer(
      java.nio.ByteBuffer b) throws java.io.IOException {
    return DECODER.decode(b);
  }

  /** ID of the client */
  @Deprecated public java.lang.CharSequence clientId;
  /** URI of the Kafka broker */
  @Deprecated public java.lang.CharSequence kafkaHost;
  /** URI of the schema registry */
  @Deprecated public java.lang.CharSequence schemaRegistry;
  /** Time in msec how often you send out a heartbeat. Default 5000 */
  @Deprecated public java.lang.Integer heartbeatInterval;
  /** Topics you are consuming */
  @Deprecated public java.util.List<eu.driver.model.core.OffsetFetchRequest> consume;
  /** Topics you are producing */
  @Deprecated public java.util.List<java.lang.CharSequence> produce;
  /** Logging details, may be replaced by an enum */
  @Deprecated public eu.driver.model.core.LogSettings logging;

  /**
   * Default constructor.  Note that this does not initialize fields
   * to their default values from the schema.  If that is desired then
   * one should use <code>newBuilder()</code>.
   */
  public Configuration() {}

  /**
   * All-args constructor.
   * @param clientId ID of the client
   * @param kafkaHost URI of the Kafka broker
   * @param schemaRegistry URI of the schema registry
   * @param heartbeatInterval Time in msec how often you send out a heartbeat. Default 5000
   * @param consume Topics you are consuming
   * @param produce Topics you are producing
   * @param logging Logging details, may be replaced by an enum
   */
  public Configuration(java.lang.CharSequence clientId, java.lang.CharSequence kafkaHost, java.lang.CharSequence schemaRegistry, java.lang.Integer heartbeatInterval, java.util.List<eu.driver.model.core.OffsetFetchRequest> consume, java.util.List<java.lang.CharSequence> produce, eu.driver.model.core.LogSettings logging) {
    this.clientId = clientId;
    this.kafkaHost = kafkaHost;
    this.schemaRegistry = schemaRegistry;
    this.heartbeatInterval = heartbeatInterval;
    this.consume = consume;
    this.produce = produce;
    this.logging = logging;
  }

  public org.apache.avro.Schema getSchema() { return SCHEMA$; }
  // Used by DatumWriter.  Applications should not call.
  public java.lang.Object get(int field$) {
    switch (field$) {
    case 0: return clientId;
    case 1: return kafkaHost;
    case 2: return schemaRegistry;
    case 3: return heartbeatInterval;
    case 4: return consume;
    case 5: return produce;
    case 6: return logging;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }

  // Used by DatumReader.  Applications should not call.
  @SuppressWarnings(value="unchecked")
  public void put(int field$, java.lang.Object value$) {
    switch (field$) {
    case 0: clientId = (java.lang.CharSequence)value$; break;
    case 1: kafkaHost = (java.lang.CharSequence)value$; break;
    case 2: schemaRegistry = (java.lang.CharSequence)value$; break;
    case 3: heartbeatInterval = (java.lang.Integer)value$; break;
    case 4: consume = (java.util.List<eu.driver.model.core.OffsetFetchRequest>)value$; break;
    case 5: produce = (java.util.List<java.lang.CharSequence>)value$; break;
    case 6: logging = (eu.driver.model.core.LogSettings)value$; break;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }

  /**
   * Gets the value of the 'clientId' field.
   * @return ID of the client
   */
  public java.lang.CharSequence getClientId() {
    return clientId;
  }

  /**
   * Sets the value of the 'clientId' field.
   * ID of the client
   * @param value the value to set.
   */
  public void setClientId(java.lang.CharSequence value) {
    this.clientId = value;
  }

  /**
   * Gets the value of the 'kafkaHost' field.
   * @return URI of the Kafka broker
   */
  public java.lang.CharSequence getKafkaHost() {
    return kafkaHost;
  }

  /**
   * Sets the value of the 'kafkaHost' field.
   * URI of the Kafka broker
   * @param value the value to set.
   */
  public void setKafkaHost(java.lang.CharSequence value) {
    this.kafkaHost = value;
  }

  /**
   * Gets the value of the 'schemaRegistry' field.
   * @return URI of the schema registry
   */
  public java.lang.CharSequence getSchemaRegistry() {
    return schemaRegistry;
  }

  /**
   * Sets the value of the 'schemaRegistry' field.
   * URI of the schema registry
   * @param value the value to set.
   */
  public void setSchemaRegistry(java.lang.CharSequence value) {
    this.schemaRegistry = value;
  }

  /**
   * Gets the value of the 'heartbeatInterval' field.
   * @return Time in msec how often you send out a heartbeat. Default 5000
   */
  public java.lang.Integer getHeartbeatInterval() {
    return heartbeatInterval;
  }

  /**
   * Sets the value of the 'heartbeatInterval' field.
   * Time in msec how often you send out a heartbeat. Default 5000
   * @param value the value to set.
   */
  public void setHeartbeatInterval(java.lang.Integer value) {
    this.heartbeatInterval = value;
  }

  /**
   * Gets the value of the 'consume' field.
   * @return Topics you are consuming
   */
  public java.util.List<eu.driver.model.core.OffsetFetchRequest> getConsume() {
    return consume;
  }

  /**
   * Sets the value of the 'consume' field.
   * Topics you are consuming
   * @param value the value to set.
   */
  public void setConsume(java.util.List<eu.driver.model.core.OffsetFetchRequest> value) {
    this.consume = value;
  }

  /**
   * Gets the value of the 'produce' field.
   * @return Topics you are producing
   */
  public java.util.List<java.lang.CharSequence> getProduce() {
    return produce;
  }

  /**
   * Sets the value of the 'produce' field.
   * Topics you are producing
   * @param value the value to set.
   */
  public void setProduce(java.util.List<java.lang.CharSequence> value) {
    this.produce = value;
  }

  /**
   * Gets the value of the 'logging' field.
   * @return Logging details, may be replaced by an enum
   */
  public eu.driver.model.core.LogSettings getLogging() {
    return logging;
  }

  /**
   * Sets the value of the 'logging' field.
   * Logging details, may be replaced by an enum
   * @param value the value to set.
   */
  public void setLogging(eu.driver.model.core.LogSettings value) {
    this.logging = value;
  }

  /**
   * Creates a new Configuration RecordBuilder.
   * @return A new Configuration RecordBuilder
   */
  public static eu.driver.model.core.Configuration.Builder newBuilder() {
    return new eu.driver.model.core.Configuration.Builder();
  }

  /**
   * Creates a new Configuration RecordBuilder by copying an existing Builder.
   * @param other The existing builder to copy.
   * @return A new Configuration RecordBuilder
   */
  public static eu.driver.model.core.Configuration.Builder newBuilder(eu.driver.model.core.Configuration.Builder other) {
    return new eu.driver.model.core.Configuration.Builder(other);
  }

  /**
   * Creates a new Configuration RecordBuilder by copying an existing Configuration instance.
   * @param other The existing instance to copy.
   * @return A new Configuration RecordBuilder
   */
  public static eu.driver.model.core.Configuration.Builder newBuilder(eu.driver.model.core.Configuration other) {
    return new eu.driver.model.core.Configuration.Builder(other);
  }

  /**
   * RecordBuilder for Configuration instances.
   */
  public static class Builder extends org.apache.avro.specific.SpecificRecordBuilderBase<Configuration>
    implements org.apache.avro.data.RecordBuilder<Configuration> {

    /** ID of the client */
    private java.lang.CharSequence clientId;
    /** URI of the Kafka broker */
    private java.lang.CharSequence kafkaHost;
    /** URI of the schema registry */
    private java.lang.CharSequence schemaRegistry;
    /** Time in msec how often you send out a heartbeat. Default 5000 */
    private java.lang.Integer heartbeatInterval;
    /** Topics you are consuming */
    private java.util.List<eu.driver.model.core.OffsetFetchRequest> consume;
    /** Topics you are producing */
    private java.util.List<java.lang.CharSequence> produce;
    /** Logging details, may be replaced by an enum */
    private eu.driver.model.core.LogSettings logging;
    private eu.driver.model.core.LogSettings.Builder loggingBuilder;

    /** Creates a new Builder */
    private Builder() {
      super(SCHEMA$);
    }

    /**
     * Creates a Builder by copying an existing Builder.
     * @param other The existing Builder to copy.
     */
    private Builder(eu.driver.model.core.Configuration.Builder other) {
      super(other);
      if (isValidValue(fields()[0], other.clientId)) {
        this.clientId = data().deepCopy(fields()[0].schema(), other.clientId);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.kafkaHost)) {
        this.kafkaHost = data().deepCopy(fields()[1].schema(), other.kafkaHost);
        fieldSetFlags()[1] = true;
      }
      if (isValidValue(fields()[2], other.schemaRegistry)) {
        this.schemaRegistry = data().deepCopy(fields()[2].schema(), other.schemaRegistry);
        fieldSetFlags()[2] = true;
      }
      if (isValidValue(fields()[3], other.heartbeatInterval)) {
        this.heartbeatInterval = data().deepCopy(fields()[3].schema(), other.heartbeatInterval);
        fieldSetFlags()[3] = true;
      }
      if (isValidValue(fields()[4], other.consume)) {
        this.consume = data().deepCopy(fields()[4].schema(), other.consume);
        fieldSetFlags()[4] = true;
      }
      if (isValidValue(fields()[5], other.produce)) {
        this.produce = data().deepCopy(fields()[5].schema(), other.produce);
        fieldSetFlags()[5] = true;
      }
      if (isValidValue(fields()[6], other.logging)) {
        this.logging = data().deepCopy(fields()[6].schema(), other.logging);
        fieldSetFlags()[6] = true;
      }
      if (other.hasLoggingBuilder()) {
        this.loggingBuilder = eu.driver.model.core.LogSettings.newBuilder(other.getLoggingBuilder());
      }
    }

    /**
     * Creates a Builder by copying an existing Configuration instance
     * @param other The existing instance to copy.
     */
    private Builder(eu.driver.model.core.Configuration other) {
            super(SCHEMA$);
      if (isValidValue(fields()[0], other.clientId)) {
        this.clientId = data().deepCopy(fields()[0].schema(), other.clientId);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.kafkaHost)) {
        this.kafkaHost = data().deepCopy(fields()[1].schema(), other.kafkaHost);
        fieldSetFlags()[1] = true;
      }
      if (isValidValue(fields()[2], other.schemaRegistry)) {
        this.schemaRegistry = data().deepCopy(fields()[2].schema(), other.schemaRegistry);
        fieldSetFlags()[2] = true;
      }
      if (isValidValue(fields()[3], other.heartbeatInterval)) {
        this.heartbeatInterval = data().deepCopy(fields()[3].schema(), other.heartbeatInterval);
        fieldSetFlags()[3] = true;
      }
      if (isValidValue(fields()[4], other.consume)) {
        this.consume = data().deepCopy(fields()[4].schema(), other.consume);
        fieldSetFlags()[4] = true;
      }
      if (isValidValue(fields()[5], other.produce)) {
        this.produce = data().deepCopy(fields()[5].schema(), other.produce);
        fieldSetFlags()[5] = true;
      }
      if (isValidValue(fields()[6], other.logging)) {
        this.logging = data().deepCopy(fields()[6].schema(), other.logging);
        fieldSetFlags()[6] = true;
      }
      this.loggingBuilder = null;
    }

    /**
      * Gets the value of the 'clientId' field.
      * ID of the client
      * @return The value.
      */
    public java.lang.CharSequence getClientId() {
      return clientId;
    }

    /**
      * Sets the value of the 'clientId' field.
      * ID of the client
      * @param value The value of 'clientId'.
      * @return This builder.
      */
    public eu.driver.model.core.Configuration.Builder setClientId(java.lang.CharSequence value) {
      validate(fields()[0], value);
      this.clientId = value;
      fieldSetFlags()[0] = true;
      return this;
    }

    /**
      * Checks whether the 'clientId' field has been set.
      * ID of the client
      * @return True if the 'clientId' field has been set, false otherwise.
      */
    public boolean hasClientId() {
      return fieldSetFlags()[0];
    }


    /**
      * Clears the value of the 'clientId' field.
      * ID of the client
      * @return This builder.
      */
    public eu.driver.model.core.Configuration.Builder clearClientId() {
      clientId = null;
      fieldSetFlags()[0] = false;
      return this;
    }

    /**
      * Gets the value of the 'kafkaHost' field.
      * URI of the Kafka broker
      * @return The value.
      */
    public java.lang.CharSequence getKafkaHost() {
      return kafkaHost;
    }

    /**
      * Sets the value of the 'kafkaHost' field.
      * URI of the Kafka broker
      * @param value The value of 'kafkaHost'.
      * @return This builder.
      */
    public eu.driver.model.core.Configuration.Builder setKafkaHost(java.lang.CharSequence value) {
      validate(fields()[1], value);
      this.kafkaHost = value;
      fieldSetFlags()[1] = true;
      return this;
    }

    /**
      * Checks whether the 'kafkaHost' field has been set.
      * URI of the Kafka broker
      * @return True if the 'kafkaHost' field has been set, false otherwise.
      */
    public boolean hasKafkaHost() {
      return fieldSetFlags()[1];
    }


    /**
      * Clears the value of the 'kafkaHost' field.
      * URI of the Kafka broker
      * @return This builder.
      */
    public eu.driver.model.core.Configuration.Builder clearKafkaHost() {
      kafkaHost = null;
      fieldSetFlags()[1] = false;
      return this;
    }

    /**
      * Gets the value of the 'schemaRegistry' field.
      * URI of the schema registry
      * @return The value.
      */
    public java.lang.CharSequence getSchemaRegistry() {
      return schemaRegistry;
    }

    /**
      * Sets the value of the 'schemaRegistry' field.
      * URI of the schema registry
      * @param value The value of 'schemaRegistry'.
      * @return This builder.
      */
    public eu.driver.model.core.Configuration.Builder setSchemaRegistry(java.lang.CharSequence value) {
      validate(fields()[2], value);
      this.schemaRegistry = value;
      fieldSetFlags()[2] = true;
      return this;
    }

    /**
      * Checks whether the 'schemaRegistry' field has been set.
      * URI of the schema registry
      * @return True if the 'schemaRegistry' field has been set, false otherwise.
      */
    public boolean hasSchemaRegistry() {
      return fieldSetFlags()[2];
    }


    /**
      * Clears the value of the 'schemaRegistry' field.
      * URI of the schema registry
      * @return This builder.
      */
    public eu.driver.model.core.Configuration.Builder clearSchemaRegistry() {
      schemaRegistry = null;
      fieldSetFlags()[2] = false;
      return this;
    }

    /**
      * Gets the value of the 'heartbeatInterval' field.
      * Time in msec how often you send out a heartbeat. Default 5000
      * @return The value.
      */
    public java.lang.Integer getHeartbeatInterval() {
      return heartbeatInterval;
    }

    /**
      * Sets the value of the 'heartbeatInterval' field.
      * Time in msec how often you send out a heartbeat. Default 5000
      * @param value The value of 'heartbeatInterval'.
      * @return This builder.
      */
    public eu.driver.model.core.Configuration.Builder setHeartbeatInterval(java.lang.Integer value) {
      validate(fields()[3], value);
      this.heartbeatInterval = value;
      fieldSetFlags()[3] = true;
      return this;
    }

    /**
      * Checks whether the 'heartbeatInterval' field has been set.
      * Time in msec how often you send out a heartbeat. Default 5000
      * @return True if the 'heartbeatInterval' field has been set, false otherwise.
      */
    public boolean hasHeartbeatInterval() {
      return fieldSetFlags()[3];
    }


    /**
      * Clears the value of the 'heartbeatInterval' field.
      * Time in msec how often you send out a heartbeat. Default 5000
      * @return This builder.
      */
    public eu.driver.model.core.Configuration.Builder clearHeartbeatInterval() {
      heartbeatInterval = null;
      fieldSetFlags()[3] = false;
      return this;
    }

    /**
      * Gets the value of the 'consume' field.
      * Topics you are consuming
      * @return The value.
      */
    public java.util.List<eu.driver.model.core.OffsetFetchRequest> getConsume() {
      return consume;
    }

    /**
      * Sets the value of the 'consume' field.
      * Topics you are consuming
      * @param value The value of 'consume'.
      * @return This builder.
      */
    public eu.driver.model.core.Configuration.Builder setConsume(java.util.List<eu.driver.model.core.OffsetFetchRequest> value) {
      validate(fields()[4], value);
      this.consume = value;
      fieldSetFlags()[4] = true;
      return this;
    }

    /**
      * Checks whether the 'consume' field has been set.
      * Topics you are consuming
      * @return True if the 'consume' field has been set, false otherwise.
      */
    public boolean hasConsume() {
      return fieldSetFlags()[4];
    }


    /**
      * Clears the value of the 'consume' field.
      * Topics you are consuming
      * @return This builder.
      */
    public eu.driver.model.core.Configuration.Builder clearConsume() {
      consume = null;
      fieldSetFlags()[4] = false;
      return this;
    }

    /**
      * Gets the value of the 'produce' field.
      * Topics you are producing
      * @return The value.
      */
    public java.util.List<java.lang.CharSequence> getProduce() {
      return produce;
    }

    /**
      * Sets the value of the 'produce' field.
      * Topics you are producing
      * @param value The value of 'produce'.
      * @return This builder.
      */
    public eu.driver.model.core.Configuration.Builder setProduce(java.util.List<java.lang.CharSequence> value) {
      validate(fields()[5], value);
      this.produce = value;
      fieldSetFlags()[5] = true;
      return this;
    }

    /**
      * Checks whether the 'produce' field has been set.
      * Topics you are producing
      * @return True if the 'produce' field has been set, false otherwise.
      */
    public boolean hasProduce() {
      return fieldSetFlags()[5];
    }


    /**
      * Clears the value of the 'produce' field.
      * Topics you are producing
      * @return This builder.
      */
    public eu.driver.model.core.Configuration.Builder clearProduce() {
      produce = null;
      fieldSetFlags()[5] = false;
      return this;
    }

    /**
      * Gets the value of the 'logging' field.
      * Logging details, may be replaced by an enum
      * @return The value.
      */
    public eu.driver.model.core.LogSettings getLogging() {
      return logging;
    }

    /**
      * Sets the value of the 'logging' field.
      * Logging details, may be replaced by an enum
      * @param value The value of 'logging'.
      * @return This builder.
      */
    public eu.driver.model.core.Configuration.Builder setLogging(eu.driver.model.core.LogSettings value) {
      validate(fields()[6], value);
      this.loggingBuilder = null;
      this.logging = value;
      fieldSetFlags()[6] = true;
      return this;
    }

    /**
      * Checks whether the 'logging' field has been set.
      * Logging details, may be replaced by an enum
      * @return True if the 'logging' field has been set, false otherwise.
      */
    public boolean hasLogging() {
      return fieldSetFlags()[6];
    }

    /**
     * Gets the Builder instance for the 'logging' field and creates one if it doesn't exist yet.
     * Logging details, may be replaced by an enum
     * @return This builder.
     */
    public eu.driver.model.core.LogSettings.Builder getLoggingBuilder() {
      if (loggingBuilder == null) {
        if (hasLogging()) {
          setLoggingBuilder(eu.driver.model.core.LogSettings.newBuilder(logging));
        } else {
          setLoggingBuilder(eu.driver.model.core.LogSettings.newBuilder());
        }
      }
      return loggingBuilder;
    }

    /**
     * Sets the Builder instance for the 'logging' field
     * Logging details, may be replaced by an enum
     * @param value The builder instance that must be set.
     * @return This builder.
     */
    public eu.driver.model.core.Configuration.Builder setLoggingBuilder(eu.driver.model.core.LogSettings.Builder value) {
      clearLogging();
      loggingBuilder = value;
      return this;
    }

    /**
     * Checks whether the 'logging' field has an active Builder instance
     * Logging details, may be replaced by an enum
     * @return True if the 'logging' field has an active Builder instance
     */
    public boolean hasLoggingBuilder() {
      return loggingBuilder != null;
    }

    /**
      * Clears the value of the 'logging' field.
      * Logging details, may be replaced by an enum
      * @return This builder.
      */
    public eu.driver.model.core.Configuration.Builder clearLogging() {
      logging = null;
      loggingBuilder = null;
      fieldSetFlags()[6] = false;
      return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Configuration build() {
      try {
        Configuration record = new Configuration();
        record.clientId = fieldSetFlags()[0] ? this.clientId : (java.lang.CharSequence) defaultValue(fields()[0]);
        record.kafkaHost = fieldSetFlags()[1] ? this.kafkaHost : (java.lang.CharSequence) defaultValue(fields()[1]);
        record.schemaRegistry = fieldSetFlags()[2] ? this.schemaRegistry : (java.lang.CharSequence) defaultValue(fields()[2]);
        record.heartbeatInterval = fieldSetFlags()[3] ? this.heartbeatInterval : (java.lang.Integer) defaultValue(fields()[3]);
        record.consume = fieldSetFlags()[4] ? this.consume : (java.util.List<eu.driver.model.core.OffsetFetchRequest>) defaultValue(fields()[4]);
        record.produce = fieldSetFlags()[5] ? this.produce : (java.util.List<java.lang.CharSequence>) defaultValue(fields()[5]);
        if (loggingBuilder != null) {
          record.logging = this.loggingBuilder.build();
        } else {
          record.logging = fieldSetFlags()[6] ? this.logging : (eu.driver.model.core.LogSettings) defaultValue(fields()[6]);
        }
        return record;
      } catch (java.lang.Exception e) {
        throw new org.apache.avro.AvroRuntimeException(e);
      }
    }
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumWriter<Configuration>
    WRITER$ = (org.apache.avro.io.DatumWriter<Configuration>)MODEL$.createDatumWriter(SCHEMA$);

  @Override public void writeExternal(java.io.ObjectOutput out)
    throws java.io.IOException {
    WRITER$.write(this, SpecificData.getEncoder(out));
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumReader<Configuration>
    READER$ = (org.apache.avro.io.DatumReader<Configuration>)MODEL$.createDatumReader(SCHEMA$);

  @Override public void readExternal(java.io.ObjectInput in)
    throws java.io.IOException {
    READER$.read(this, SpecificData.getDecoder(in));
  }

}
