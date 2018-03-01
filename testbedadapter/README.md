# JAVA Testbed Adaptor
The Java Testbed adaptor is used to be integrated directly into the JAVA application. It is build as library for easy integraton.
It offers one central point for communication: CISAdapter
In this following methods are available:

## public constructor for instantiation the CISAdapter
In this, the check to the configured KAFKA is done. The Adaptor is able to detect the mode in which the Adapter should run:
### Available modes are:
DEV_MODE, SEC_DEV_MODE, TRIAL_MODE
NOTE: for SEC_DEV_MODE and for TRIAL mode a certificate located in the /config/cert directory has to be available

## register callback for specific type (topic)
to get data (message) into you application you need to register a callback (which has to implement the IAdapterCallback Interface

## send a message
send an already into an AVRO Object structure converted message

## get the trail time
returns the victive trial time (does not need to be the real time)

## get the trail time speed
returns the speed factore of the trail time (how fast the trial continues)


# Requirements for Development

* Java JDK 1.8+
* Development Environment e.g.: Eclipse
* maven

# Run the Adaptor

* the build of this java adaptor results in an java library that can be included in your JAVA application.

# Configuration

## Default values

are defined 

## Specific Configuration

If you wish to override default configuration values you can do so in the configuration files in the 'config' directory.
