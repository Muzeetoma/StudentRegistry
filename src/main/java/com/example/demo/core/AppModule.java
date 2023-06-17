package com.example.demo.core;

import com.example.demo.repository.StudentRepository;
import com.example.demo.repository.UserRepository;
import com.google.inject.AbstractModule;

public class AppModule extends AbstractModule {

  private final StudentRepository studentRepository = new StudentRepository();
  private final UserRepository userRepository = new UserRepository();

  @Override
  protected void configure() {
    super.configure();
    bind(StudentRepository.class).toInstance(studentRepository);
    bind(UserRepository.class).toInstance(userRepository);
  }
}
