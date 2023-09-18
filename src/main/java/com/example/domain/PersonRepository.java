package com.example.domain;

import com.example.pojo.Person;
import java.util.ArrayList;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.RepositoryDefinition;
import org.springframework.stereotype.Repository;

@Repository
@Lazy
public interface PersonRepository extends CrudRepository<PersonDAO, Long> {
  ArrayList<PersonDAO> findAll();
  PersonDAO findPersonById(Long id);
}
