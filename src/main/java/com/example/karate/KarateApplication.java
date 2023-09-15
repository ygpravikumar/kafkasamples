package com.example.karate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.example")
public class KarateApplication {

  public static void main(String[] args) {
    SpringApplication.run(KarateApplication.class, args);
  }

}
