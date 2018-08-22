/**
 * Autogenerated by Avro
 *
 * DO NOT EDIT DIRECTLY
 */
package eu.driver.model.mlp;

import org.apache.avro.specific.SpecificData;
import org.apache.avro.message.BinaryMessageEncoder;
import org.apache.avro.message.BinaryMessageDecoder;
import org.apache.avro.message.SchemaStore;

@SuppressWarnings("all")
@org.apache.avro.specific.AvroGenerated
public class Msid extends org.apache.avro.specific.SpecificRecordBase implements org.apache.avro.specific.SpecificRecord {
  private static final long serialVersionUID = -3486209882734824680L;
  public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"record\",\"name\":\"Msid\",\"namespace\":\"eu.driver.model.mlp\",\"fields\":[{\"name\":\"msid\",\"type\":\"string\"},{\"name\":\"attr_type\",\"type\":{\"type\":\"enum\",\"name\":\"AttrType\",\"symbols\":[\"MSISDN\",\"IMSI\",\"IMEI\",\"MIN\",\"MDN\",\"EME_MSID\",\"ASID\",\"OPE_ID\",\"IPV4\",\"IPV6\",\"SESSID\"]}},{\"name\":\"attr_enc\",\"type\":{\"type\":\"enum\",\"name\":\"AttrEnc\",\"symbols\":[\"ASC\",\"CRP\"]},\"namespace\":\"eu.driver.model.mlp\"}]}");
  public static org.apache.avro.Schema getClassSchema() { return SCHEMA$; }

  private static SpecificData MODEL$ = new SpecificData();

  private static final BinaryMessageEncoder<Msid> ENCODER =
      new BinaryMessageEncoder<Msid>(MODEL$, SCHEMA$);

  private static final BinaryMessageDecoder<Msid> DECODER =
      new BinaryMessageDecoder<Msid>(MODEL$, SCHEMA$);

  /**
   * Return the BinaryMessageDecoder instance used by this class.
   */
  public static BinaryMessageDecoder<Msid> getDecoder() {
    return DECODER;
  }

  /**
   * Create a new BinaryMessageDecoder instance for this class that uses the specified {@link SchemaStore}.
   * @param resolver a {@link SchemaStore} used to find schemas by fingerprint
   */
  public static BinaryMessageDecoder<Msid> createDecoder(SchemaStore resolver) {
    return new BinaryMessageDecoder<Msid>(MODEL$, SCHEMA$, resolver);
  }

  /** Serializes this Msid to a ByteBuffer. */
  public java.nio.ByteBuffer toByteBuffer() throws java.io.IOException {
    return ENCODER.encode(this);
  }

  /** Deserializes a Msid from a ByteBuffer. */
  public static Msid fromByteBuffer(
      java.nio.ByteBuffer b) throws java.io.IOException {
    return DECODER.decode(b);
  }

  @Deprecated public java.lang.CharSequence msid;
  @Deprecated public eu.driver.model.mlp.AttrType attr_type;
  @Deprecated public eu.driver.model.mlp.AttrEnc attr_enc;

  /**
   * Default constructor.  Note that this does not initialize fields
   * to their default values from the schema.  If that is desired then
   * one should use <code>newBuilder()</code>.
   */
  public Msid() {}

  /**
   * All-args constructor.
   * @param msid The new value for msid
   * @param attr_type The new value for attr_type
   * @param attr_enc The new value for attr_enc
   */
  public Msid(java.lang.CharSequence msid, eu.driver.model.mlp.AttrType attr_type, eu.driver.model.mlp.AttrEnc attr_enc) {
    this.msid = msid;
    this.attr_type = attr_type;
    this.attr_enc = attr_enc;
  }

  public org.apache.avro.Schema getSchema() { return SCHEMA$; }
  // Used by DatumWriter.  Applications should not call.
  public java.lang.Object get(int field$) {
    switch (field$) {
    case 0: return msid;
    case 1: return attr_type;
    case 2: return attr_enc;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }

  // Used by DatumReader.  Applications should not call.
  @SuppressWarnings(value="unchecked")
  public void put(int field$, java.lang.Object value$) {
    switch (field$) {
    case 0: msid = (java.lang.CharSequence)value$; break;
    case 1: attr_type = (eu.driver.model.mlp.AttrType)value$; break;
    case 2: attr_enc = (eu.driver.model.mlp.AttrEnc)value$; break;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }

  /**
   * Gets the value of the 'msid' field.
   * @return The value of the 'msid' field.
   */
  public java.lang.CharSequence getMsid() {
    return msid;
  }

  /**
   * Sets the value of the 'msid' field.
   * @param value the value to set.
   */
  public void setMsid(java.lang.CharSequence value) {
    this.msid = value;
  }

  /**
   * Gets the value of the 'attr_type' field.
   * @return The value of the 'attr_type' field.
   */
  public eu.driver.model.mlp.AttrType getAttrType() {
    return attr_type;
  }

  /**
   * Sets the value of the 'attr_type' field.
   * @param value the value to set.
   */
  public void setAttrType(eu.driver.model.mlp.AttrType value) {
    this.attr_type = value;
  }

  /**
   * Gets the value of the 'attr_enc' field.
   * @return The value of the 'attr_enc' field.
   */
  public eu.driver.model.mlp.AttrEnc getAttrEnc() {
    return attr_enc;
  }

  /**
   * Sets the value of the 'attr_enc' field.
   * @param value the value to set.
   */
  public void setAttrEnc(eu.driver.model.mlp.AttrEnc value) {
    this.attr_enc = value;
  }

  /**
   * Creates a new Msid RecordBuilder.
   * @return A new Msid RecordBuilder
   */
  public static eu.driver.model.mlp.Msid.Builder newBuilder() {
    return new eu.driver.model.mlp.Msid.Builder();
  }

  /**
   * Creates a new Msid RecordBuilder by copying an existing Builder.
   * @param other The existing builder to copy.
   * @return A new Msid RecordBuilder
   */
  public static eu.driver.model.mlp.Msid.Builder newBuilder(eu.driver.model.mlp.Msid.Builder other) {
    return new eu.driver.model.mlp.Msid.Builder(other);
  }

  /**
   * Creates a new Msid RecordBuilder by copying an existing Msid instance.
   * @param other The existing instance to copy.
   * @return A new Msid RecordBuilder
   */
  public static eu.driver.model.mlp.Msid.Builder newBuilder(eu.driver.model.mlp.Msid other) {
    return new eu.driver.model.mlp.Msid.Builder(other);
  }

  /**
   * RecordBuilder for Msid instances.
   */
  public static class Builder extends org.apache.avro.specific.SpecificRecordBuilderBase<Msid>
    implements org.apache.avro.data.RecordBuilder<Msid> {

    private java.lang.CharSequence msid;
    private eu.driver.model.mlp.AttrType attr_type;
    private eu.driver.model.mlp.AttrEnc attr_enc;

    /** Creates a new Builder */
    private Builder() {
      super(SCHEMA$);
    }

    /**
     * Creates a Builder by copying an existing Builder.
     * @param other The existing Builder to copy.
     */
    private Builder(eu.driver.model.mlp.Msid.Builder other) {
      super(other);
      if (isValidValue(fields()[0], other.msid)) {
        this.msid = data().deepCopy(fields()[0].schema(), other.msid);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.attr_type)) {
        this.attr_type = data().deepCopy(fields()[1].schema(), other.attr_type);
        fieldSetFlags()[1] = true;
      }
      if (isValidValue(fields()[2], other.attr_enc)) {
        this.attr_enc = data().deepCopy(fields()[2].schema(), other.attr_enc);
        fieldSetFlags()[2] = true;
      }
    }

    /**
     * Creates a Builder by copying an existing Msid instance
     * @param other The existing instance to copy.
     */
    private Builder(eu.driver.model.mlp.Msid other) {
            super(SCHEMA$);
      if (isValidValue(fields()[0], other.msid)) {
        this.msid = data().deepCopy(fields()[0].schema(), other.msid);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.attr_type)) {
        this.attr_type = data().deepCopy(fields()[1].schema(), other.attr_type);
        fieldSetFlags()[1] = true;
      }
      if (isValidValue(fields()[2], other.attr_enc)) {
        this.attr_enc = data().deepCopy(fields()[2].schema(), other.attr_enc);
        fieldSetFlags()[2] = true;
      }
    }

    /**
      * Gets the value of the 'msid' field.
      * @return The value.
      */
    public java.lang.CharSequence getMsid() {
      return msid;
    }

    /**
      * Sets the value of the 'msid' field.
      * @param value The value of 'msid'.
      * @return This builder.
      */
    public eu.driver.model.mlp.Msid.Builder setMsid(java.lang.CharSequence value) {
      validate(fields()[0], value);
      this.msid = value;
      fieldSetFlags()[0] = true;
      return this;
    }

    /**
      * Checks whether the 'msid' field has been set.
      * @return True if the 'msid' field has been set, false otherwise.
      */
    public boolean hasMsid() {
      return fieldSetFlags()[0];
    }


    /**
      * Clears the value of the 'msid' field.
      * @return This builder.
      */
    public eu.driver.model.mlp.Msid.Builder clearMsid() {
      msid = null;
      fieldSetFlags()[0] = false;
      return this;
    }

    /**
      * Gets the value of the 'attr_type' field.
      * @return The value.
      */
    public eu.driver.model.mlp.AttrType getAttrType() {
      return attr_type;
    }

    /**
      * Sets the value of the 'attr_type' field.
      * @param value The value of 'attr_type'.
      * @return This builder.
      */
    public eu.driver.model.mlp.Msid.Builder setAttrType(eu.driver.model.mlp.AttrType value) {
      validate(fields()[1], value);
      this.attr_type = value;
      fieldSetFlags()[1] = true;
      return this;
    }

    /**
      * Checks whether the 'attr_type' field has been set.
      * @return True if the 'attr_type' field has been set, false otherwise.
      */
    public boolean hasAttrType() {
      return fieldSetFlags()[1];
    }


    /**
      * Clears the value of the 'attr_type' field.
      * @return This builder.
      */
    public eu.driver.model.mlp.Msid.Builder clearAttrType() {
      attr_type = null;
      fieldSetFlags()[1] = false;
      return this;
    }

    /**
      * Gets the value of the 'attr_enc' field.
      * @return The value.
      */
    public eu.driver.model.mlp.AttrEnc getAttrEnc() {
      return attr_enc;
    }

    /**
      * Sets the value of the 'attr_enc' field.
      * @param value The value of 'attr_enc'.
      * @return This builder.
      */
    public eu.driver.model.mlp.Msid.Builder setAttrEnc(eu.driver.model.mlp.AttrEnc value) {
      validate(fields()[2], value);
      this.attr_enc = value;
      fieldSetFlags()[2] = true;
      return this;
    }

    /**
      * Checks whether the 'attr_enc' field has been set.
      * @return True if the 'attr_enc' field has been set, false otherwise.
      */
    public boolean hasAttrEnc() {
      return fieldSetFlags()[2];
    }


    /**
      * Clears the value of the 'attr_enc' field.
      * @return This builder.
      */
    public eu.driver.model.mlp.Msid.Builder clearAttrEnc() {
      attr_enc = null;
      fieldSetFlags()[2] = false;
      return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Msid build() {
      try {
        Msid record = new Msid();
        record.msid = fieldSetFlags()[0] ? this.msid : (java.lang.CharSequence) defaultValue(fields()[0]);
        record.attr_type = fieldSetFlags()[1] ? this.attr_type : (eu.driver.model.mlp.AttrType) defaultValue(fields()[1]);
        record.attr_enc = fieldSetFlags()[2] ? this.attr_enc : (eu.driver.model.mlp.AttrEnc) defaultValue(fields()[2]);
        return record;
      } catch (java.lang.Exception e) {
        throw new org.apache.avro.AvroRuntimeException(e);
      }
    }
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumWriter<Msid>
    WRITER$ = (org.apache.avro.io.DatumWriter<Msid>)MODEL$.createDatumWriter(SCHEMA$);

  @Override public void writeExternal(java.io.ObjectOutput out)
    throws java.io.IOException {
    WRITER$.write(this, SpecificData.getEncoder(out));
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumReader<Msid>
    READER$ = (org.apache.avro.io.DatumReader<Msid>)MODEL$.createDatumReader(SCHEMA$);

  @Override public void readExternal(java.io.ObjectInput in)
    throws java.io.IOException {
    READER$.read(this, SpecificData.getDecoder(in));
  }

}
