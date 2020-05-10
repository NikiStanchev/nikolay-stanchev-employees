package com.nikolay.stanchev.employees.service.impl;

import com.nikolay.stanchev.employees.domain.Employee;
import com.nikolay.stanchev.employees.domain.EmployeeCouple;
import com.nikolay.stanchev.employees.repository.impl.EmployeeRepositoryImpl;
import com.nikolay.stanchev.employees.service.EmployeeService;
import org.springframework.core.io.InputStreamSource;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

import static java.time.temporal.ChronoUnit.DAYS;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepositoryImpl employeeRepository;

    public EmployeeServiceImpl(EmployeeRepositoryImpl employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public List<EmployeeCouple> findAllCouples(InputStreamSource source) {

        List<Employee> employeeList = employeeRepository.readAllEmployeesFromFile(source);
        List<EmployeeCouple> result = new ArrayList<>();
        Set<String> projectIds = findAllProjectIds(employeeList);

        for(String id : projectIds){

            List<Employee> allEmployeesById =
                    employeeList
                    .stream()
                    .filter(e -> e.getProjectId().equals(id)).collect(Collectors.toList());

            int timeWorksTogether = 0;
            EmployeeCouple employeeCouple = new EmployeeCouple();
            for (int i = 0; i < allEmployeesById.size() - 1; i++) {
                Employee firstEmployee = allEmployeesById.get(i);

                for (int j = i + 1; j < allEmployeesById.size(); j++) {

                    Employee secondEmployee = allEmployeesById.get(j);

                    int tempTimeWorksTogether = timeWorksTogether(firstEmployee, secondEmployee);

                    if(tempTimeWorksTogether > timeWorksTogether){
                        employeeCouple.setFirstEmployeeId(firstEmployee.getId());
                        employeeCouple.setSecondEmployeeId(secondEmployee.getId());
                        employeeCouple.setDaysWorked(tempTimeWorksTogether);
                        employeeCouple.setProjectId(id);
                        timeWorksTogether = tempTimeWorksTogether;
                    }
                }
            }

            if(employeeCouple.getFirstEmployeeId() != null
                    && employeeCouple.getSecondEmployeeId() != null
                    && employeeCouple.getProjectId() != null){
                result.add(employeeCouple);
            }
        }
        return result;
    }

    private int timeWorksTogether(Employee firstEmployee, Employee secondEmployee) {
        LocalDate dateFrom =
                firstEmployee.getDateFrom().compareTo(secondEmployee.getDateFrom()) > 0 ?
                        firstEmployee.getDateFrom() :
                        secondEmployee.getDateFrom();
        LocalDate dateTo =
                firstEmployee.getDateTo().compareTo(secondEmployee.getDateTo()) < 0 ?
                        firstEmployee.getDateTo() :
                        secondEmployee.getDateTo();

        return (int) DAYS.between(dateFrom, dateTo);
    }

    private Set<String> findAllProjectIds(List<Employee> employees) {

        Set<String> result = new HashSet<>();
        for(Employee employee : employees){
            result.add(employee.getProjectId());
        }
        return result;
    }
}
