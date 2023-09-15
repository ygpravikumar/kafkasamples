package com.example.messaging;

import com.example.pojo.Person;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@EnableKafka
@Slf4j
public class Consumers {

  @Autowired
  ObjectMapper objectMapper;
  @Value(value = "${spring.kafka.consumer.group-id}")
  private String groupId;

  @KafkaListener(topics = "string-topic", groupId = "karate", containerFactory =
      "kafkaListenerContainerFactory")
  public void listenFromMyTopic(@Payload String message,
      @Header(name = "kafka_receivedPartitionId") int partition) {
    log.info("Received message in group with message={} in parition={} with group={} for topic={}",
        message,
        partition, groupId, "my-topic");
  }

  /*@KafkaListener(topics = "basic-json-topic", groupId = "karate", containerFactory =
      "basicJsonKafkaListenerContainerFactory")
  public void listenFromBasicJsonTopic(@Payload JsonNode person,
      @Headers Map<String, Object> headerMap) {
    if (!person.isObject()) {
      log.error("Unknown exception while parsing message={} for topic={}", person,
          "basic-json-node");
    }
    Person personPojo = objectMapper.convertValue(person, Person.class);
    log.info("Received message in group with message={} in headers={} with group={} for topic={}",
        person,
        headerMap, groupId, "basic-json-topic");
  }*/

  @KafkaListener(topics = "basic-json-topic", groupId = "karate", containerFactory =
      "kafkaListenerContainerFactory")
  public void listenFromBasicJsonUsingStringTopic(@Payload String personString,
      @Headers Map<String, Object> headerMap) {
    try {
      Person personPojo = objectMapper.readValue(personString, Person.class);
      log.info("Received message in group with message={} in headers={} with group={} for topic={}",
          personString,
          headerMap, groupId, "basic-json-topic");
    } catch (Exception exception) {
      log.error("Error occured for message={} in topic={} with headers={}", personString, headerMap,
          "basic-json-topic");
    }
  }

}
