package com.springboot.benefex.benefexproject.service;

import com.springboot.benefex.benefexproject.dto.EmployeeRequest;
import com.springboot.benefex.benefexproject.dto.EmployeeResponse;
import com.springboot.benefex.benefexproject.model.Employee;
import com.springboot.benefex.benefexproject.repository.EmployeeRepository;
import com.springboot.benefex.benefexproject.util.EmployeeDTOConverter;
import com.springboot.benefex.benefexproject.validator.EmployeeValidator;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private EmployeeValidator employeeValidator;

    public List<EmployeeResponse> getAllEmployees() {
        return EmployeeDTOConverter.convertEmployeesToDTOs(employeeRepository.findAll());
    }

    public EmployeeResponse createEmployee(EmployeeRequest employeeRequest) {
        employeeValidator.validateEmployeeRequest(employeeRequest);
        Employee employee = EmployeeDTOConverter.convertDTOToEmployee(employeeRequest);
        employee = employeeRepository.save(employee);
        return EmployeeDTOConverter.convertEmployeeToDTO(employee);
    }
}
