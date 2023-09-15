package com.example.service;

import com.example.pojo.Person;
import java.util.concurrent.CompletableFuture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

@Service
public class KafkaJsonPayloadProducer {

  @Autowired
  private KafkaTemplate<String, Person> kafkaTemplate;

  public void sendMessage(String firstName, String lastName) {
    Person person = new Person(firstName, lastName);
    CompletableFuture<SendResult<String, Person>> future = kafkaTemplate.send("basic-json-topic",
        person);
    future.whenComplete((result, ex) -> {
      if (ex == null) {
        System.out.println("Sent message=[" + person +
            "] with offset=[" + result.getRecordMetadata().offset() + "]");
      } else {
        System.out.println("Unable to send message=[" +
            person + "] due to : " + ex.getMessage());
      }
    });
  }
}
