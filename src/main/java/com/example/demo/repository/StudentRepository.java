package com.example.demo.repository;

import com.example.demo.dto.StudentReq;
import com.example.demo.model.Student;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public class StudentRepository {

  private static final Set<Student> students = new HashSet<>();

  public void add(StudentReq studentReq) {
    Student student = Student.builder()
        .id(UUID.randomUUID().toString())
        .name(studentReq.getName())
        .matricNumber(studentReq.getMatricNumber())
        .department(studentReq.getDepartment())
        .build();
    students.add(student);
  }

  public Set<Student> getStudents() {
    return students;
  }

  private Optional<Student> findById(String id) {
    return students.stream()
        .filter(_student -> _student.getId().equals(id))
        .findFirst();
  }

  public boolean delete(String id) {
    Student student = findById(id).orElse(null);
    return student != null && students.remove(student);
  }

}
