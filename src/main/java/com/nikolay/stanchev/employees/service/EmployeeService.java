package com.nikolay.stanchev.employees.service;


import com.nikolay.stanchev.employees.domain.EmployeeCouple;
import org.springframework.core.io.InputStreamSource;

import java.util.List;

public interface EmployeeService {

    List<EmployeeCouple> findAllCouples(InputStreamSource source);
}
