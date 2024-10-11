package com.springboot.benefex.benefexproject.service;

import com.springboot.benefex.benefexproject.dto.EmployeeRequest;
import com.springboot.benefex.benefexproject.dto.EmployeeResponse;
import com.springboot.benefex.benefexproject.model.Employee;
import com.springboot.benefex.benefexproject.repository.EmployeeRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public EmployeeResponse createEmployee(EmployeeRequest employeeRequest) {
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
