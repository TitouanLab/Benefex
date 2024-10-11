package com.springboot.benefex.benefexproject.validator;

import com.springboot.benefex.benefexproject.dto.EmployeeRequest;
import com.springboot.benefex.benefexproject.repository.EmployeeRepository;
import exception.EmployeeEmailNullOrEmpty;
import exception.InvalidEmployeeEmailException;
import exception.NotUniqueEmployeeEmailException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class EmployeeValidator {

    @Autowired
    private EmployeeRepository employeeRepository;

    public void validateEmployeeRequest(EmployeeRequest employee) {
        validateEmail(employee.getEmail());
    }

    private void validateEmail(String email) {
        if (email == null || email.isEmpty()) {
            throw new EmployeeEmailNullOrEmpty();
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
