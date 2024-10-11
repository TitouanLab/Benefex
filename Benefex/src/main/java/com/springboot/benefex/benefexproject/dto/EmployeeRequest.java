package com.springboot.benefex.benefexproject.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class EmployeeRequest {
    private String title;
    private String firstName;
    private String surname;
    @JsonFormat(pattern="dd-MM-yyyy")
    private LocalDate dateOfBirth;
    private String gender;
    private String email;
    private String address;
}
