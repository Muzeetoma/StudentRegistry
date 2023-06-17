package com.example.demo.repository;

import com.example.demo.model.User;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public class UserRepository {

  private static final Set<User> users = new HashSet<>();

  public void fakeUsers(){
    if(users.isEmpty()) {
      users.add(new User(UUID.randomUUID().toString(),
          "muze@gmail.com", "12345"));
      users.add(new User(UUID.randomUUID().toString(),
          "alozie@gmail.com", "67890"));
    }
  }

  public Optional<User> findByEmail(final String email){
   return users.stream().filter(user -> user.getEmail().equals(email))
        .findFirst();
  }
}
