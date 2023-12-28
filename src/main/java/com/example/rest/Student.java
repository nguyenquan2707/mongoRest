package com.example.rest;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.List;

@Data
@Document
@NoArgsConstructor
@AllArgsConstructor
public class Student {

    @Id
    private String id;

    private String firstName;
    private String lastName;
    private String email;

    private Gender gender;

    @Indexed(unique = true)
    private Address address;

    private List<String> favouriteSubjects;

    private BigDecimal totalSpentInBooks;
    private LocalDateTime created;
}
