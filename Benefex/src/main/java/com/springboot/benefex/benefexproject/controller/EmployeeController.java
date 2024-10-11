package com.springboot.benefex.benefexproject.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String createEmployee() {
        return "Api called";
    }
}
