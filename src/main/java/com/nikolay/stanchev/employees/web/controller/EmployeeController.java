package com.nikolay.stanchev.employees.web.controller;

import com.nikolay.stanchev.employees.domain.EmployeeCouple;
import com.nikolay.stanchev.employees.service.impl.EmployeeServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class EmployeeController {

    private final EmployeeServiceImpl employeeService;

    public EmployeeController(EmployeeServiceImpl employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/")
    public ModelAndView getAllPatients(ModelAndView modelAndView){
        modelAndView.setViewName("upload-file");
        return modelAndView;
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ModelAndView submit(@RequestParam("file") MultipartFile file, ModelAndView modelAndView) {
        List<EmployeeCouple> employeeCouples = employeeService.findAllCouples(file);
        modelAndView.addObject("employeeCouples", employeeCouples);
        modelAndView.setViewName("upload-file-success");
        return modelAndView;
    }
}
