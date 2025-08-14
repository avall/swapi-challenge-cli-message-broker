package com.capitole.challenge.cli.domain.model;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class Person {
    String name;
    int id;
    String email; // optional
}
