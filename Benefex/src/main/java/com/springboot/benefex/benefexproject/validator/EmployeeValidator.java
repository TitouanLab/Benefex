package com.springboot.benefex.benefexproject.validator;

import com.springboot.benefex.benefexproject.dto.EmployeeRequest;
import com.springboot.benefex.benefexproject.repository.EmployeeRepository;
import exception.EmployeeInputParamNullOrEmpty;
import exception.EmployeeInvalidGenderException;
import exception.InvalidEmployeeEmailException;
import exception.NotUniqueEmployeeEmailException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

@Component
public class EmployeeValidator {

    public static final List<String> VALID_GENDERS = Arrays.asList("male", "female", "other");

    @Autowired
    private EmployeeRepository employeeRepository;

    public void validateEmployeeRequest(EmployeeRequest employee) {
        validateTitle(employee.getTitle());
        validateFirstName(employee.getFirstName());
        validateSurname(employee.getSurname());
        validateDateOfBirth(employee.getDateOfBirth());
        validateGender(employee.getGender());
        validateEmail(employee.getEmail());
    }

    public void validateTitle(String title) {
        if (title == null || title.isEmpty()) {
            throw new EmployeeInputParamNullOrEmpty("title");
        }
    }

    public void validateFirstName(String firstName) {
        if (firstName == null || firstName.isEmpty()) {
            throw new EmployeeInputParamNullOrEmpty("first name");
        }
    }

    public void validateSurname(String surName) {
        if (surName == null || surName.isEmpty()) {
            throw new EmployeeInputParamNullOrEmpty("surname");
        }
    }

    public void validateDateOfBirth(LocalDate dateOfBirth) {
        if (dateOfBirth == null) {
            throw new EmployeeInputParamNullOrEmpty("date of birth");
        }
    }

    public void validateGender(String gender) {
        if (gender == null || gender.isEmpty()) {
            throw new EmployeeInputParamNullOrEmpty("gender");
        }
        if (!VALID_GENDERS.contains(gender.toLowerCase())) {
            throw new EmployeeInvalidGenderException();
        }
    }

    private void validateEmail(String email) {
        if (email == null || email.isEmpty()) {
            throw new EmployeeInputParamNullOrEmpty("email");
        }
        Pattern validEmailAddress =
                Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
        if (!validEmailAddress.matcher(email).matches()) {
            throw new InvalidEmployeeEmailException();
        }
        if (employeeRepository.findByEmail(email) != null) {
            throw new NotUniqueEmployeeEmailException();
        }
    }
}
