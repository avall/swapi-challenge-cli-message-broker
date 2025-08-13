# swapi-challenge-cli-message-broker
CLI tool that sends a protobuf Person message to a Kafka topic using Spring Cloud Stream.

## Use Case
Read a JSON file from disk, transform it into a protobuf Person, and publish the bytes to a Kafka topic so that other systems can consume it.

## Tech Stack
- Java 17
- Spring Boot 3
- Spring Cloud Stream (Kafka binder)
- Protobuf

## Architecture
- Hexagonal architecture: domain and application are technology-agnostic; infrastructure (command + broker) handles IO and technologies.
- Application use case is annotated with a custom `@Interactor`; it’s discovered by Spring via a ComponentScan filter to avoid Spring dependencies in the application module.

## Prerequisites
- Java 17
- Docker (for Kafka via docker-compose)

## Quickstart
1) Start Kafka locally
- docker compose up -d
This repo includes a docker-compose.yml that exposes Kafka on localhost:9092 (and optionally a UI on 9999 if present).

2) Build the project
- ./gradlew clean build

3) Run the CLI app
- java -jar app/main/build/libs/cli-app-0.0.1-SNAPSHOT.jar --file=./addons/test/person.json
Or with shorthand flag:
- java -jar app/main/build/libs/cli-app-0.0.1-SNAPSHOT.jar -f=./addons/test/person.json

Sample JSON files:
- addons/test/person.json
- app/main/src/main/resources/sample-person.json

Topic: topic-capitole-protobuf-message

## Configuration
The relevant properties are in app/main/src/main/resources/application.yml
- spring.cloud.stream.bindings.person-out-0.destination=${APP_TOPIC_PERSON:topic-capitole-protobuf-message}
- spring.cloud.stream.kafka.binder.brokers=${APP_KAFKA_BROKERS:${KAFKA_BOOTSTRAP_SERVERS:localhost:9092}}
- Producer uses application/x-protobuf and ByteArraySerializer
You can override env vars like APP_KAFKA_BROKERS or APP_TOPIC_PERSON when running.

## Consuming the message
The payload is protobuf bytes (Person). Use a protobuf-aware consumer to decode.
- Console consumer example (raw bytes):
  kafka-console-consumer --bootstrap-server localhost:9092 \
    --topic topic-capitole-protobuf-message --from-beginning \
    --property print.key=true --property value.deserializer=org.apache.kafka.common.serialization.ByteArrayDeserializer
- Java consumer example (decoding):
  PersonProtos.Person parsed = PersonProtos.Person.parseFrom(payloadBytes);

## Troubleshooting
- If the CLI prints Usage, ensure you provide --file=/path/to/person.json or -f=...
- If Kafka connection fails, verify KAFKA_BOOTSTRAP_SERVERS or APP_KAFKA_BROKERS env variable and that docker compose is up.
- If topic does not exist, auto-creation is enabled by default via application.yml. You can pre-create it if your broker requires.

## Definition of Done
- The message can be read by any consumer subscribing to topic-capitole-protobuf-message and decoding with the provided protobuf schema.

