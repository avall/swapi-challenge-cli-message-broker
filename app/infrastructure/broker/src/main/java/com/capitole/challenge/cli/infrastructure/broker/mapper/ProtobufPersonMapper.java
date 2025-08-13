package com.capitole.challenge.cli.infrastructure.broker.mapper;

import com.capitole.challenge.cli.domain.model.Person;
import com.capitole.challenge.cli.infrastructure.broker.proto.PersonProtos;

/**
 * @author alex.vall
 *
 * This class is used to map domain Person to protobuf and vice versa.
 */
public class ProtobufPersonMapper {

    public static PersonProtos.Person toProto(Person person) {
        PersonProtos.Person.Builder builder = PersonProtos.Person.newBuilder()
                .setName(person.getName())
                .setEmail(person.getEmail())
                .setId(person.getId());
        return builder.build();
    }
}
