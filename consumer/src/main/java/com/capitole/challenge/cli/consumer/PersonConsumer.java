package com.capitole.challenge.cli.consumer;

import com.capitole.challenge.cli.infrastructure.broker.proto.ProtoPerson;
import java.util.function.Consumer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

/**
 * @author alex.vall
 *
 * This class is used to consume domain Person from Kafka via StreamBridge.
 */
@Slf4j
@Component("person-consumer")
@RequiredArgsConstructor
public class PersonConsumer implements Consumer<Message<ProtoPerson.Person>> {

  @Override
  public void accept(final Message<ProtoPerson.Person> message) {
    log.info("\nPersonConsumer#accept, \nMessage={} ", message);
    var pp = message.getPayload();
    var email = pp.getEmail();
  }
}
