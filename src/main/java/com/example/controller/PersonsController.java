package com.example.controller;

import com.example.pojo.Person;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class PersonsController {

  @PostMapping(value = "/api/persons")
  public Person publishPerson(Person user) {
    log.info("Received person={}", user);
    return user;
  }

}
