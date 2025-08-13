package com.capitole.challenge.cli.infrastructure.command;

import com.capitole.challenge.cli.application.port.input.SendMessageUseCase;
import com.capitole.challenge.cli.domain.model.Person;
import com.capitole.challenge.cli.infrastructure.command.dto.PersonDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author alex.vall
 *
 * This class is used to run the application from the command line.
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class CommandLineApp {

    private final SendMessageUseCase sendMessageUseCase;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public void run(String[] args) throws Exception {
      String filePath = getFilePath(args);
      if (filePath == null) {
        log.info("Usage: java -jar app.jar --file=/path/to/person.json");
        return;
      }
      if (!new File(filePath).exists()) {
        log.info("File not found: {}", filePath);
        return;
      }

      PersonDto dto = objectMapper.readValue(new File(filePath), PersonDto.class);
      Person person = mapper(dto);
      sendMessageUseCase.execute(new SendMessageUseCase.Input(person));
      log.info("Sent person to Kafka topic 'topic-capitole-protobuf-message'");
    }

  private Person mapper(PersonDto dto) {
    return Person.builder()
            .name(dto.getName())
            .id(dto.getId())
            .email(dto.getEmail())
            .build();
  }

  private String getFilePath(String[] args) {
    String filePath = null;
    for (String arg : args) {
        if (arg.startsWith("--file=")) {
            filePath = arg.substring("--file=".length());
        } else if (arg.startsWith("-f=")) {
            filePath = arg.substring("-f=".length());
        }
    }
    return filePath;
  }
}
