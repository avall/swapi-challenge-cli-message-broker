package com.capitole.challenge.cli.main;

import com.capitole.challenge.cli.domain.decorator.Interactor;
import com.capitole.challenge.cli.infrastructure.command.CommandLineApp;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

/**
 * @author alex.vall
 * Main spring-boot application taking care to load
 * into the Spring Context any class that implements the Interactor.
 */
@ComponentScan(
    basePackages = {"com.capitole.challenge.cli.*"},
    includeFilters = @ComponentScan.Filter(
    type = FilterType.ANNOTATION,
    classes = {Interactor.class})
)
@RequiredArgsConstructor
@SpringBootApplication
public class Application implements CommandLineRunner {

  private final CommandLineApp commandApp;

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }

  @Override
  public void run(String... args) throws Exception {
    commandApp.run(args);
  }

}
