# java-test-bed-adapter
The JAVA test-bed adapter

# Running from Eclipse

Clone this project and the [XML-Avro project](https://github.com/DRIVER-EU/xml-avro). 

Import both projects into your Java editor as a Maven project. The TestbedAdapter currently has a dependency on the XML-Avro project.

## Known Error

If you get the error:

```
No marketplace entries found to handle avro-maven-plugin:1.8.2:Schema in Eclipse.  Please see Help for more information.
```

You should mark in Eclipse to ignore this error.

# Running the example

A simple example of converting an XML CAP message in a file to our CAP Avro format, sending it to the CIS, and consuming it from the CIS can be found in the `examples.adapter` package: `CISAdapterCAPExample.java`
