package com.luv2code.springboot.cruddemo.dao;

import com.luv2code.springboot.cruddemo.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
  // no need to write any code
  // spring data Jpa will create the DAO for free

  // add a method to sort by last name
  // spring data JPA will parse the method name
  // Looks for a specific format and pattern
  // Automatic creates appropriate query
  public List<Employee> findAllByOrderByLastNameAsc();
}
