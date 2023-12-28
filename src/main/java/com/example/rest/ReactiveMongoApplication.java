package com.example.rest;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.query.Query;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.Arrays;

@SpringBootApplication
public class ReactiveMongoApplication {

    public static void main(String[] args) {
        SpringApplication.run(ReactiveMongoApplication.class, args);
    }

    @Bean
    CommandLineRunner runner(StudentRepo studentRepo) {

        return args -> {
            Student student = new Student(
                    null,
                    "Q",
                    "N",
                    "q@gmail.com",
                    Gender.MALE,
                    new Address("","",""),
                    Arrays.asList("A", "A"),
                    BigDecimal.valueOf(11),
                    LocalDateTime.now());

            studentRepo.save(student);

        };
    }

}
