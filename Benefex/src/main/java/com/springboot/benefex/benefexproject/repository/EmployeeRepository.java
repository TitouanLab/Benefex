package com.springboot.benefex.benefexproject.repository;

import com.springboot.benefex.benefexproject.model.Employee;
import org.springframework.data.repository.CrudRepository;

public interface EmployeeRepository extends CrudRepository<Employee, Long> {
}
