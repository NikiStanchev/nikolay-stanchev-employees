package com.nikolay.stanchev.employees.repository.impl;

import com.nikolay.stanchev.employees.domain.Employee;
import com.nikolay.stanchev.employees.repository.EmployeeRepository;
import org.springframework.core.io.InputStreamSource;
import org.springframework.stereotype.Repository;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class EmployeeRepositoryImpl implements EmployeeRepository {
    @Override
    public List<Employee> readAllEmployeesFromFile(InputStreamSource source) {


        BufferedReader br;
        List<Employee> result = new ArrayList<>();
        try {

            String line;
            InputStream is = source.getInputStream();
            br = new BufferedReader(new InputStreamReader(is));


            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if(data.length > 3){
                    result.add(new Employee(data[0], data[1], parseDate(data[2]), parseDate(data[3])));
                }
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }

        return result;
    }

    private LocalDate parseDate(String date){

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(
                        "[yyyy-MM-dd]" +
                        "[yyyy/MM/dd]" +
                        "[dd-MM-yyyy]" +
                        "[yyyy.MM.dd G 'at' HH:mm:ss z]" +
                        "[EEE, MMM d, ''yy]" +
                        "[h:mm a]" +
                        "[dd/MM/yyyy]" +
                        "[dd/MM/yy]" +
                        "[yyyyMMdd]");
        try{
            return LocalDate.parse(date.trim(), formatter);
        }catch (DateTimeParseException e){
            if(date.trim().equalsIgnoreCase("NULL")){
                return LocalDate.now();
            }else {
                throw e;
            }
        }
    }
}