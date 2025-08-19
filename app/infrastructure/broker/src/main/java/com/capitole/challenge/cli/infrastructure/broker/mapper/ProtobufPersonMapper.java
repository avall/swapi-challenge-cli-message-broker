package com.capitole.challenge.cli.infrastructure.broker.mapper;

import com.capitole.challenge.cli.domain.model.Person;
import com.capitole.challenge.cli.infrastructure.broker.proto.ProtoPerson;

/**
 * @author alex.vall
 *
 * This class is used to map domain Person to protobuf and vice versa.
 */
public class ProtobufPersonMapper {
    private ProtobufPersonMapper() {
    }


    public static ProtoPerson.Person toProto(Person person) {
        return ProtoPerson.Person.newBuilder()
                .setName(person.getName())
                .setEmail(person.getEmail())
                .setId(person.getId())
            .build();
    }
}
