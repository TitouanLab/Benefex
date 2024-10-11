package com.springboot.benefex.benefexproject.validator;

import com.springboot.benefex.benefexproject.dto.EmployeeRequest;
import com.springboot.benefex.benefexproject.model.Employee;
import com.springboot.benefex.benefexproject.repository.EmployeeRepository;
import exception.EmployeeEmailNullOrEmpty;
import exception.InvalidEmployeeEmailException;
import exception.NotUniqueEmployeeEmailException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@SpringBootTest
public class EmployeeValidatorTest {

    @MockBean
    private EmployeeRepository employeeRepository;

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

    @Test
    public void validateEmail_notUniqueEmail_throwsNotUniqueEmailException() {
        String email = "john.smith@gmail.com";

        when(employeeRepository.findByEmail(email)).thenReturn(new Employee());

        EmployeeRequest employeeRequest = new EmployeeRequest("Mr", "John", "Smith", LocalDate.of(1900, 1, 1), "Male", email, "1 Somewhere Road");
        assertThrows(NotUniqueEmployeeEmailException.class, () -> employeeValidator.validateEmployeeRequest(employeeRequest));
    }
}
