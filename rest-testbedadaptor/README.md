# REST Endpoint Adaptor
This standalone DRIVER+ Adaptor provides an REST Endpoint for sending standard message (e.g. CAP, MLP) via the JAVA Testbed Adator.
The standard message can be validated before sending it to the CIS. This mechanism can be configured in the Adaptor properties. If the parameter is not specified no validation is performed.
It offers two possibilities for retrieving messages back:
REST Endpoint: can be configured in the adaptor properties or by passing it via a REST Endpoint
WebSocket: established by the client, adaptor is sending the message via this socket communication (heartbeat - invoked by the client (request-response) needed to check if the socket is up).

# Requirements for Development

* Java JDK 1.8+
* Development environment e.g. Eclipse
* maven

# Run the Adaptor

* Run docker container

# Swagger Interface for testing
The REST Adapter offers for testing purposes a swagger ui where all exposed methods can be tested.
The SWAGER UI can be reached by:
*http://localhsot:8090/swagger-ui.html

# Configuration

## Default values
### application.properties
*server.port = 8090


## Specific Configuration

If you wish to override default configuration values you can do so in the configuration files in the 'config' directory.
