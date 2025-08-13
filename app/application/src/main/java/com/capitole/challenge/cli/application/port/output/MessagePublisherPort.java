package com.capitole.challenge.cli.application.port.output;

public interface MessagePublisherPort<T> {
    void publish(T message);
}
