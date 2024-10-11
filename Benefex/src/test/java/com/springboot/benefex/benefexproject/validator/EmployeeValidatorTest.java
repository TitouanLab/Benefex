package com.springboot.benefex.benefexproject.validator;

import com.springboot.benefex.benefexproject.dto.EmployeeRequest;
import com.springboot.benefex.benefexproject.model.Employee;
import com.springboot.benefex.benefexproject.repository.EmployeeRepository;
import exception.EmployeeInputParamNullOrEmpty;
import exception.InvalidEmployeeEmailException;
import exception.NotUniqueEmployeeEmailException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;

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
        EmployeeRequest employeeRequest = createValidEmployeeRequest();
        employeeValidator.validateEmployeeRequest(employeeRequest);
    }

    @Test
    public void validateEmployeeRequest_nullTitle_throwsNullParamException() {
        EmployeeRequest employeeRequest = createValidEmployeeRequest();
        employeeRequest.setTitle(null);
        assertThrows(EmployeeInputParamNullOrEmpty.class, () -> employeeValidator.validateEmployeeRequest(employeeRequest));
    }

    @Test
    public void validateEmployeeRequest_emptyTitle_throwsNullParamException() {
        EmployeeRequest employeeRequest = createValidEmployeeRequest();
        employeeRequest.setTitle("");
        assertThrows(EmployeeInputParamNullOrEmpty.class, () -> employeeValidator.validateEmployeeRequest(employeeRequest));
    }

    @Test
    public void validateEmployeeRequest_nullFirstName_throwsNullParamException() {
        EmployeeRequest employeeRequest = createValidEmployeeRequest();
        employeeRequest.setFirstName(null);
        assertThrows(EmployeeInputParamNullOrEmpty.class, () -> employeeValidator.validateEmployeeRequest(employeeRequest));
    }

    @Test
    public void validateEmployeeRequest_emptyFirstName_throwsNullParamException() {
        EmployeeRequest employeeRequest = createValidEmployeeRequest();
        employeeRequest.setFirstName("");
        assertThrows(EmployeeInputParamNullOrEmpty.class, () -> employeeValidator.validateEmployeeRequest(employeeRequest));
    }

    @Test
    public void validateEmployeeRequest_nullSurname_throwsNullParamException() {
        EmployeeRequest employeeRequest = createValidEmployeeRequest();
        employeeRequest.setSurname(null);
        assertThrows(EmployeeInputParamNullOrEmpty.class, () -> employeeValidator.validateEmployeeRequest(employeeRequest));
    }

    @Test
    public void validateEmployeeRequest_emptySurname_throwsNullParamException() {
        EmployeeRequest employeeRequest = createValidEmployeeRequest();
        employeeRequest.setSurname("");
        assertThrows(EmployeeInputParamNullOrEmpty.class, () -> employeeValidator.validateEmployeeRequest(employeeRequest));
    }

    @Test
    public void validateEmployeeRequest_nullDateOfBirth_throwsNullParamException() {
        EmployeeRequest employeeRequest = createValidEmployeeRequest();
        employeeRequest.setDateOfBirth(null);
        assertThrows(EmployeeInputParamNullOrEmpty.class, () -> employeeValidator.validateEmployeeRequest(employeeRequest));
    }

    @Test
    public void validateEmployeeRequest_nullEmail_throwsNullParamException() {
        EmployeeRequest employeeRequest = createValidEmployeeRequest();
        employeeRequest.setEmail(null);
        assertThrows(EmployeeInputParamNullOrEmpty.class, () -> employeeValidator.validateEmployeeRequest(employeeRequest));
    }

    @Test
    public void validateEmployeeRequest_emptyEmail_throwsNullParamException() {
        EmployeeRequest employeeRequest = createValidEmployeeRequest();
        employeeRequest.setEmail("");
        assertThrows(EmployeeInputParamNullOrEmpty.class, () -> employeeValidator.validateEmployeeRequest(employeeRequest));
    }

    @Test
    public void validateEmployeeRequest_noAtSign_throwsInvalidEmailException() {
        EmployeeRequest employeeRequest = createValidEmployeeRequest();
        employeeRequest.setEmail("john.smith.com");
        assertThrows(InvalidEmployeeEmailException.class, () -> employeeValidator.validateEmployeeRequest(employeeRequest));
    }

    @Test
    public void validateEmployeeRequest_noDomainExtension_throwsInvalidEmailException() {
        EmployeeRequest employeeRequest = createValidEmployeeRequest();
        employeeRequest.setEmail("john.smith@gmail");
        assertThrows(InvalidEmployeeEmailException.class, () -> employeeValidator.validateEmployeeRequest(employeeRequest));
    }

    @Test
    public void validateEmployeeRequest_noEmailSuffix_throwsInvalidEmailException() {
        EmployeeRequest employeeRequest = createValidEmployeeRequest();
        employeeRequest.setEmail("@gmail.com");
        assertThrows(InvalidEmployeeEmailException.class, () -> employeeValidator.validateEmployeeRequest(employeeRequest));
    }

    @Test
    public void validateEmployeeRequest_notUniqueEmail_throwsNotUniqueEmailException() {
        EmployeeRequest employeeRequest = createValidEmployeeRequest();
        when(employeeRepository.findByEmail(employeeRequest.getEmail())).thenReturn(new Employee());
        assertThrows(NotUniqueEmployeeEmailException.class, () -> employeeValidator.validateEmployeeRequest(employeeRequest));
    }

    private EmployeeRequest createValidEmployeeRequest() {
        return new EmployeeRequest("Mr", "John", "Smith", LocalDate.of(1900, 1, 1), "Male", "john.smith@gmail.com", "1 Somewhere Road");

    }
}
