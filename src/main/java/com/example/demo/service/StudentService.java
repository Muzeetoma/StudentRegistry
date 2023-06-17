package com.example.demo.service;

import com.example.demo.dto.StudentReq;
import com.example.demo.model.Student;
import com.example.demo.repository.StudentRepository;
import com.github.javafaker.Faker;
import com.google.inject.Inject;
import java.util.ArrayList;
import java.util.List;

public class StudentService {

  @Inject
  StudentRepository studentRepository;

  public List<Student> getStudents(){
    return new ArrayList<>(studentRepository.getStudents());
  }

  public boolean deleteStudent(final String id){
   return studentRepository.delete(id);
  }

  public void addStudent(final StudentReq studentReq){
    studentRepository.add(studentReq);
  }
  public void fakeStudents(){
    if(getStudents().isEmpty()) {
      final Faker faker = new Faker();
      for (int i = 0; i < 10; i++) {
        studentRepository.add(StudentReq.builder()
            .name(faker.name().fullName())
            .matricNumber("15/"+faker.number().digits(8))
            .department("Computer Science")
            .build());
      }
    }
  }
}
