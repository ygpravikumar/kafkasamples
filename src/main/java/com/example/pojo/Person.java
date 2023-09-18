package com.example.pojo;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
@AllArgsConstructor
public class Person {
  @Length(max=50)
  @Pattern(regexp = "[a-zA-Z ,.'-]+")
  String firstName;

  @Length(max=50)
  @Pattern(regexp = "[a-zA-Z ,.'-]+")
  String lastName;
}
