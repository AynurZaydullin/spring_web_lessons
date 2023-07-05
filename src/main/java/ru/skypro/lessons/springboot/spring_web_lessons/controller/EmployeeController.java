package ru.skypro.lessons.springboot.spring_web_lessons.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import ru.skypro.lessons.springboot.spring_web_lessons.service.EmployeeService;
import ru.skypro.lessons.springboot.spring_web_lessons.service.pojo.Employee;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

//    @GetMapping("/employees")
//    public List<Employee> getAllEmployees() {
//        return employeeService.getAllEmployees();
//    }
//
//    @GetMapping
//    public Employee getEmployeeById(@RequestParam("Name") int name) {
//        return employeeService.getEmployeeById(name);
//    }

    @PostMapping(value = "/upload" , consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void uploadFile(@RequestPart("employees") MultipartFile employees) {
        try {
            employeeService.addEmployees(employees);
        } catch (Throwable t) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }
    @PostMapping(value = "/report")
    public void report() {
        try {
            employeeService.report();
        } catch (Throwable t) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }
}