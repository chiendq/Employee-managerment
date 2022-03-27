package com.example.springboot2.repository;

import com.example.springboot2.model.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Long> {

    User findUserByUsername(String username);
}
