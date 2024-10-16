package com.springboot.benefex.benefexproject.dto;

import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Builder
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
