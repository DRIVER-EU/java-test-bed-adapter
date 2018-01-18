# java-test-bed-adapter
The JAVA test-bed adapter

# Requirements for Development

* Java JDK 1.8+
* [Apache Maven](https://maven.apache.org/install.html) 
* A running testbed infrastruture. See [Local](https://github.com/DRIVER-EU/test-bed/tree/master/docker/local) for how to run a local testbed infrastructure.

# Running from Eclipse / IntelliJ

* Clone this project and the [XML-Avro project](https://github.com/DRIVER-EU/xml-avro). 
* Navigate to the xml-avro project directory and run: `mvn clean install -DskipTests`
* Import the Java Testbed Adapter into your Editor as a Maven project
* Run the `CISAdapterCAPExample.java` example

## Known Error

If you get the following error in Eclipse:

```
No marketplace entries found to handle avro-maven-plugin:1.8.2:Schema in Eclipse.  Please see Help for more information.
```

You should mark this error to be ignored.

# Configuration

## Default values

TODO: fill this

## Specific Configuration

If you wish to override default configuration values you can do so in the configuration files in the 'config' directory.
