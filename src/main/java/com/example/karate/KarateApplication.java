package com.example.karate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
@EnableJpaRepositories("com.example")
@EntityScan("com.example")
@ComponentScan(basePackages = "com.example")
public class KarateApplication {

  public static void main(String[] args) {
    SpringApplication.run(KarateApplication.class, args);
  }

}
