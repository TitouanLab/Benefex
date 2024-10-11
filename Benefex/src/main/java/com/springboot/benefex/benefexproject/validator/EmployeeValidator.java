package com.springboot.benefex.benefexproject.validator;

import com.springboot.benefex.benefexproject.dto.EmployeeRequest;
import exception.EmployeeEmailNullOrEmpty;
import org.springframework.stereotype.Component;

@Component
public class EmployeeValidator {

    public void validateEmployeeRequest(EmployeeRequest employee) {
        validateEmail(employee.getEmail());
    }

    private void validateEmail(String email) {
        if (email == null || email.isEmpty()) {
            throw new EmployeeEmailNullOrEmpty();
        }
        //TODO validate format
        //TODO validate uniqueness
    }
}
