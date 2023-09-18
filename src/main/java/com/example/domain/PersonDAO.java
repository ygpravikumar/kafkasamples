package com.example.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "Person")
@Data
public class PersonDAO {

  @Column(name="first_name", nullable = false, length = 50)
  String firstName;
  @Column(name="last_name", nullable = false, length = 50)
  String LastName;
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  Long id;

}
