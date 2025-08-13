package com.capitole.challenge.cli.application.port.output;

/**
 * @author alex.vall
 * Interface used to publish messages
 *
 * @param <T>
 */
public interface MessagePublisherPort<T> {
    void publish(T message);
}
