package com.capitole.challenge.cli.infrastructure.broker;

import com.capitole.challenge.cli.application.port.output.MessagePublisherPort;
import com.capitole.challenge.cli.domain.model.Person;
import com.capitole.challenge.cli.infrastructure.broker.mapper.ProtobufPersonMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

/**
 * @author alex.vall
 *
 * This class is used to publish domain Person to Kafka via StreamBridge.
 */
@Component
@RequiredArgsConstructor
public class KafkaPersonPublisher implements MessagePublisherPort<Person> {

    private final StreamBridge streamBridge;

    private static final String BINDING_NAME = "person-out-0";

    @Override
    public void publish(Person person) {
        byte[] payload = ProtobufPersonMapper.toProto(person).toByteArray();
        Message<byte[]> message = MessageBuilder.withPayload(payload)
                .setHeader("contentType", "application/x-protobuf")
                .build();
        streamBridge.send(BINDING_NAME, message);
    }
}
