/**
 * Autogenerated by Avro
 *
 * DO NOT EDIT DIRECTLY
 */
package eu.driver.model.core;
@SuppressWarnings("all")
@org.apache.avro.specific.AvroGenerated
public enum Command {
  Init, Start, Pause, Update, Stop, Reset  ;
  public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"enum\",\"name\":\"Command\",\"namespace\":\"eu.driver.model.core\",\"symbols\":[\"Init\",\"Start\",\"Pause\",\"Update\",\"Stop\",\"Reset\"]}");
  public static org.apache.avro.Schema getClassSchema() { return SCHEMA$; }
}
