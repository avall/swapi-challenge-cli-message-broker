# swapi-challenge-cli-message-broker
This is a consulting challenge to send protobuf message to broker (kafka).

## Use Case
Implement a CLI tool that sends protobuf messages to a Kafka message broker.

## Tech Stack
- Java 17
- Spring Boot 3
- Spring Cloud Stream (Kafka binder)
- Protobuf

## Architecture
- Hexagonal architecture: domain and application are technology-agnostic; infrastructure handles command parsing and broker specifics.
- The application use case is annotated with a custom @Interactor but is wired via infra @Configuration to keep the application module free of Spring dependencies.

## How to Run
1) Prerequisites
- A running Kafka broker. By default the app uses KAFKA_BOOTSTRAP_SERVERS=localhost:9092. You can override via env var.
- Java 17 and Maven.

2) Build
- ./mvnw clean package

3) Start the application
- Option A: ./mvnw -pl app/main -am spring-boot:run -Dspring-boot.run.arguments="--file=app/main/src/main/resources/sample-person.json"
- Option B: java -jar app/main/target/main.jar --file=/absolute/path/to/sample-person.json

4) Produce a message
- Provide a JSON file path via --file or -f. Example JSON (already included at app/main/src/main/resources/sample-person.json):
{
  "name": "Luke Skywalker",
  "id": 1,
  "email": "luke@rebellion.org"
}

5) Consume the message (verification)
- Using Kafka console consumer:
  kafka-console-consumer --bootstrap-server localhost:9092 --topic topic-capitole-protobuf-message --from-beginning --property print.key=true --property value.deserializer=org.apache.kafka.common.serialization.ByteArrayDeserializer
  Note: The payload is protobuf bytes (Person message). Use a protobuf-aware consumer to decode or a small Java consumer using the generated PersonProtos.Person.parseFrom(bytes).

## Configuration
- app/main/src/main/resources/application.yml contains:
  - spring.cloud.stream.bindings.person-out-0.destination=topic-capitole-protobuf-message
  - spring.cloud.stream.kafka.binder.brokers=${KAFKA_BOOTSTRAP_SERVERS:localhost:9092}

## Design notes
- Domain has Person entity and PersonPublisherPort.
- Application has SendPersonUseCase which depends only on the port.
- Infrastructure-command parses CLI args and reads the JSON file.
- Infrastructure-broker maps domain Person to protobuf and publishes bytes to Kafka via StreamBridge.

## Definition of Done
- The message can be read by any consumer subscribing to topic-capitole-protobuf-message and decoding with the provided protobuf schema.
- Instructions above explain how to run and consume.
