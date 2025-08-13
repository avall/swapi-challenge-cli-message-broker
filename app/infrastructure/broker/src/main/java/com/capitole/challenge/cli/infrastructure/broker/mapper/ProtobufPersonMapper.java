package com.capitole.challenge.cli.infrastructure.broker.mapper;

import com.capitole.challenge.cli.domain.model.Person;
import com.capitole.challenge.cli.infrastructure.broker.proto.PersonProtos;

public class ProtobufPersonMapper {

    public static PersonProtos.Person toProto(Person person) {
        PersonProtos.Person.Builder builder = PersonProtos.Person.newBuilder()
                .setName(person.getName())
                .setId(person.getId());
        if (person.getEmail() != null && !person.getEmail().isBlank()) {
            builder.setEmail(person.getEmail());
        }
        return builder.build();
    }
}
