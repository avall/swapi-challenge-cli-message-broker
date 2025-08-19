# swapi-challenge-cli-message-broker
CLI tool that sends a protobuf Person message to a Kafka topic using Spring Cloud Stream.

## Use Case
[Challenge description](addons/docs/Software%20Engineer%20Quiz.pdf) \
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
- Java 17 (to run locally)
- Docker (for Kafka via docker-compose)

## Quickstart
1) Start Kafka locally
- docker compose up -d
This repo includes a docker-compose.yml that exposes Kafka on localhost:9092 (and optionally a UI on 9999 if present).

2) Build the project
- ./gradlew clean build

3) Run the CLI app (to produce messages) from terminal (located in the root folder of the project)
- java -jar app/main/build/libs/cli-app-0.0.1-SNAPSHOT.jar --file=./addons/test/person.json
Or with shorthand flag:
- java -jar app/main/build/libs/cli-app-0.0.1-SNAPSHOT.jar -f=./addons/test/person.json

Sample JSON files:
- addons/test/person.json

Topic: topic-capitole-protobuf-message

4) Check the kafka UI (localhost:9999)

## Configuration
The relevant properties in the producer are in app/main/src/main/resources/application.yml
- spring.cloud.stream.bindings.person-out-0.destination=${APP_TOPIC_PERSON:topic-capitole-protobuf-message}
- spring.cloud.stream.kafka.binder.brokers=${APP_KAFKA_BROKERS:${KAFKA_BOOTSTRAP_SERVERS:localhost:9092}}
- Producer uses application/x-protobuf and KafkaProtobufSerializer

The relevant properties in the consumer are in consumer/src/main/resources/application.yml
- spring.cloud.stream.kafka.binder.consumer-properties.specific.protobuf.value.type: com.capitole.challenge.cli.infrastructure.broker.proto.ProtoPerson$Person


## Troubleshooting
- If the CLI prints Usage, ensure you provide --file=/path/to/person.json or -f=...
- If Kafka connection fails, verify KAFKA_BOOTSTRAP_SERVERS or APP_KAFKA_BROKERS env variable and that docker compose is up.
- If topic does not exist, auto-creation is enabled by default via application.yml. 

## Definition of Done
- The message can be read by any consumer subscribing to topic-capitole-protobuf-message and decoding with the provided protobuf schema.


## Running the Producer (CLI) and the Consumer

Producer (already packaged as cli-app):
- Build: ./gradlew clean build
- Run: java -jar app/main/build/libs/cli-app-0.0.1-SNAPSHOT.jar --file=./addons/test/person.json
  - Shorthand: java -jar app/main/build/libs/cli-app-0.0.1-SNAPSHOT.jar -f=./addons/test/person.json
    Normal:    java -jar app/main/build/libs/cli-app-0.0.1-SNAPSHOT.jar --file=./addons/test/person.json

Consumer (packaged as cli-consumer via a dedicated BootJar task):
- Build only the consumer jar: ./gradlew :app:main:bootJarConsumer
  - Or build everything: ./gradlew clean build (also produces the consumer jar)
- Run: java -jar consumer/build/libs/cli-consumer-0.0.1-SNAPSHOT.jar

Notes:
- Ensure Kafka and Schema Registry from docker-compose are up before starting the consumer.
- The consumer binds to spring.cloud.function.definition=person-consumer and listens on the topic configured by APP_TOPIC_PERSON (default topic-capitole-protobuf-message).
- You should see logs like "PersonConsumer#accept, id=..." when messages arrive.
