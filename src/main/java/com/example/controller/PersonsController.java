package com.example.controller;

import com.example.domain.PersonDAO;
import com.example.domain.PersonMapper;
import com.example.domain.PersonRepository;
import com.example.pojo.Person;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@Validated
public class PersonsController {

  @Autowired
  PersonRepository repository;

  @Autowired
  PersonMapper personMapper;
  
  @PostMapping(value = "/api/persons")
  public PersonDAO createPerson(@Valid @RequestBody Person user) {
    log.info("Received person={}", user);
    PersonDAO personDAO = repository.save(personMapper.getPersonDAOFromPersonDTO(user));
    log.info("Created db object with details={}", personDAO);
    return personDAO;
  }

  @GetMapping(value = "/api/persons/{id}")
  public ResponseEntity getPerson(@Valid @PathVariable Long id) {
    log.info("Received personId={}", id);
    PersonDAO personDAO = repository.findPersonById(id);
    if(personDAO != null){
      return new ResponseEntity(personDAO, HttpStatus.OK);
    }
    else {
      return new ResponseEntity("Unable to find person with given id=" + id, HttpStatus.NOT_FOUND);
    }
  }

  @PostMapping(value = "/api/persons/{id}")
  public PersonDAO updatePerson(@Valid @RequestBody Person user, @Valid @PathVariable Long id) {
    log.info("Received person={}", user);
    PersonDAO personDAO = repository.findPersonById(id);
    if(personDAO != null){
      personDAO = personMapper.getPersonDAOFromPersonDTO(user);
      personDAO.setId(id);
    }
    else {
      throw new ObjectNotFoundException("Unable to find person with given id", id);
    }
    personDAO = repository.save(personDAO);
    log.info("Updated db object with details={}", personDAO);
    return personDAO;
  }

  @DeleteMapping(value = "/api/persons/{id}")
  public ResponseEntity deletePerson(@Valid @PathVariable Long id) {
    log.info("Received personId={}", id);
    PersonDAO personDAO = repository.findPersonById(id);
    log.info("Retreived db object with details={}", personDAO);
    String returnMessage="";
    if(personDAO != null){
      repository.deleteById(id);
      returnMessage = "Successfully deleted person with id=" + id;
    }
    else {
      returnMessage = "No object found with id=" + id;
    }
    return new ResponseEntity<>(returnMessage, HttpStatus.OK);
  }

}
