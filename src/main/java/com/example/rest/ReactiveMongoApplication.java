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
import java.util.Optional;

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

//            studentRepo.save(student);

//            Optional<Student> byEmail = studentRepo.findByEmail("q@gmail.com");
//            byEmail.ifPresentOrElse(
//                    (email) -> System.out.println("email: " + email),
//                    () -> new RuntimeException());

            Optional<Student> byEmail2 = studentRepo.findByEmail("q2@gmail.com");
            System.out.println(byEmail2);
            byEmail2.ifPresentOrElse(
                    (email) -> System.out.println("email: " + email),
                    () -> System.out.println("Fail...."));
        };
    }

}
