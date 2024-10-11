package com.springboot.benefex.benefexproject.validator;

import com.springboot.benefex.benefexproject.dto.EmployeeRequest;
import exception.EmployeeEmailNullOrEmpty;
import exception.InvalidEmployeeEmailException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class EmployeeValidatorTest {

    @Autowired
    private EmployeeValidator employeeValidator;

    @Test
    public void validateEmail_validRequest_noException() {
        EmployeeRequest employeeRequest = new EmployeeRequest("Mr", "John", "Smith", LocalDate.of(1900, 1, 1), "Male", "john.smith@gmail.com", "1 Somewhere Road");
        employeeValidator.validateEmployeeRequest(employeeRequest);
    }

    @Test
    public void validateEmail_nullEmail_throwsNullNameException() {
        EmployeeRequest employeeRequest = new EmployeeRequest("Mr", "John", "Smith", LocalDate.of(1900, 1, 1), "Male", null, "1 Somewhere Road");
        assertThrows(EmployeeEmailNullOrEmpty.class, () -> employeeValidator.validateEmployeeRequest(employeeRequest));
    }

    @Test
    public void validateEmail_emptyEmail_throwsNullNameException() {
        EmployeeRequest employeeRequest = new EmployeeRequest("Mr", "John", "Smith", LocalDate.of(1900, 1, 1), "Male", "", "1 Somewhere Road");
        assertThrows(EmployeeEmailNullOrEmpty.class, () -> employeeValidator.validateEmployeeRequest(employeeRequest));
    }

    @Test
    public void validateEmail_noAtSign_throwsInvalidEmailException() {
        EmployeeRequest employeeRequest = new EmployeeRequest("Mr", "John", "Smith", LocalDate.of(1900, 1, 1), "Male", "john.smith.com", "1 Somewhere Road");
        assertThrows(InvalidEmployeeEmailException.class, () -> employeeValidator.validateEmployeeRequest(employeeRequest));
    }

    @Test
    public void validateEmail_noDomainExtension_throwsInvalidEmailException() {
        EmployeeRequest employeeRequest = new EmployeeRequest("Mr", "John", "Smith", LocalDate.of(1900, 1, 1), "Male", "john.smith@gmail", "1 Somewhere Road");
        assertThrows(InvalidEmployeeEmailException.class, () -> employeeValidator.validateEmployeeRequest(employeeRequest));
    }

    @Test
    public void validateEmail_noEmailSuffix_throwsInvalidEmailException() {
        EmployeeRequest employeeRequest = new EmployeeRequest("Mr", "John", "Smith", LocalDate.of(1900, 1, 1), "Male", "@gmail.com", "1 Somewhere Road");
        assertThrows(InvalidEmployeeEmailException.class, () -> employeeValidator.validateEmployeeRequest(employeeRequest));
    }
}
