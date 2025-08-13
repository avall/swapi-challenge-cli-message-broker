package com.capitole.challenge.cli.application.port.input;

import com.capitole.challenge.cli.application.port.UseCase;
import com.capitole.challenge.cli.domain.model.Person;


public interface SendMessageUseCase extends UseCase<SendMessageUseCase.Input, SendMessageUseCase.Output> {
    @lombok.Value
    @lombok.AllArgsConstructor
    class Input implements UseCase.InputValues {
        Person person;
    }

    class Output implements UseCase.OutputValues {}
}
