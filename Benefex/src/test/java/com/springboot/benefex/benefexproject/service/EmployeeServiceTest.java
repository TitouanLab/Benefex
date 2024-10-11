package com.springboot.benefex.benefexproject.service;

import com.springboot.benefex.benefexproject.dto.EmployeeRequest;
import com.springboot.benefex.benefexproject.dto.EmployeeResponse;
import com.springboot.benefex.benefexproject.model.Employee;
import com.springboot.benefex.benefexproject.repository.EmployeeRepository;
import com.springboot.benefex.benefexproject.util.EmployeeDTOConverter;
import com.springboot.benefex.benefexproject.validator.EmployeeValidator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
public class EmployeeServiceTest {

    @MockBean
    private EmployeeRepository employeeRepository;

    @MockBean
    private EmployeeValidator employeeValidator;

    @Autowired
    private EmployeeService employeeService;

    @Test
    public void createEmployee() {
        EmployeeRequest employeeRequest = createValidEmployeeRequest();
        Employee employeeSaved = EmployeeDTOConverter.convertDTOToEmployee(employeeRequest);
        employeeSaved.setId(1);
        when(employeeRepository.save(any(Employee.class))).thenReturn(employeeSaved);

        EmployeeResponse result = employeeService.createEmployee(employeeRequest);

        assertNotNull(result);
        assertEquals(employeeSaved.getId(), result.getId());
        assertEquals(employeeSaved.getEmployeeNo(), result.getEmployeeNo());
        assertEquals(employeeSaved.getTitle(), result.getTitle());
        assertEquals(employeeSaved.getFirstName(), result.getFirstName());
        assertEquals(employeeSaved.getSurname(), result.getSurname());
        assertEquals(employeeSaved.getDateOfBirth(), result.getDateOfBirth());
        assertEquals(employeeSaved.getGender(), result.getGender());
        assertEquals(employeeSaved.getEmail(), result.getEmail());
        assertEquals(employeeSaved.getAddress(), result.getAddress());
    }

    private EmployeeRequest createValidEmployeeRequest() {
        return new EmployeeRequest("Mr", "John", "Smith", LocalDate.of(1900, 1, 1), "Male", "john.smith@gmail.com", "1 Somewhere Road");
    }
}
