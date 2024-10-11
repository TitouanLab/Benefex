package com.springboot.benefex.benefexproject.service;

import com.springboot.benefex.benefexproject.dto.EmployeeRequest;
import com.springboot.benefex.benefexproject.dto.EmployeeResponse;
import com.springboot.benefex.benefexproject.model.Employee;
import com.springboot.benefex.benefexproject.repository.EmployeeRepository;
import com.springboot.benefex.benefexproject.validator.EmployeeValidator;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private EmployeeValidator employeeValidator;

    public List<EmployeeResponse> getAllEmployees() {
        return convertEmployeesToDTOs(employeeRepository.findAll());
    }

    public EmployeeResponse createEmployee(EmployeeRequest employeeRequest) {
        employeeValidator.validateEmployeeRequest(employeeRequest);
        Employee employee = convertDTOToEmployee(employeeRequest);
        employee = employeeRepository.save(employee);
        return convertEmployeeToDTO(employee);
    }

    private Employee convertDTOToEmployee(EmployeeRequest employeeRequest) {
        return Employee.builder()
                .employeeNo(UUID.randomUUID())
                .title(employeeRequest.getTitle())
                .firstName(employeeRequest.getFirstName())
                .surname(employeeRequest.getSurname())
                .dateOfBirth(employeeRequest.getDateOfBirth())
                .gender(employeeRequest.getGender())
                .email(employeeRequest.getEmail())
                .address(employeeRequest.getAddress())
                .build();
    }

    private List<EmployeeResponse> convertEmployeesToDTOs(List<Employee> employees) {
        return employees.stream().map(this::convertEmployeeToDTO).collect(Collectors.toList());
    }

    private EmployeeResponse convertEmployeeToDTO(Employee employee) {
        return EmployeeResponse.builder()
                .id(employee.getId())
                .employeeNo(employee.getEmployeeNo())
                .title(employee.getTitle())
                .firstName(employee.getFirstName())
                .surname(employee.getSurname())
                .dateOfBirth(employee.getDateOfBirth())
                .gender(employee.getGender())
                .email(employee.getEmail())
                .address(employee.getAddress())
                .build();
    }
}
