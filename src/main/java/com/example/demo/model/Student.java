package com.example.demo.model;

import com.google.common.base.Objects;
import javafx.beans.property.StringProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Data
@Builder
public class Student {

  private String id;
  private String name;
  private String matricNumber;
  private String department;


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Student student = (Student) o;
    return Objects.equal(matricNumber, student.matricNumber);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(matricNumber);
  }
}
