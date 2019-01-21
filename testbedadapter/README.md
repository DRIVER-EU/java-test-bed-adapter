# JAVA Testbed Adapter
The Java Testbed Adapter is used to be integrated directly into the JAVA application. It is build as library for easy integration.
It offers one central point for communication: Adapter.getInstance();

In this following methods are available:

## public getInstance for instantiation the Adapter Singelton
In this, the check to the configured KAFKA is done. The Adapter is able to detect the mode in which the Adapter should run:
### Available modes are:
DEV_MODE, SEC_DEV_MODE, TRIAL_MODE
NOTE: for SEC_DEV_MODE and for TRIAL mode a certificate located in the /config/cert directory has to be available

## public getNewInstance for getting a new Adapter Singelton instance
If you need to reset all consumer/producers you can request a complete clean new instance.

## send a message
send an already into an AVRO Object structure converted message
### follwing methods are available
public void sendMessage(ARVO Object) throws CommunicationException
public void sendMessage(ARVO Object,; topicName) throws CommunicationException


## register callback for specific type (topic)
to get data (message) into you application you need to register a callback (which has to implement the IAdapterCallback Interface
public void addCallback(methodDelegate(senderID, topicName, specificType(exc.JS)), topicName);


## getTrialTime
Get the simulation time as Date
public AVROTimeInfo getTimeInfo();

## getTimeElapsed
Get elapsed time in msec
public long getTimeElapsed();


## add a Log entry in the Trial Event Log
public void addLogEntry(AVRO LOG Object)

## add a Log callback to get the Trial Event Logs send by all connected solutions
public void addLogCallback(methodDelegate(avroLogRecord))


# Requirements for Development

* Java JDK 1.8+
* Development Environment e.g.: Eclipse
* maven

# Run the Adapter

* the build of this java Adapter results in an java library that can be included in your JAVA application.

# Configuration

## Default values

###Default Consumer Properties
* bootstrap.servers=broker.url
* group.id=<client.id> from the client-config.properties files
* enable.auto.commit=true
* auto.offset.reset=latest
* key.deserializer=io.confluent.kafka.serializers.KafkaAvroDeserializer
* value.deserializer=io.confluent.kafka.serializers.KafkaAvroDeserializer
* schema.registry.url=schema.url

###Default Consumer Properties
* bootstrap.servers=broker.url
* schema.registry.url=schema.url
* compression.type=none
* acks=all
* retries=retry.count
* request.timeout.ms= retry.time
* key.serializer=io.confluent.kafka.serializers.KafkaAvroSerializer
* value.serializer=io.confluent.kafka.serializers.KafkaAvroSerializer

## Authentication Configuration
If the Testbed is running in seucred mode, the adapter needs to identify with a certificate. This certificate has to be stored and the needed information have to be provided in the ssl.properties file. The adapter will automatically detect if ssl is needed.
* security.protocol=SSL
* ssl.truststore.location=config/cert/truststore.jks
* ssl.truststore.type=JKS
* ssl.truststore.password=changeit
* ssl.keystore.location=config/cert/test_new.p12
* ssl.keystore.type=PKCS12
* ssl.keystore.password=test
* ssl.key.password=test

## Specific Configuration

If you wish to override default configuration values you can do so in the configuration files in the 'config' directory.
###Specific Client Properties
* client.id=solution client
* heartbeat.interval=5000
* certificate.path=path
* broker.url=
* schema.url=
* send.sync=true/false
* retry.count=3
* retry.time=1000?

## Example implementation
2 Example implementations (CISAdapterCAPExample & ExampleAdapter) can be found under: src/main/java/eu/driver/examples/adapter 
