package com.springboot.benefex.benefexproject.repository;

import com.springboot.benefex.benefexproject.model.Employee;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface EmployeeRepository extends CrudRepository<Employee, Long> {

    public List<Employee> findAll();

    public Employee findByEmail(String email);
}
