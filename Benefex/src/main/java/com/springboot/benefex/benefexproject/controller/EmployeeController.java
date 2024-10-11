package com.springboot.benefex.benefexproject.controller;

import com.springboot.benefex.benefexproject.dto.EmployeeResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EmployeeResponse createEmployee() {
        return new EmployeeResponse();
    }
}
