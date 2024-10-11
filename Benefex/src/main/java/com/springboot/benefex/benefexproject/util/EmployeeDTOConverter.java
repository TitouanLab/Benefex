package com.springboot.benefex.benefexproject.util;

import com.springboot.benefex.benefexproject.dto.EmployeeRequest;
import com.springboot.benefex.benefexproject.dto.EmployeeResponse;
import com.springboot.benefex.benefexproject.model.Employee;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class EmployeeDTOConverter {

    public List<EmployeeResponse> convertEmployeesToDTOs(List<Employee> employees) {
        return employees.stream().map(this::convertEmployeeToDTO).collect(Collectors.toList());
    }

    public EmployeeResponse convertEmployeeToDTO(Employee employee) {
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

    public Employee convertDTOToEmployee(EmployeeRequest employeeRequest) {
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
}
