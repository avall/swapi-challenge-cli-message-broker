package com.capitole.challenge.cli.application.port.input.interactor;

import com.capitole.challenge.cli.application.port.input.SendMessageUseCase;
import com.capitole.challenge.cli.application.port.output.MessagePublisherPort;
import com.capitole.challenge.cli.domain.decorator.Interactor;
import com.capitole.challenge.cli.domain.model.Person;
import lombok.RequiredArgsConstructor;

/**
 * @author alex.vall
 *
 * This class is used to send domain Person to Kafka via MessagePublisherPort.
 */
@RequiredArgsConstructor
@Interactor
public class SendMessageInteractor implements SendMessageUseCase {

    private final MessagePublisherPort<Person> publisherPort;

    @Override
    public Output execute(Input input) {
        publisherPort.publish(input.getPerson());
        return new Output();
    }
}
