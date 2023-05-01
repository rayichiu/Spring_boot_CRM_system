package com.luv2code.springboot.cruddemo.rest;

import com.luv2code.springboot.cruddemo.entity.Employee;
import com.luv2code.springboot.cruddemo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
//@RestController
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
  @ResponseBody
  @GetMapping("/employees")
  public List<Employee> findAll(){
    return employeeService.findAll();
  }

//  // add mapping for GET /employees/{employeeId}
//  @ResponseBody
//  @GetMapping("/employees/{employeeId}")
//  public Employee getEmployees(@PathVariable int employeeId){
//    Employee theEmployee = employeeService.findById(employeeId);
//    if (theEmployee == null){
//      throw new RuntimeException("Employee id not found - " + employeeId);
//    }
//    return theEmployee;
//  }

  // add mapping for GET /employees/{employeeId}
  @ResponseBody
  @GetMapping("/employees/{employeeId}")
  public Employee getEmployees(@PathVariable int employeeId){
    Employee theEmployee = employeeService.findById(employeeId);
    if (theEmployee == null){
      throw new EmployeeNotFoundException("Did not find employee id - " + employeeId);
//      throw new RuntimeException("Employee id not found - " + employeeId);
    }
    return theEmployee;
  }

  // add mapping for Post /employees - add new employee
  // the employee data is gonna come in tje request body as JSON
  // -> add @RequestBody to handle binding JSON to the employee object
  @ResponseBody
  @PostMapping("/employees")
  public Employee addEmployee(@RequestBody Employee theEmployee){
    // also just in case they pass an id in JSON ... set id to 0
    // this is to force a save of new item ... instead of update
    theEmployee.setId(0);
    Employee dbEmployee = employeeService.save(theEmployee);
    return dbEmployee;
  }

  // add mapping for PUT /employees - update existing employee
  @ResponseBody
  @PutMapping("/employees")
  public Employee updateEmployee(@RequestBody Employee theEmployee){
    Employee dbEmployee = employeeService.save(theEmployee);
    return dbEmployee;
  }

  // add mapping for DELETE /employees/{employeeId} - delete employee
  @ResponseBody
  @DeleteMapping("/employees/{employeeId}")
  public String deleteEmployee(@PathVariable int employeeId){
    Employee theEmployee = employeeService.findById(employeeId);
    if (theEmployee == null){
      throw new RuntimeException("Employee id not found - " + employeeId);
    }
    employeeService.deleteById(employeeId);
    return "Delete employee id - " + employeeId;
  }

  /*
  * Thymeleaf add on
  */
  @GetMapping("/employees/list")
  public String listEmployees(Model theModel) {

    // get the employees from db
    List<Employee> theEmployees = employeeService.findAll();

    // add to the spring model
    theModel.addAttribute("employees", theEmployees);

    return "employees/list-employees";
  }

  @GetMapping("/employees/showFormForAdd")
  public String showFormForAdd(Model theModel) {
    // create model attribute to bind form data
    Employee theEmployee = new Employee();
    theModel.addAttribute("employee", theEmployee);
    return "employees/employee-form";
  }

  @GetMapping("/employees/showFormForUpdate")
  public String showFormForUpdate(@RequestParam("employeeId") int theId,
                                  Model theModel) {
    // get the employee from the service
    Employee theEmployee = employeeService.findById(theId);
    // set employee as a model attribute to pre-populate the form
    theModel.addAttribute("employee", theEmployee);
    // send over to our form
    return "employees/employee-form";
  }

  @PostMapping("/employees/save")
  public String saveEmployee(@ModelAttribute("employee") Employee theEmployee){
    // save the employee
    employeeService.save(theEmployee);

    // use a redirect to prevent duplicate submissions
    return "redirect:/api/employees/list";
  }

  @GetMapping("/employees/delete")
  public String delete(@RequestParam("employeeId") int theId) {
    // delete the employee
    employeeService.deleteById(theId);
    // redirect to /employees/list
    return "redirect:/api/employees/list";
  }

  @GetMapping("/employees/home")
  public String showHome(){
    return "employees/home";
  }

  @GetMapping("/employees/showMyLoginPage")
  public String showMyLoginPage() {
//    return "employees/plain-login";
    return "employees/fancy-login";
  }
}
