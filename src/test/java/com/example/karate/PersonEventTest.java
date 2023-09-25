package com.example.karate;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.assertj.core.api.Assertions.assertThat;
import static org.awaitility.Awaitility.await;

import com.example.domain.PersonDAO;
import com.example.domain.PersonRepository;
import com.example.pojo.Person;
import java.time.Duration;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.TestPropertySource;
import org.testcontainers.containers.KafkaContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

@SpringBootTest
@TestPropertySource(
    properties = {
        "spring.kafka.consumer.auto-offset-reset=earliest",
        "spring.datasource.url=jdbc:tc:mysql:8.0.32:///db",
        "spring.datasource.driver-class-name=org.testcontainers.jdbc.ContainerDatabaseDriver"
    }
)
@Testcontainers
class PersonEventTest {

  @Container
  static final KafkaContainer kafka = new KafkaContainer(
      DockerImageName.parse("confluentinc/cp-kafka:7.3.3")
  );

  @DynamicPropertySource
  static void overrideProperties(DynamicPropertyRegistry registry) {
    registry.add("spring.kafka.bootstrap-servers", kafka::getBootstrapServers);
  }

  @Autowired
  private KafkaTemplate<String, Person> kafkaTemplate;

  @Autowired
  private PersonRepository personRepository;

  @BeforeEach
  void setUp() {
    PersonDAO person = new PersonDAO();
    person.setFirstName("Ravi Kumar");
    person.setLastName("G");
    personRepository.save(person);
  }

  @Test
  void shouldHandleProductPriceChangedEvent() {
    Person event = new Person(
        "Ravi G",
        "G"
    );

    kafkaTemplate.send("basic-json-topic", event);

    await()
        .pollInterval(Duration.ofSeconds(3))
        .atMost(10, SECONDS)
        .untilAsserted(() -> {
          ArrayList<PersonDAO> optionalProduct = personRepository.findAll();
          assertThat(optionalProduct).isNotEmpty();
          //assertThat(optionalProduct.get().getCode()).isEqualTo("P100");
          //assertThat(optionalProduct.get().getPrice()).isEqualTo(new BigDecimal("14.50"));
        });
  }
}
