package com.nikolay.stanchev.employees.repository;

import com.nikolay.stanchev.employees.domain.Employee;
import org.springframework.core.io.InputStreamSource;

import java.util.List;

public interface EmployeeRepository {

    List<Employee> readAllEmployeesFromFile(InputStreamSource source);
}