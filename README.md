# Graylog Message Handler
This project is a CLI tool to read and parse messages from an input file and send them to a running Graylog instance over HTTP using the GELF message specification.

# Structure
The project consists of three main modules:

1. The Ingestor module which reads in a text file and parses into JSON based message objects.
2. The Formatter module which takes the JSON messages and reformats them to meet GELF message specifications by adding required fields and formatting any additional fields.
3. The Sender module that establishes an HTTP connection with the running Graylog instance and sends the messages.

# Usage
The tool can be run from the command line using the provided graylog-message-handler-jar-with-dependencies.jar file.

The required input arguments are the host address of a running Graylog instance, the listening port on the Graylog host, and the path of the file containing the messages to parse and send.

Example:
```

java -jar graylog-message-handler-jar-with-dependencies.jar localhost 12201 /home/usr/logs/sample-messages.txt

```
