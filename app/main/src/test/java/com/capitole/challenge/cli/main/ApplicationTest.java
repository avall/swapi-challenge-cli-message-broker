package com.capitole.challenge.cli.main;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

@SpringBootTest(
    classes = {Application.class},
    webEnvironment = SpringBootTest.WebEnvironment.MOCK)
class ApplicationTest {
  @Autowired ApplicationContext applicationContext;

  @Test
  void contextLoads() {
    Assertions.assertNotNull(applicationContext);
  }
}
