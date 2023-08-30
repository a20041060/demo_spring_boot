package com.example.demo.controller;

import com.example.demo.exception.ResourceNotFoundException;
import jakarta.validation.Valid;
import com.example.demo.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.demo.repository.EmployeeRepository;

import java.lang.module.ResolutionException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class EmployeeController {
    @Autowired
    private EmployeeRepository employeeRepository;
    @GetMapping("/employees")
    public List<Employee> getAllEmployees(){
        return this.employeeRepository.findAll();
    }

    @GetMapping("/employees/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable(value = "id") Long employeeId)
            throws ResourceNotFoundException {
        Employee employees = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));
        return ResponseEntity.ok().body(employees);
    }
    @PostMapping("/employees")
    public  Employee createEmployee(@RequestBody Employee employee){
        return this.employeeRepository.save(employee);
    };

    @PutMapping("/employees/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable(value="id") Long employeeId, @Valid @RequestBody Employee employeeDetails ){
        Employee employee= employeeRepository.findById(employeeId).orElseThrow(()->new ResolutionException("Employee not found for this id :: " + employeeId));
        employee.setEmail(employeeDetails.getEmail());
        employee.setFirstName(employeeDetails.getFirstName());
        employee.setLastName(employeeDetails.getLastName());
        return ResponseEntity.ok(this.employeeRepository.save(employee));
    }

    @DeleteMapping("/employees/{id}")
    public Map<String,Boolean> deleteEmployee(@PathVariable(value = "id") Long employeeId){
        Employee employee = employeeRepository.findById(employeeId).orElseThrow(()->new ResolutionException("Employee not found for this id :: " + employeeId));
        this.employeeRepository.delete(employee);

        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted",Boolean.TRUE);

        return response;
    }
}
