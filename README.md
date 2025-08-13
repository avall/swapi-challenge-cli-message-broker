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
`./gradlew clean build`
`docker compose up`
`java -jar ./app/main/build/libs/cli-app-0.0.1-SNAPSHOT.jar --file=./addons/test/person.json`

- After running the above command, the message will be sent to the Kafka topic `topic-capitole-protobuf-message`.
- The message can be viewed in the Kafka UI (http://localhost:9999).


## Design notes

## Definition of Done
- The message can be read by any consumer subscribing to topic-capitole-protobuf-message and decoding with the provided protobuf schema.

