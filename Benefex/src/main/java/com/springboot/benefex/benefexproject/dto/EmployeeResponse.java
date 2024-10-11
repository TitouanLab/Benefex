package com.springboot.benefex.benefexproject.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class EmployeeResponse {
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
