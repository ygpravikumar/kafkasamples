package com.example.domain;

import com.example.pojo.Person;
import org.mapstruct.Mapper;

@Mapper
public interface PersonMapper {

  Person getPersonDTOFromPersonDAO(PersonDAO personDAO);
  PersonDAO getPersonDAOFromPersonDTO(Person personDTO);
}
