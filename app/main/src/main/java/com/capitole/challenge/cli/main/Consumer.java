package com.capitole.challenge.cli.main;

import com.capitole.challenge.cli.domain.decorator.Interactor;
import lombok.RequiredArgsConstructor;
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
    basePackages = {"com.capitole.challenge.cli.infrastructure.broker.subscriber"},
    includeFilters = @ComponentScan.Filter(
    type = FilterType.ANNOTATION,
    classes = {Interactor.class})
)
@RequiredArgsConstructor
@SpringBootApplication
public class Consumer {

  public static void main(String[] args) {
    SpringApplication.run(Consumer.class, args);
  }
}
