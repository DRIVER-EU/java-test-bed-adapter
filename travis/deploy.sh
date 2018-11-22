cd java-adaptor-avro-schemas
mvn deploy -DskipTests
cd ../testbedadapter
mvn deploy -DskipTests
cd ../xml-json-to-avro-mapper
mvn deploy -DskipTests
