package com.springboot.benefex.benefexproject.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private UUID employeeNo;
    private String title;
    private String firstName;
    private String surname;
    private LocalDate dateOfBirth;
    private String gender;
    private String email;
    private String address;
}
