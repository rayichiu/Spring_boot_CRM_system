package com.luv2code.springboot.cruddemo.rest;

public class EmployeeNotFoundException extends RuntimeException{
  public EmployeeNotFoundException(String message) {
    super(message);
  }

  public EmployeeNotFoundException(String message, Throwable cause) {
    super(message, cause);
  }

  public EmployeeNotFoundException(Throwable cause) {
    super(cause);
  }
}
