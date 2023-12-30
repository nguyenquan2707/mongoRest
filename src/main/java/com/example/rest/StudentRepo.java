package com.example.rest;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface StudentRepo extends MongoRepository<Student, String> {

    Optional<Student> findByEmail(String email);
}
