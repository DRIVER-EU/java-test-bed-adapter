/**
 * Autogenerated by Avro
 *
 * DO NOT EDIT DIRECTLY
 */
package eu.driver.model.core;
@SuppressWarnings("all")
@org.apache.avro.specific.AvroGenerated
public enum State {
  Idle, Initialized, Started, Paused, Stopped  ;
  public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"enum\",\"name\":\"State\",\"namespace\":\"eu.driver.model.core\",\"symbols\":[\"Idle\",\"Initialized\",\"Started\",\"Paused\",\"Stopped\"]}");
  public static org.apache.avro.Schema getClassSchema() { return SCHEMA$; }
}