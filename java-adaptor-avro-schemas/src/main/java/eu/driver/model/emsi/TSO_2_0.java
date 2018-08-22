/**
 * Autogenerated by Avro
 *
 * DO NOT EDIT DIRECTLY
 */
package eu.driver.model.emsi;

import org.apache.avro.specific.SpecificData;
import org.apache.avro.message.BinaryMessageEncoder;
import org.apache.avro.message.BinaryMessageDecoder;
import org.apache.avro.message.SchemaStore;

@SuppressWarnings("all")
/** EMSI (TSO) Message (version 2.0) */
@org.apache.avro.specific.AvroGenerated
public class TSO_2_0 extends org.apache.avro.specific.SpecificRecordBase implements org.apache.avro.specific.SpecificRecord {
  private static final long serialVersionUID = 2422434200633991984L;
  public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"record\",\"name\":\"TSO_2_0\",\"namespace\":\"eu.driver.model.emsi\",\"doc\":\"EMSI (TSO) Message (version 2.0)\",\"fields\":[{\"name\":\"CONTEXT\",\"type\":{\"type\":\"record\",\"name\":\"CONTEXT\",\"fields\":[{\"name\":\"ID\",\"type\":\"string\",\"source\":\"element ID\"},{\"name\":\"MODE\",\"type\":\"string\",\"source\":\"element MODE\"},{\"name\":\"MSGTYPE\",\"type\":\"string\",\"source\":\"element MSGTYPE\"},{\"name\":\"CREATION\",\"type\":[\"null\",\"long\"],\"source\":\"element CREATION\"},{\"name\":\"LINK\",\"type\":{\"type\":\"array\",\"items\":{\"type\":\"record\",\"name\":\"LINKCONTEXT\",\"fields\":[{\"name\":\"ID\",\"type\":\"string\",\"source\":\"element ID\"},{\"name\":\"ROLE\",\"type\":[\"null\",\"string\"],\"source\":\"element ROLE\"}]}},\"source\":\"element LINK\"},{\"name\":\"LEVEL\",\"type\":[\"null\",\"string\"],\"source\":\"element LEVEL\"},{\"name\":\"SECLASS\",\"type\":[\"null\",\"string\"],\"source\":\"element SECLASS\"},{\"name\":\"FREETEXT\",\"type\":[\"null\",\"string\"],\"source\":\"element FREETEXT\"},{\"name\":\"URGENCY\",\"type\":[\"null\",\"string\"],\"source\":\"element URGENCY\"},{\"name\":\"ORIGIN\",\"type\":[\"null\",{\"type\":\"record\",\"name\":\"ORIGINCONTEXT\",\"fields\":[{\"name\":\"ORG_ID\",\"type\":\"string\",\"source\":\"element ORG_ID\"},{\"name\":\"USER_ID\",\"type\":[\"null\",\"string\"],\"source\":\"element USER_ID\"},{\"name\":\"NAME\",\"type\":[\"null\",\"string\"],\"source\":\"element NAME\"}]}],\"source\":\"element ORIGIN\"},{\"name\":\"EXTERNAL_INFO\",\"type\":{\"type\":\"array\",\"items\":{\"type\":\"record\",\"name\":\"EXTERNAL_INFOCONTEXT\",\"fields\":[{\"name\":\"FREETEXT\",\"type\":[\"null\",\"string\"],\"source\":\"element FREETEXT\"},{\"name\":\"URI\",\"type\":\"string\",\"source\":\"element URI\"},{\"name\":\"TYPE\",\"type\":[\"null\",\"string\"],\"source\":\"element TYPE\"}]}},\"source\":\"element EXTERNAL_INFO\"}]},\"source\":\"element CONTEXT\"},{\"name\":\"EVENT\",\"type\":[\"null\",{\"type\":\"record\",\"name\":\"EVENT\",\"fields\":[{\"name\":\"ID\",\"type\":\"string\",\"source\":\"element ID\"},{\"name\":\"NAME\",\"type\":[\"null\",\"string\"],\"source\":\"element NAME\"},{\"name\":\"MAIN_EVENT_ID\",\"type\":[\"null\",\"string\"],\"source\":\"element MAIN_EVENT_ID\"},{\"name\":\"ETYPE\",\"type\":[\"null\",{\"type\":\"record\",\"name\":\"ETYPEEVENT\",\"fields\":[{\"name\":\"CATEGORY\",\"type\":{\"type\":\"array\",\"items\":\"string\"},\"source\":\"element CATEGORY\"},{\"name\":\"ACTOR\",\"type\":{\"type\":\"array\",\"items\":\"string\"},\"source\":\"element ACTOR\"},{\"name\":\"LOCTYPE\",\"type\":{\"type\":\"array\",\"items\":\"string\"},\"source\":\"element LOCTYPE\"},{\"name\":\"ENV\",\"type\":{\"type\":\"array\",\"items\":\"string\"},\"source\":\"element ENV\"}]}],\"source\":\"element ETYPE\"},{\"name\":\"SOURCE\",\"type\":[\"null\",\"string\"],\"source\":\"element SOURCE\"},{\"name\":\"SCALE\",\"type\":[\"null\",\"string\"],\"source\":\"element SCALE\"},{\"name\":\"CERTAINTY\",\"type\":[\"null\",\"string\"],\"source\":\"element CERTAINTY\"},{\"name\":\"DECL_DATIME\",\"type\":[\"null\",\"long\"],\"source\":\"element DECL_DATIME\"},{\"name\":\"OCC_DATIME\",\"type\":[\"null\",\"long\"],\"source\":\"element OCC_DATIME\"},{\"name\":\"OBS_DATIME\",\"type\":[\"null\",\"long\"],\"source\":\"element OBS_DATIME\"},{\"name\":\"STATUS\",\"type\":[\"null\",\"string\"],\"source\":\"element STATUS\"},{\"name\":\"RISK_ASSESSMNT\",\"type\":[\"null\",\"string\"],\"source\":\"element RISK_ASSESSMNT\"},{\"name\":\"REFERENCE\",\"type\":{\"type\":\"array\",\"items\":{\"type\":\"record\",\"name\":\"REFERENCEEVENT\",\"fields\":[{\"name\":\"ORG_ID\",\"type\":\"string\",\"source\":\"element ORG_ID\"},{\"name\":\"OTHER_EVENT_ID\",\"type\":{\"type\":\"array\",\"items\":\"string\"},\"source\":\"element OTHER_EVENT_ID\"}]}},\"source\":\"element REFERENCE\"},{\"name\":\"CASUALTIES\",\"type\":{\"type\":\"array\",\"items\":{\"type\":\"record\",\"name\":\"CASUALTIESEVENT\",\"fields\":[{\"name\":\"CONTEXT\",\"type\":\"string\",\"source\":\"element CONTEXT\"},{\"name\":\"DATIME\",\"type\":[\"null\",\"long\"],\"source\":\"element DATIME\"},{\"name\":\"DECONT\",\"type\":[\"null\",\"string\"],\"source\":\"element DECONT\"},{\"name\":\"TRIAGERED\",\"type\":[\"null\",\"string\"],\"source\":\"element TRIAGERED\"},{\"name\":\"TRIAGEYELLOW\",\"type\":[\"null\",\"string\"],\"source\":\"element TRIAGEYELLOW\"},{\"name\":\"TRIAGEGREEN\",\"type\":[\"null\",\"string\"],\"source\":\"element TRIAGEGREEN\"},{\"name\":\"TRIAGEBLACK\",\"type\":[\"null\",\"string\"],\"source\":\"element TRIAGEBLACK\"},{\"name\":\"MISSING\",\"type\":[\"null\",\"string\"],\"source\":\"element MISSING\"}]}},\"source\":\"element CASUALTIES\"},{\"name\":\"EVAC\",\"type\":{\"type\":\"array\",\"items\":{\"type\":\"record\",\"name\":\"EVACEVENT\",\"fields\":[{\"name\":\"DATIME\",\"type\":[\"null\",\"long\"],\"source\":\"element DATIME\"},{\"name\":\"DISPLACED\",\"type\":[\"null\",\"string\"],\"source\":\"element DISPLACED\"},{\"name\":\"EVACUATED\",\"type\":[\"null\",\"string\"],\"source\":\"element EVACUATED\"}]}},\"source\":\"element EVAC\"},{\"name\":\"EGEO\",\"type\":{\"type\":\"array\",\"items\":{\"type\":\"record\",\"name\":\"EGEOEVENT\",\"fields\":[{\"name\":\"DATIME\",\"type\":[\"null\",\"long\"],\"source\":\"element DATIME\"},{\"name\":\"TYPE\",\"type\":\"string\",\"source\":\"element TYPE\"},{\"name\":\"POSITION\",\"type\":{\"type\":\"record\",\"name\":\"POSITION\",\"fields\":[{\"name\":\"LOC_ID\",\"type\":[\"null\",\"string\"],\"source\":\"element LOC_ID\"},{\"name\":\"NAME\",\"type\":[\"null\",\"string\"],\"source\":\"element NAME\"},{\"name\":\"TYPE\",\"type\":[\"null\",\"string\"],\"source\":\"element TYPE\"},{\"name\":\"COORDSYS\",\"type\":[\"null\",\"string\"],\"source\":\"element COORDSYS\"},{\"name\":\"COORD\",\"type\":{\"type\":\"array\",\"items\":{\"type\":\"record\",\"name\":\"COORDType\",\"fields\":[{\"name\":\"LAT\",\"type\":\"double\",\"source\":\"element LAT\"},{\"name\":\"LON\",\"type\":\"double\",\"source\":\"element LON\"},{\"name\":\"HEIGHT\",\"type\":[\"null\",\"double\"],\"source\":\"element HEIGHT\"}]}},\"source\":\"element COORD\"},{\"name\":\"HEIGHT_ROLE\",\"type\":[\"null\",\"string\"],\"source\":\"element HEIGHT_ROLE\"},{\"name\":\"ADDRESS\",\"type\":{\"type\":\"array\",\"items\":\"string\"},\"source\":\"element ADDRESS\"}]},\"source\":\"element POSITION\"},{\"name\":\"WEATHER\",\"type\":{\"type\":\"array\",\"items\":\"string\"},\"source\":\"element WEATHER\"},{\"name\":\"FREETEXT\",\"type\":[\"null\",\"string\"],\"source\":\"element FREETEXT\"},{\"name\":\"ID\",\"type\":[\"null\",\"string\"],\"source\":\"element ID\"},{\"name\":\"STATUS\",\"type\":[\"null\",\"string\"],\"source\":\"element STATUS\"}]}},\"source\":\"element EGEO\"},{\"name\":\"CAUSE\",\"type\":[\"null\",\"string\"],\"source\":\"element CAUSE\"},{\"name\":\"FREETEXT\",\"type\":[\"null\",\"string\"],\"source\":\"element FREETEXT\"}]}],\"source\":\"element EVENT\"},{\"name\":\"RESOURCE\",\"type\":{\"type\":\"array\",\"items\":{\"type\":\"record\",\"name\":\"RESOURCE\",\"fields\":[{\"name\":\"RTYPE\",\"type\":{\"type\":\"record\",\"name\":\"RTYPERESOURCE\",\"fields\":[{\"name\":\"CLASS\",\"type\":\"string\",\"source\":\"element CLASS\"},{\"name\":\"CAPABILITY\",\"type\":{\"type\":\"array\",\"items\":\"string\"},\"source\":\"element CAPABILITY\"},{\"name\":\"CHARACTERISTICS\",\"type\":{\"type\":\"array\",\"items\":\"string\"},\"source\":\"element CHARACTERISTICS\"}]},\"source\":\"element RTYPE\"},{\"name\":\"ID\",\"type\":[\"null\",\"string\"],\"source\":\"element ID\"},{\"name\":\"ORG_ID\",\"type\":[\"null\",\"string\"],\"source\":\"element ORG_ID\"},{\"name\":\"NAME\",\"type\":[\"null\",\"string\"],\"source\":\"element NAME\"},{\"name\":\"FREETEXT\",\"type\":[\"null\",\"string\"],\"source\":\"element FREETEXT\"},{\"name\":\"RGEO\",\"type\":{\"type\":\"array\",\"items\":{\"type\":\"record\",\"name\":\"RGEORESOURCE\",\"fields\":[{\"name\":\"DATIME\",\"type\":[\"null\",\"long\"],\"source\":\"element DATIME\"},{\"name\":\"TYPE\",\"type\":\"string\",\"source\":\"element TYPE\"},{\"name\":\"POSITION\",\"type\":\"POSITION\",\"source\":\"element POSITION\"},{\"name\":\"FREETEXT\",\"type\":[\"null\",\"string\"],\"source\":\"element FREETEXT\"},{\"name\":\"ID\",\"type\":[\"null\",\"string\"],\"source\":\"element ID\"}]}},\"source\":\"element RGEO\"},{\"name\":\"QUANTITY\",\"type\":[\"null\",\"double\"],\"source\":\"element QUANTITY\"},{\"name\":\"UM\",\"type\":[\"null\",\"string\"],\"source\":\"element UM\"},{\"name\":\"STATUS\",\"type\":[\"null\",\"string\"],\"source\":\"element STATUS\"},{\"name\":\"NATIONALITY\",\"type\":[\"null\",\"string\"],\"source\":\"element NATIONALITY\"},{\"name\":\"CONTACT\",\"type\":{\"type\":\"array\",\"items\":{\"type\":\"record\",\"name\":\"CONTACTRESOURCE\",\"fields\":[{\"name\":\"TYPE\",\"type\":\"string\",\"source\":\"element TYPE\"},{\"name\":\"DETAIL\",\"type\":\"string\",\"source\":\"element DETAIL\"}]}},\"source\":\"element CONTACT\"}]}},\"source\":\"element RESOURCE\"},{\"name\":\"MISSION\",\"type\":{\"type\":\"array\",\"items\":{\"type\":\"record\",\"name\":\"MISSION\",\"fields\":[{\"name\":\"TYPE\",\"type\":{\"type\":\"array\",\"items\":\"string\"},\"source\":\"element TYPE\"},{\"name\":\"FREETEXT\",\"type\":[\"null\",\"string\"],\"source\":\"element FREETEXT\"},{\"name\":\"ID\",\"type\":[\"null\",\"string\"],\"source\":\"element ID\"},{\"name\":\"MAIN_MISSION_ID\",\"type\":[\"null\",\"string\"],\"source\":\"element MAIN_MISSION_ID\"},{\"name\":\"ORG_ID\",\"type\":[\"null\",\"string\"],\"source\":\"element ORG_ID\"},{\"name\":\"NAME\",\"type\":[\"null\",\"string\"],\"source\":\"element NAME\"},{\"name\":\"STATUS\",\"type\":[\"null\",\"string\"],\"source\":\"element STATUS\"},{\"name\":\"START_TIME\",\"type\":[\"nul","l\",\"long\"],\"source\":\"element START_TIME\"},{\"name\":\"END_TIME\",\"type\":[\"null\",\"long\"],\"source\":\"element END_TIME\"},{\"name\":\"RESOURCE_ID\",\"type\":{\"type\":\"array\",\"items\":\"string\"},\"source\":\"element RESOURCE_ID\"},{\"name\":\"PARENT_MISSION_ID\",\"type\":{\"type\":\"array\",\"items\":\"string\"},\"source\":\"element PARENT_MISSION_ID\"},{\"name\":\"CHILD_MISSION_ID\",\"type\":{\"type\":\"array\",\"items\":\"string\"},\"source\":\"element CHILD_MISSION_ID\"},{\"name\":\"POSITION\",\"type\":[\"null\",\"POSITION\"],\"source\":\"element POSITION\"},{\"name\":\"PRIORITY\",\"type\":[\"null\",\"string\"],\"source\":\"element PRIORITY\"}]}},\"source\":\"element MISSION\"}],\"source\":\"document\"}");
  public static org.apache.avro.Schema getClassSchema() { return SCHEMA$; }

  private static SpecificData MODEL$ = new SpecificData();

  private static final BinaryMessageEncoder<TSO_2_0> ENCODER =
      new BinaryMessageEncoder<TSO_2_0>(MODEL$, SCHEMA$);

  private static final BinaryMessageDecoder<TSO_2_0> DECODER =
      new BinaryMessageDecoder<TSO_2_0>(MODEL$, SCHEMA$);

  /**
   * Return the BinaryMessageDecoder instance used by this class.
   */
  public static BinaryMessageDecoder<TSO_2_0> getDecoder() {
    return DECODER;
  }

  /**
   * Create a new BinaryMessageDecoder instance for this class that uses the specified {@link SchemaStore}.
   * @param resolver a {@link SchemaStore} used to find schemas by fingerprint
   */
  public static BinaryMessageDecoder<TSO_2_0> createDecoder(SchemaStore resolver) {
    return new BinaryMessageDecoder<TSO_2_0>(MODEL$, SCHEMA$, resolver);
  }

  /** Serializes this TSO_2_0 to a ByteBuffer. */
  public java.nio.ByteBuffer toByteBuffer() throws java.io.IOException {
    return ENCODER.encode(this);
  }

  /** Deserializes a TSO_2_0 from a ByteBuffer. */
  public static TSO_2_0 fromByteBuffer(
      java.nio.ByteBuffer b) throws java.io.IOException {
    return DECODER.decode(b);
  }

  @Deprecated public eu.driver.model.emsi.CONTEXT CONTEXT;
  @Deprecated public eu.driver.model.emsi.EVENT EVENT;
  @Deprecated public java.util.List<eu.driver.model.emsi.RESOURCE> RESOURCE;
  @Deprecated public java.util.List<eu.driver.model.emsi.MISSION> MISSION;

  /**
   * Default constructor.  Note that this does not initialize fields
   * to their default values from the schema.  If that is desired then
   * one should use <code>newBuilder()</code>.
   */
  public TSO_2_0() {}

  /**
   * All-args constructor.
   * @param CONTEXT The new value for CONTEXT
   * @param EVENT The new value for EVENT
   * @param RESOURCE The new value for RESOURCE
   * @param MISSION The new value for MISSION
   */
  public TSO_2_0(eu.driver.model.emsi.CONTEXT CONTEXT, eu.driver.model.emsi.EVENT EVENT, java.util.List<eu.driver.model.emsi.RESOURCE> RESOURCE, java.util.List<eu.driver.model.emsi.MISSION> MISSION) {
    this.CONTEXT = CONTEXT;
    this.EVENT = EVENT;
    this.RESOURCE = RESOURCE;
    this.MISSION = MISSION;
  }

  public org.apache.avro.Schema getSchema() { return SCHEMA$; }
  // Used by DatumWriter.  Applications should not call.
  public java.lang.Object get(int field$) {
    switch (field$) {
    case 0: return CONTEXT;
    case 1: return EVENT;
    case 2: return RESOURCE;
    case 3: return MISSION;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }

  // Used by DatumReader.  Applications should not call.
  @SuppressWarnings(value="unchecked")
  public void put(int field$, java.lang.Object value$) {
    switch (field$) {
    case 0: CONTEXT = (eu.driver.model.emsi.CONTEXT)value$; break;
    case 1: EVENT = (eu.driver.model.emsi.EVENT)value$; break;
    case 2: RESOURCE = (java.util.List<eu.driver.model.emsi.RESOURCE>)value$; break;
    case 3: MISSION = (java.util.List<eu.driver.model.emsi.MISSION>)value$; break;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }

  /**
   * Gets the value of the 'CONTEXT' field.
   * @return The value of the 'CONTEXT' field.
   */
  public eu.driver.model.emsi.CONTEXT getCONTEXT() {
    return CONTEXT;
  }

  /**
   * Sets the value of the 'CONTEXT' field.
   * @param value the value to set.
   */
  public void setCONTEXT(eu.driver.model.emsi.CONTEXT value) {
    this.CONTEXT = value;
  }

  /**
   * Gets the value of the 'EVENT' field.
   * @return The value of the 'EVENT' field.
   */
  public eu.driver.model.emsi.EVENT getEVENT() {
    return EVENT;
  }

  /**
   * Sets the value of the 'EVENT' field.
   * @param value the value to set.
   */
  public void setEVENT(eu.driver.model.emsi.EVENT value) {
    this.EVENT = value;
  }

  /**
   * Gets the value of the 'RESOURCE' field.
   * @return The value of the 'RESOURCE' field.
   */
  public java.util.List<eu.driver.model.emsi.RESOURCE> getRESOURCE() {
    return RESOURCE;
  }

  /**
   * Sets the value of the 'RESOURCE' field.
   * @param value the value to set.
   */
  public void setRESOURCE(java.util.List<eu.driver.model.emsi.RESOURCE> value) {
    this.RESOURCE = value;
  }

  /**
   * Gets the value of the 'MISSION' field.
   * @return The value of the 'MISSION' field.
   */
  public java.util.List<eu.driver.model.emsi.MISSION> getMISSION() {
    return MISSION;
  }

  /**
   * Sets the value of the 'MISSION' field.
   * @param value the value to set.
   */
  public void setMISSION(java.util.List<eu.driver.model.emsi.MISSION> value) {
    this.MISSION = value;
  }

  /**
   * Creates a new TSO_2_0 RecordBuilder.
   * @return A new TSO_2_0 RecordBuilder
   */
  public static eu.driver.model.emsi.TSO_2_0.Builder newBuilder() {
    return new eu.driver.model.emsi.TSO_2_0.Builder();
  }

  /**
   * Creates a new TSO_2_0 RecordBuilder by copying an existing Builder.
   * @param other The existing builder to copy.
   * @return A new TSO_2_0 RecordBuilder
   */
  public static eu.driver.model.emsi.TSO_2_0.Builder newBuilder(eu.driver.model.emsi.TSO_2_0.Builder other) {
    return new eu.driver.model.emsi.TSO_2_0.Builder(other);
  }

  /**
   * Creates a new TSO_2_0 RecordBuilder by copying an existing TSO_2_0 instance.
   * @param other The existing instance to copy.
   * @return A new TSO_2_0 RecordBuilder
   */
  public static eu.driver.model.emsi.TSO_2_0.Builder newBuilder(eu.driver.model.emsi.TSO_2_0 other) {
    return new eu.driver.model.emsi.TSO_2_0.Builder(other);
  }

  /**
   * RecordBuilder for TSO_2_0 instances.
   */
  public static class Builder extends org.apache.avro.specific.SpecificRecordBuilderBase<TSO_2_0>
    implements org.apache.avro.data.RecordBuilder<TSO_2_0> {

    private eu.driver.model.emsi.CONTEXT CONTEXT;
    private eu.driver.model.emsi.CONTEXT.Builder CONTEXTBuilder;
    private eu.driver.model.emsi.EVENT EVENT;
    private eu.driver.model.emsi.EVENT.Builder EVENTBuilder;
    private java.util.List<eu.driver.model.emsi.RESOURCE> RESOURCE;
    private java.util.List<eu.driver.model.emsi.MISSION> MISSION;

    /** Creates a new Builder */
    private Builder() {
      super(SCHEMA$);
    }

    /**
     * Creates a Builder by copying an existing Builder.
     * @param other The existing Builder to copy.
     */
    private Builder(eu.driver.model.emsi.TSO_2_0.Builder other) {
      super(other);
      if (isValidValue(fields()[0], other.CONTEXT)) {
        this.CONTEXT = data().deepCopy(fields()[0].schema(), other.CONTEXT);
        fieldSetFlags()[0] = true;
      }
      if (other.hasCONTEXTBuilder()) {
        this.CONTEXTBuilder = eu.driver.model.emsi.CONTEXT.newBuilder(other.getCONTEXTBuilder());
      }
      if (isValidValue(fields()[1], other.EVENT)) {
        this.EVENT = data().deepCopy(fields()[1].schema(), other.EVENT);
        fieldSetFlags()[1] = true;
      }
      if (other.hasEVENTBuilder()) {
        this.EVENTBuilder = eu.driver.model.emsi.EVENT.newBuilder(other.getEVENTBuilder());
      }
      if (isValidValue(fields()[2], other.RESOURCE)) {
        this.RESOURCE = data().deepCopy(fields()[2].schema(), other.RESOURCE);
        fieldSetFlags()[2] = true;
      }
      if (isValidValue(fields()[3], other.MISSION)) {
        this.MISSION = data().deepCopy(fields()[3].schema(), other.MISSION);
        fieldSetFlags()[3] = true;
      }
    }

    /**
     * Creates a Builder by copying an existing TSO_2_0 instance
     * @param other The existing instance to copy.
     */
    private Builder(eu.driver.model.emsi.TSO_2_0 other) {
            super(SCHEMA$);
      if (isValidValue(fields()[0], other.CONTEXT)) {
        this.CONTEXT = data().deepCopy(fields()[0].schema(), other.CONTEXT);
        fieldSetFlags()[0] = true;
      }
      this.CONTEXTBuilder = null;
      if (isValidValue(fields()[1], other.EVENT)) {
        this.EVENT = data().deepCopy(fields()[1].schema(), other.EVENT);
        fieldSetFlags()[1] = true;
      }
      this.EVENTBuilder = null;
      if (isValidValue(fields()[2], other.RESOURCE)) {
        this.RESOURCE = data().deepCopy(fields()[2].schema(), other.RESOURCE);
        fieldSetFlags()[2] = true;
      }
      if (isValidValue(fields()[3], other.MISSION)) {
        this.MISSION = data().deepCopy(fields()[3].schema(), other.MISSION);
        fieldSetFlags()[3] = true;
      }
    }

    /**
      * Gets the value of the 'CONTEXT' field.
      * @return The value.
      */
    public eu.driver.model.emsi.CONTEXT getCONTEXT() {
      return CONTEXT;
    }

    /**
      * Sets the value of the 'CONTEXT' field.
      * @param value The value of 'CONTEXT'.
      * @return This builder.
      */
    public eu.driver.model.emsi.TSO_2_0.Builder setCONTEXT(eu.driver.model.emsi.CONTEXT value) {
      validate(fields()[0], value);
      this.CONTEXTBuilder = null;
      this.CONTEXT = value;
      fieldSetFlags()[0] = true;
      return this;
    }

    /**
      * Checks whether the 'CONTEXT' field has been set.
      * @return True if the 'CONTEXT' field has been set, false otherwise.
      */
    public boolean hasCONTEXT() {
      return fieldSetFlags()[0];
    }

    /**
     * Gets the Builder instance for the 'CONTEXT' field and creates one if it doesn't exist yet.
     * @return This builder.
     */
    public eu.driver.model.emsi.CONTEXT.Builder getCONTEXTBuilder() {
      if (CONTEXTBuilder == null) {
        if (hasCONTEXT()) {
          setCONTEXTBuilder(eu.driver.model.emsi.CONTEXT.newBuilder(CONTEXT));
        } else {
          setCONTEXTBuilder(eu.driver.model.emsi.CONTEXT.newBuilder());
        }
      }
      return CONTEXTBuilder;
    }

    /**
     * Sets the Builder instance for the 'CONTEXT' field
     * @param value The builder instance that must be set.
     * @return This builder.
     */
    public eu.driver.model.emsi.TSO_2_0.Builder setCONTEXTBuilder(eu.driver.model.emsi.CONTEXT.Builder value) {
      clearCONTEXT();
      CONTEXTBuilder = value;
      return this;
    }

    /**
     * Checks whether the 'CONTEXT' field has an active Builder instance
     * @return True if the 'CONTEXT' field has an active Builder instance
     */
    public boolean hasCONTEXTBuilder() {
      return CONTEXTBuilder != null;
    }

    /**
      * Clears the value of the 'CONTEXT' field.
      * @return This builder.
      */
    public eu.driver.model.emsi.TSO_2_0.Builder clearCONTEXT() {
      CONTEXT = null;
      CONTEXTBuilder = null;
      fieldSetFlags()[0] = false;
      return this;
    }

    /**
      * Gets the value of the 'EVENT' field.
      * @return The value.
      */
    public eu.driver.model.emsi.EVENT getEVENT() {
      return EVENT;
    }

    /**
      * Sets the value of the 'EVENT' field.
      * @param value The value of 'EVENT'.
      * @return This builder.
      */
    public eu.driver.model.emsi.TSO_2_0.Builder setEVENT(eu.driver.model.emsi.EVENT value) {
      validate(fields()[1], value);
      this.EVENTBuilder = null;
      this.EVENT = value;
      fieldSetFlags()[1] = true;
      return this;
    }

    /**
      * Checks whether the 'EVENT' field has been set.
      * @return True if the 'EVENT' field has been set, false otherwise.
      */
    public boolean hasEVENT() {
      return fieldSetFlags()[1];
    }

    /**
     * Gets the Builder instance for the 'EVENT' field and creates one if it doesn't exist yet.
     * @return This builder.
     */
    public eu.driver.model.emsi.EVENT.Builder getEVENTBuilder() {
      if (EVENTBuilder == null) {
        if (hasEVENT()) {
          setEVENTBuilder(eu.driver.model.emsi.EVENT.newBuilder(EVENT));
        } else {
          setEVENTBuilder(eu.driver.model.emsi.EVENT.newBuilder());
        }
      }
      return EVENTBuilder;
    }

    /**
     * Sets the Builder instance for the 'EVENT' field
     * @param value The builder instance that must be set.
     * @return This builder.
     */
    public eu.driver.model.emsi.TSO_2_0.Builder setEVENTBuilder(eu.driver.model.emsi.EVENT.Builder value) {
      clearEVENT();
      EVENTBuilder = value;
      return this;
    }

    /**
     * Checks whether the 'EVENT' field has an active Builder instance
     * @return True if the 'EVENT' field has an active Builder instance
     */
    public boolean hasEVENTBuilder() {
      return EVENTBuilder != null;
    }

    /**
      * Clears the value of the 'EVENT' field.
      * @return This builder.
      */
    public eu.driver.model.emsi.TSO_2_0.Builder clearEVENT() {
      EVENT = null;
      EVENTBuilder = null;
      fieldSetFlags()[1] = false;
      return this;
    }

    /**
      * Gets the value of the 'RESOURCE' field.
      * @return The value.
      */
    public java.util.List<eu.driver.model.emsi.RESOURCE> getRESOURCE() {
      return RESOURCE;
    }

    /**
      * Sets the value of the 'RESOURCE' field.
      * @param value The value of 'RESOURCE'.
      * @return This builder.
      */
    public eu.driver.model.emsi.TSO_2_0.Builder setRESOURCE(java.util.List<eu.driver.model.emsi.RESOURCE> value) {
      validate(fields()[2], value);
      this.RESOURCE = value;
      fieldSetFlags()[2] = true;
      return this;
    }

    /**
      * Checks whether the 'RESOURCE' field has been set.
      * @return True if the 'RESOURCE' field has been set, false otherwise.
      */
    public boolean hasRESOURCE() {
      return fieldSetFlags()[2];
    }


    /**
      * Clears the value of the 'RESOURCE' field.
      * @return This builder.
      */
    public eu.driver.model.emsi.TSO_2_0.Builder clearRESOURCE() {
      RESOURCE = null;
      fieldSetFlags()[2] = false;
      return this;
    }

    /**
      * Gets the value of the 'MISSION' field.
      * @return The value.
      */
    public java.util.List<eu.driver.model.emsi.MISSION> getMISSION() {
      return MISSION;
    }

    /**
      * Sets the value of the 'MISSION' field.
      * @param value The value of 'MISSION'.
      * @return This builder.
      */
    public eu.driver.model.emsi.TSO_2_0.Builder setMISSION(java.util.List<eu.driver.model.emsi.MISSION> value) {
      validate(fields()[3], value);
      this.MISSION = value;
      fieldSetFlags()[3] = true;
      return this;
    }

    /**
      * Checks whether the 'MISSION' field has been set.
      * @return True if the 'MISSION' field has been set, false otherwise.
      */
    public boolean hasMISSION() {
      return fieldSetFlags()[3];
    }


    /**
      * Clears the value of the 'MISSION' field.
      * @return This builder.
      */
    public eu.driver.model.emsi.TSO_2_0.Builder clearMISSION() {
      MISSION = null;
      fieldSetFlags()[3] = false;
      return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public TSO_2_0 build() {
      try {
        TSO_2_0 record = new TSO_2_0();
        if (CONTEXTBuilder != null) {
          record.CONTEXT = this.CONTEXTBuilder.build();
        } else {
          record.CONTEXT = fieldSetFlags()[0] ? this.CONTEXT : (eu.driver.model.emsi.CONTEXT) defaultValue(fields()[0]);
        }
        if (EVENTBuilder != null) {
          record.EVENT = this.EVENTBuilder.build();
        } else {
          record.EVENT = fieldSetFlags()[1] ? this.EVENT : (eu.driver.model.emsi.EVENT) defaultValue(fields()[1]);
        }
        record.RESOURCE = fieldSetFlags()[2] ? this.RESOURCE : (java.util.List<eu.driver.model.emsi.RESOURCE>) defaultValue(fields()[2]);
        record.MISSION = fieldSetFlags()[3] ? this.MISSION : (java.util.List<eu.driver.model.emsi.MISSION>) defaultValue(fields()[3]);
        return record;
      } catch (java.lang.Exception e) {
        throw new org.apache.avro.AvroRuntimeException(e);
      }
    }
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumWriter<TSO_2_0>
    WRITER$ = (org.apache.avro.io.DatumWriter<TSO_2_0>)MODEL$.createDatumWriter(SCHEMA$);

  @Override public void writeExternal(java.io.ObjectOutput out)
    throws java.io.IOException {
    WRITER$.write(this, SpecificData.getEncoder(out));
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumReader<TSO_2_0>
    READER$ = (org.apache.avro.io.DatumReader<TSO_2_0>)MODEL$.createDatumReader(SCHEMA$);

  @Override public void readExternal(java.io.ObjectInput in)
    throws java.io.IOException {
    READER$.read(this, SpecificData.getDecoder(in));
  }

}
