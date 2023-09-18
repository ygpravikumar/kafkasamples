package com.example.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity(name = "Person")
@Data
public class PersonDAO {


  String firstName;
  String LastName;
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  Long id;

}
