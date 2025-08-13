package com.capitole.challenge.cli.infrastructure.command;

import com.capitole.challenge.cli.application.port.input.SendMessageUseCase;
import com.capitole.challenge.cli.domain.model.Person;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * @author alex.vall
 *
 * This class is used to run the application from the command line.
 */
@Component
@RequiredArgsConstructor
public class CommandLineApp implements CommandLineRunner {

    private final SendMessageUseCase sendMessageUseCase;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void run(String... args) throws Exception {
        String filePath = null;
        for (String arg : args) {
            if (arg.startsWith("--file=")) {
                filePath = arg.substring("--file=".length());
            } else if (arg.startsWith("-f=")) {
                filePath = arg.substring("-f=".length());
            }
        }
        if (filePath == null) {
            System.out.println("Usage: java -jar app.jar --file=/path/to/person.json");
            return;
        }

        PersonJson pj = objectMapper.readValue(new File(filePath), PersonJson.class);
        Person person = Person.builder()
                .name(pj.getName())
                .id(pj.getId())
                .email(pj.getEmail())
                .build();
        sendMessageUseCase.execute(new SendMessageUseCase.Input(person));
        System.out.println("Sent person to Kafka topic 'topic-capitole-protobuf-message'");
    }

    @Data
    private static class PersonJson {
        private String name;
        private int id;
        private String email;
    }
}
