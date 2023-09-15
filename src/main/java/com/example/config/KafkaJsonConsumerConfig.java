package com.example.config;

import com.fasterxml.jackson.databind.JsonNode;
import java.util.HashMap;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.DefaultErrorHandler;
import org.springframework.kafka.support.serializer.ErrorHandlingDeserializer;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.stereotype.Component;
import org.springframework.util.backoff.FixedBackOff;

@EnableKafka
@Component
@Slf4j
public class KafkaJsonConsumerConfig {

  @Value(value = "${spring.kafka.bootstrap-servers}")
  private String bootstrapAddress;

  @Value(value = "${spring.kafka.consumer.group-id}")
  private String groupId;

  @Bean
  public ConsumerFactory<String, JsonNode> jsonConsumerFactory() {
    Map<String, Object> props = new HashMap<>();
    props.put(
        ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,
        bootstrapAddress);
    props.put(
        ConsumerConfig.GROUP_ID_CONFIG,
        groupId);
    props.put(
        ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG,
        false);
    ErrorHandlingDeserializer<JsonNode> errorHandlingDeserializer
        = new ErrorHandlingDeserializer<>(new JsonDeserializer<>(JsonNode.class));
    return new DefaultKafkaConsumerFactory<>(props, new StringDeserializer(),
        errorHandlingDeserializer);
  }

  @Bean(name = "basicJsonKafkaListenerContainerFactory")
  public ConcurrentKafkaListenerContainerFactory<String, JsonNode>
  basicJsonKafkaListenerContainerFactory() {

    ConcurrentKafkaListenerContainerFactory<String, JsonNode> factory =
        new ConcurrentKafkaListenerContainerFactory<>();
    factory.setConsumerFactory(jsonConsumerFactory());
    factory.setCommonErrorHandler(new DefaultErrorHandler(new FixedBackOff(1000L, 2L)));
    return factory;
  }


}
