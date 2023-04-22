package com.luv2code.springboot.cruddemo.rest;

import com.luv2code.springboot.cruddemo.entity.Employee;
import com.luv2code.springboot.cruddemo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {

//  private EmployeeDAO employeeDAO;
//  // quick and dirty: inject employee dao (use constructor injection)
//  // theEmployeeDAO generate by Spring Boot
//  public EmployeeRestController(EmployeeDAO theEmployeeDAO){
//    employeeDAO = theEmployeeDAO;
//  }


  // instead using service to follow the application architecture
  private EmployeeService employeeService;
  @Autowired
  public EmployeeRestController(EmployeeService theEmployeeService){
    employeeService = theEmployeeService;
  }

  // expose "/employees" and return a list of employees
  @GetMapping("/employees")
  public List<Employee> findAll(){
    return employeeService.findAll();
  }

  // add mapping for GET /employees/{employeeId}
  @GetMapping("/employees/{employeeId}")
  public  Employee getEmployees(@PathVariable int employeeId){
    Employee theEmployee = employeeService.findById(employeeId);
    if (theEmployee == null){
      throw new RuntimeException("Employee id not found - " + employeeId);
    }
    return theEmployee;
  }

  // add mapping for Post /employees - add new employee
  // the employee data is gonna come in tje request body as JSON
  // -> add @RequestBody to handle binding JSON to the employee object
  @PostMapping("/employees")
  public Employee addEmployee(@RequestBody Employee theEmployee){
    // also just in case they pass an id in JSON ... set id to 0
    // this is to force a save of new item ... instead of update
    theEmployee.setId(0);
    Employee dbEmployee = employeeService.save(theEmployee);
    return dbEmployee;
  }

  // add mapping for PUT /employees - update existing employee
  @PutMapping("/employees")
  public Employee updateEmployee(@RequestBody Employee theEmployee){
    Employee dbEmployee = employeeService.save(theEmployee);
    return dbEmployee;
  }

  // add mapping for DELETE /employees/{employeeId} - delete employee
  @DeleteMapping("/employees/{employeeId}")
  public String deleteEmployee(@PathVariable int employeeId){
    Employee theEmployee = employeeService.findById(employeeId);
    if (theEmployee == null){
      throw new RuntimeException("Employee id not found - " + employeeId);
    }
    employeeService.deleteById(employeeId);
    return "Delete employee id - " + employeeId;
  }

}
