package com.capitole.challenge.cli.consumer;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author alex.vall
 * Main spring-boot application taking care to load
 * into the Spring Context any class that implements the Interactor.
 */

@ComponentScan(
    basePackages = {"com.capitole.challenge.*"}
)
@RequiredArgsConstructor
@SpringBootApplication
public class Application {

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }
}
