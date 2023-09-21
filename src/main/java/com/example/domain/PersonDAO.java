package com.example.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.Data;
import org.hibernate.annotations.CurrentTimestamp;
import org.hibernate.tuple.GenerationTiming;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;

@Entity
@Table(name = "Person")
@Data
public class PersonDAO  {

  @Column(name="first_name", nullable = false, length = 50)
  String firstName;
  @Column(name="last_name", nullable = false, length = 50)
  String LastName;
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  Long id;

  @Column(name="createdTime", nullable = true, length = 50)
  @CurrentTimestamp(timing = GenerationTiming.INSERT)
  LocalDateTime createdTime;
  @Column(name="createdBy", nullable = true, length = 50)
  @CreatedBy
  String createdBy;
  @Column(name="lastModifiedTime", nullable = true, length = 50)
  @CurrentTimestamp(timing = GenerationTiming.ALWAYS)
  LocalDateTime lastModifiedTime;
  @Column(name="modifiedBy", nullable = true, length = 50)
  @LastModifiedBy
  String modifiedBy;

}

