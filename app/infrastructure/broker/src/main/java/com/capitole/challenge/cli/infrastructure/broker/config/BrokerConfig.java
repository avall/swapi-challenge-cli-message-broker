package com.capitole.challenge.cli.infrastructure.broker.config;

import com.capitole.challenge.cli.application.port.input.SendMessageUseCase;
import com.capitole.challenge.cli.application.port.input.interactor.SendMessageInteractor;
import com.capitole.challenge.cli.application.port.output.MessagePublisherPort;
import com.capitole.challenge.cli.domain.model.Person;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BrokerConfig {

//    @Bean
//    public SendMessageUseCase sendPersonUseCase(MessagePublisherPort<Person> publisherPort) {
//        return new SendMessageInteractor(publisherPort);
//    }
}
