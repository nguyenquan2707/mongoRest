package com.example.rest;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class StudentService {

    private final StudentRepo studentRepo;

    public List<Student> getAllStudents() {
        return studentRepo.findAll();
    }
}
