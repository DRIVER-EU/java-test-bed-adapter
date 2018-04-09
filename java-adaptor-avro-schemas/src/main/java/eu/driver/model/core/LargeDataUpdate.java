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
/** Message for indicating large data files updated and uploaded to a central server */
@org.apache.avro.specific.AvroGenerated
public class LargeDataUpdate extends org.apache.avro.specific.SpecificRecordBase implements org.apache.avro.specific.SpecificRecord {
  private static final long serialVersionUID = 4103124082644801196L;
  public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"record\",\"name\":\"LargeDataUpdate\",\"namespace\":\"eu.driver.model.core\",\"doc\":\"Message for indicating large data files updated and uploaded to a central server\",\"fields\":[{\"name\":\"url\",\"type\":\"string\",\"doc\":\"Link of where to download the data file from\"},{\"name\":\"title\",\"type\":[\"null\",\"string\"],\"doc\":\"Optional title of the data file, e.g. to serve it via WMS or otherwise\",\"default\":null},{\"name\":\"description\",\"type\":[\"null\",\"string\"],\"doc\":\"Optional description of the file\",\"default\":null},{\"name\":\"dataType\",\"type\":{\"type\":\"enum\",\"name\":\"DataType\",\"symbols\":[\"msword\",\"ogg\",\"pdf\",\"excel\",\"powerpoint\",\"zip\",\"audio_mpeg\",\"audio_vorbis\",\"image_bmp\",\"image_gif\",\"image_geotiff\",\"image_jpeg\",\"image_png\",\"json\",\"geojson\",\"text_plain\",\"video_mpeg\",\"video_msvideo\",\"video_avi\",\"other\"]},\"doc\":\"The type of data that is sent\"}]}");
  public static org.apache.avro.Schema getClassSchema() { return SCHEMA$; }

  private static SpecificData MODEL$ = new SpecificData();

  private static final BinaryMessageEncoder<LargeDataUpdate> ENCODER =
      new BinaryMessageEncoder<LargeDataUpdate>(MODEL$, SCHEMA$);

  private static final BinaryMessageDecoder<LargeDataUpdate> DECODER =
      new BinaryMessageDecoder<LargeDataUpdate>(MODEL$, SCHEMA$);

  /**
   * Return the BinaryMessageDecoder instance used by this class.
   */
  public static BinaryMessageDecoder<LargeDataUpdate> getDecoder() {
    return DECODER;
  }

  /**
   * Create a new BinaryMessageDecoder instance for this class that uses the specified {@link SchemaStore}.
   * @param resolver a {@link SchemaStore} used to find schemas by fingerprint
   */
  public static BinaryMessageDecoder<LargeDataUpdate> createDecoder(SchemaStore resolver) {
    return new BinaryMessageDecoder<LargeDataUpdate>(MODEL$, SCHEMA$, resolver);
  }

  /** Serializes this LargeDataUpdate to a ByteBuffer. */
  public java.nio.ByteBuffer toByteBuffer() throws java.io.IOException {
    return ENCODER.encode(this);
  }

  /** Deserializes a LargeDataUpdate from a ByteBuffer. */
  public static LargeDataUpdate fromByteBuffer(
      java.nio.ByteBuffer b) throws java.io.IOException {
    return DECODER.decode(b);
  }

  /** Link of where to download the data file from */
  @Deprecated public java.lang.CharSequence url;
  /** Optional title of the data file, e.g. to serve it via WMS or otherwise */
  @Deprecated public java.lang.CharSequence title;
  /** Optional description of the file */
  @Deprecated public java.lang.CharSequence description;
  /** The type of data that is sent */
  @Deprecated public eu.driver.model.core.DataType dataType;

  /**
   * Default constructor.  Note that this does not initialize fields
   * to their default values from the schema.  If that is desired then
   * one should use <code>newBuilder()</code>.
   */
  public LargeDataUpdate() {}

  /**
   * All-args constructor.
   * @param url Link of where to download the data file from
   * @param title Optional title of the data file, e.g. to serve it via WMS or otherwise
   * @param description Optional description of the file
   * @param dataType The type of data that is sent
   */
  public LargeDataUpdate(java.lang.CharSequence url, java.lang.CharSequence title, java.lang.CharSequence description, eu.driver.model.core.DataType dataType) {
    this.url = url;
    this.title = title;
    this.description = description;
    this.dataType = dataType;
  }

  public org.apache.avro.Schema getSchema() { return SCHEMA$; }
  // Used by DatumWriter.  Applications should not call.
  public java.lang.Object get(int field$) {
    switch (field$) {
    case 0: return url;
    case 1: return title;
    case 2: return description;
    case 3: return dataType;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }

  // Used by DatumReader.  Applications should not call.
  @SuppressWarnings(value="unchecked")
  public void put(int field$, java.lang.Object value$) {
    switch (field$) {
    case 0: url = (java.lang.CharSequence)value$; break;
    case 1: title = (java.lang.CharSequence)value$; break;
    case 2: description = (java.lang.CharSequence)value$; break;
    case 3: dataType = (eu.driver.model.core.DataType)value$; break;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }

  /**
   * Gets the value of the 'url' field.
   * @return Link of where to download the data file from
   */
  public java.lang.CharSequence getUrl() {
    return url;
  }

  /**
   * Sets the value of the 'url' field.
   * Link of where to download the data file from
   * @param value the value to set.
   */
  public void setUrl(java.lang.CharSequence value) {
    this.url = value;
  }

  /**
   * Gets the value of the 'title' field.
   * @return Optional title of the data file, e.g. to serve it via WMS or otherwise
   */
  public java.lang.CharSequence getTitle() {
    return title;
  }

  /**
   * Sets the value of the 'title' field.
   * Optional title of the data file, e.g. to serve it via WMS or otherwise
   * @param value the value to set.
   */
  public void setTitle(java.lang.CharSequence value) {
    this.title = value;
  }

  /**
   * Gets the value of the 'description' field.
   * @return Optional description of the file
   */
  public java.lang.CharSequence getDescription() {
    return description;
  }

  /**
   * Sets the value of the 'description' field.
   * Optional description of the file
   * @param value the value to set.
   */
  public void setDescription(java.lang.CharSequence value) {
    this.description = value;
  }

  /**
   * Gets the value of the 'dataType' field.
   * @return The type of data that is sent
   */
  public eu.driver.model.core.DataType getDataType() {
    return dataType;
  }

  /**
   * Sets the value of the 'dataType' field.
   * The type of data that is sent
   * @param value the value to set.
   */
  public void setDataType(eu.driver.model.core.DataType value) {
    this.dataType = value;
  }

  /**
   * Creates a new LargeDataUpdate RecordBuilder.
   * @return A new LargeDataUpdate RecordBuilder
   */
  public static eu.driver.model.core.LargeDataUpdate.Builder newBuilder() {
    return new eu.driver.model.core.LargeDataUpdate.Builder();
  }

  /**
   * Creates a new LargeDataUpdate RecordBuilder by copying an existing Builder.
   * @param other The existing builder to copy.
   * @return A new LargeDataUpdate RecordBuilder
   */
  public static eu.driver.model.core.LargeDataUpdate.Builder newBuilder(eu.driver.model.core.LargeDataUpdate.Builder other) {
    return new eu.driver.model.core.LargeDataUpdate.Builder(other);
  }

  /**
   * Creates a new LargeDataUpdate RecordBuilder by copying an existing LargeDataUpdate instance.
   * @param other The existing instance to copy.
   * @return A new LargeDataUpdate RecordBuilder
   */
  public static eu.driver.model.core.LargeDataUpdate.Builder newBuilder(eu.driver.model.core.LargeDataUpdate other) {
    return new eu.driver.model.core.LargeDataUpdate.Builder(other);
  }

  /**
   * RecordBuilder for LargeDataUpdate instances.
   */
  public static class Builder extends org.apache.avro.specific.SpecificRecordBuilderBase<LargeDataUpdate>
    implements org.apache.avro.data.RecordBuilder<LargeDataUpdate> {

    /** Link of where to download the data file from */
    private java.lang.CharSequence url;
    /** Optional title of the data file, e.g. to serve it via WMS or otherwise */
    private java.lang.CharSequence title;
    /** Optional description of the file */
    private java.lang.CharSequence description;
    /** The type of data that is sent */
    private eu.driver.model.core.DataType dataType;

    /** Creates a new Builder */
    private Builder() {
      super(SCHEMA$);
    }

    /**
     * Creates a Builder by copying an existing Builder.
     * @param other The existing Builder to copy.
     */
    private Builder(eu.driver.model.core.LargeDataUpdate.Builder other) {
      super(other);
      if (isValidValue(fields()[0], other.url)) {
        this.url = data().deepCopy(fields()[0].schema(), other.url);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.title)) {
        this.title = data().deepCopy(fields()[1].schema(), other.title);
        fieldSetFlags()[1] = true;
      }
      if (isValidValue(fields()[2], other.description)) {
        this.description = data().deepCopy(fields()[2].schema(), other.description);
        fieldSetFlags()[2] = true;
      }
      if (isValidValue(fields()[3], other.dataType)) {
        this.dataType = data().deepCopy(fields()[3].schema(), other.dataType);
        fieldSetFlags()[3] = true;
      }
    }

    /**
     * Creates a Builder by copying an existing LargeDataUpdate instance
     * @param other The existing instance to copy.
     */
    private Builder(eu.driver.model.core.LargeDataUpdate other) {
            super(SCHEMA$);
      if (isValidValue(fields()[0], other.url)) {
        this.url = data().deepCopy(fields()[0].schema(), other.url);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.title)) {
        this.title = data().deepCopy(fields()[1].schema(), other.title);
        fieldSetFlags()[1] = true;
      }
      if (isValidValue(fields()[2], other.description)) {
        this.description = data().deepCopy(fields()[2].schema(), other.description);
        fieldSetFlags()[2] = true;
      }
      if (isValidValue(fields()[3], other.dataType)) {
        this.dataType = data().deepCopy(fields()[3].schema(), other.dataType);
        fieldSetFlags()[3] = true;
      }
    }

    /**
      * Gets the value of the 'url' field.
      * Link of where to download the data file from
      * @return The value.
      */
    public java.lang.CharSequence getUrl() {
      return url;
    }

    /**
      * Sets the value of the 'url' field.
      * Link of where to download the data file from
      * @param value The value of 'url'.
      * @return This builder.
      */
    public eu.driver.model.core.LargeDataUpdate.Builder setUrl(java.lang.CharSequence value) {
      validate(fields()[0], value);
      this.url = value;
      fieldSetFlags()[0] = true;
      return this;
    }

    /**
      * Checks whether the 'url' field has been set.
      * Link of where to download the data file from
      * @return True if the 'url' field has been set, false otherwise.
      */
    public boolean hasUrl() {
      return fieldSetFlags()[0];
    }


    /**
      * Clears the value of the 'url' field.
      * Link of where to download the data file from
      * @return This builder.
      */
    public eu.driver.model.core.LargeDataUpdate.Builder clearUrl() {
      url = null;
      fieldSetFlags()[0] = false;
      return this;
    }

    /**
      * Gets the value of the 'title' field.
      * Optional title of the data file, e.g. to serve it via WMS or otherwise
      * @return The value.
      */
    public java.lang.CharSequence getTitle() {
      return title;
    }

    /**
      * Sets the value of the 'title' field.
      * Optional title of the data file, e.g. to serve it via WMS or otherwise
      * @param value The value of 'title'.
      * @return This builder.
      */
    public eu.driver.model.core.LargeDataUpdate.Builder setTitle(java.lang.CharSequence value) {
      validate(fields()[1], value);
      this.title = value;
      fieldSetFlags()[1] = true;
      return this;
    }

    /**
      * Checks whether the 'title' field has been set.
      * Optional title of the data file, e.g. to serve it via WMS or otherwise
      * @return True if the 'title' field has been set, false otherwise.
      */
    public boolean hasTitle() {
      return fieldSetFlags()[1];
    }


    /**
      * Clears the value of the 'title' field.
      * Optional title of the data file, e.g. to serve it via WMS or otherwise
      * @return This builder.
      */
    public eu.driver.model.core.LargeDataUpdate.Builder clearTitle() {
      title = null;
      fieldSetFlags()[1] = false;
      return this;
    }

    /**
      * Gets the value of the 'description' field.
      * Optional description of the file
      * @return The value.
      */
    public java.lang.CharSequence getDescription() {
      return description;
    }

    /**
      * Sets the value of the 'description' field.
      * Optional description of the file
      * @param value The value of 'description'.
      * @return This builder.
      */
    public eu.driver.model.core.LargeDataUpdate.Builder setDescription(java.lang.CharSequence value) {
      validate(fields()[2], value);
      this.description = value;
      fieldSetFlags()[2] = true;
      return this;
    }

    /**
      * Checks whether the 'description' field has been set.
      * Optional description of the file
      * @return True if the 'description' field has been set, false otherwise.
      */
    public boolean hasDescription() {
      return fieldSetFlags()[2];
    }


    /**
      * Clears the value of the 'description' field.
      * Optional description of the file
      * @return This builder.
      */
    public eu.driver.model.core.LargeDataUpdate.Builder clearDescription() {
      description = null;
      fieldSetFlags()[2] = false;
      return this;
    }

    /**
      * Gets the value of the 'dataType' field.
      * The type of data that is sent
      * @return The value.
      */
    public eu.driver.model.core.DataType getDataType() {
      return dataType;
    }

    /**
      * Sets the value of the 'dataType' field.
      * The type of data that is sent
      * @param value The value of 'dataType'.
      * @return This builder.
      */
    public eu.driver.model.core.LargeDataUpdate.Builder setDataType(eu.driver.model.core.DataType value) {
      validate(fields()[3], value);
      this.dataType = value;
      fieldSetFlags()[3] = true;
      return this;
    }

    /**
      * Checks whether the 'dataType' field has been set.
      * The type of data that is sent
      * @return True if the 'dataType' field has been set, false otherwise.
      */
    public boolean hasDataType() {
      return fieldSetFlags()[3];
    }


    /**
      * Clears the value of the 'dataType' field.
      * The type of data that is sent
      * @return This builder.
      */
    public eu.driver.model.core.LargeDataUpdate.Builder clearDataType() {
      dataType = null;
      fieldSetFlags()[3] = false;
      return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public LargeDataUpdate build() {
      try {
        LargeDataUpdate record = new LargeDataUpdate();
        record.url = fieldSetFlags()[0] ? this.url : (java.lang.CharSequence) defaultValue(fields()[0]);
        record.title = fieldSetFlags()[1] ? this.title : (java.lang.CharSequence) defaultValue(fields()[1]);
        record.description = fieldSetFlags()[2] ? this.description : (java.lang.CharSequence) defaultValue(fields()[2]);
        record.dataType = fieldSetFlags()[3] ? this.dataType : (eu.driver.model.core.DataType) defaultValue(fields()[3]);
        return record;
      } catch (java.lang.Exception e) {
        throw new org.apache.avro.AvroRuntimeException(e);
      }
    }
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumWriter<LargeDataUpdate>
    WRITER$ = (org.apache.avro.io.DatumWriter<LargeDataUpdate>)MODEL$.createDatumWriter(SCHEMA$);

  @Override public void writeExternal(java.io.ObjectOutput out)
    throws java.io.IOException {
    WRITER$.write(this, SpecificData.getEncoder(out));
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumReader<LargeDataUpdate>
    READER$ = (org.apache.avro.io.DatumReader<LargeDataUpdate>)MODEL$.createDatumReader(SCHEMA$);

  @Override public void readExternal(java.io.ObjectInput in)
    throws java.io.IOException {
    READER$.read(this, SpecificData.getDecoder(in));
  }

}