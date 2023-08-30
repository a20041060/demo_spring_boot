package com.example.demo.repository;

import com.example.demo.model.Employee;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;


@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Long> {

}
