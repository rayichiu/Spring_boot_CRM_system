package com.luv2code.springboot.cruddemo.service;

import com.luv2code.springboot.cruddemo.dao.EmployeeRepository;
import com.luv2code.springboot.cruddemo.entity.Employee;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService{

  private EmployeeRepository employeeRepository;

  // constructor injection
  @Autowired
  public EmployeeServiceImpl(EmployeeRepository theEmployeeRepository){
    employeeRepository = theEmployeeRepository;
  }

  @Override
  public List<Employee> findAll() {
    return employeeRepository.findAllByOrderByLastNameAsc();
  }

  // delegate the call to DAO
  @Override
  public Employee findById(int theId) {
    Optional<Employee> result = employeeRepository.findById(theId);
    // get the data without checking for null, JPA use optional
    Employee theEmployee = null;
    if (result.isPresent()){
      theEmployee = result.get();
    }else{
      // we didn't find the employee
      throw new RuntimeException("Did not find employee id - " + theId);
    }
    return theEmployee;
  }

//  @Transactional JPA provides this function
  @Override
  public Employee save(Employee theEmployee) {
    return employeeRepository.save(theEmployee);
  }

//  @Transactional
  @Override
  public void deleteById(int theId) {
    employeeRepository.deleteById(theId);
  }
}
