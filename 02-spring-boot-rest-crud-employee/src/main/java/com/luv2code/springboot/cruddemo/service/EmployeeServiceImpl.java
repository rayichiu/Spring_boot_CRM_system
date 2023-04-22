package com.luv2code.springboot.cruddemo.service;

import com.luv2code.springboot.cruddemo.dao.EmployeeDAO;
import com.luv2code.springboot.cruddemo.entity.Employee;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService{

  private EmployeeDAO employeeDAO;

  // constructor injection
  @Autowired
  public EmployeeServiceImpl(EmployeeDAO theEmployeeDAO){
    employeeDAO = theEmployeeDAO;
  }
  @Override
  public List<Employee> findAll() {
    return employeeDAO.findAll();
  }

  // delegate the call to DAO
  @Override
  public Employee findById(int theId) {
    return employeeDAO.findById(theId);
  }

  @Transactional
  @Override
  public Employee save(Employee theEmployee) {
    return employeeDAO.save(theEmployee);
  }

  @Transactional
  @Override
  public void deleteById(int theId) {
    employeeDAO.deleteById(theId);
  }
}
