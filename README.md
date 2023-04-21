## Project folder
### 01-spring-boot-rest-crud
This is the Student Information Service API. 
It comprises of functions that retrieve data of existing students 
from a MySQL database, manage exceptions related to student not found,
and handle general exceptions.
All student information is hardcoded in the database. 
We plan to create all the necessary functions in the 02 folder subsequently. 
It is important to note that the project in this folder is just a pilot project.
### 02-spring-boot-rest-crud-employee
#### This project is to mimic Salesforce's CRM system  
Create a REST API for the Employee Directory
REST clients should be able to  
• Get a list of employees  
• Get a single employee by id  
• Add a new employee  
• Update an employee  
• Delete an employee  

Restful service endpoints' description  
| HTTP Method | Endpoint |  CRUD Action |  
| ----------- | ----------- |  ----------- |   
| POST | `/api/employees` | Create a new employee |  
| GET | `/api/employees` | Read a list of employees |  
| GET | `/api/employees/{employeeId}` | Read a single employee |  
| PUT | `/api/employees` | Update an existing employee |  
| DELETE | `/api/employees/{employeeId}` | Delete an existing employee | 

The employee table has four columns id, first_name, last_name and email.
#### Requirements
• Assign the actions via http methods  
• CRUD support for backend database, Rest API security

#### Technical stack
Maven, Git, Spring boot, Spring MVC, Hibernate, JPA, Thymeleaf, Spring MVC, Postman

#### Keywords
AOP Programming, Java Spring Boot Framework,
Spring data JPA (Database related work, automatic CRUD implementation),
Web (Restful services),
Spring Boot dev tool (automatic reloading during development),
mysql-connector-j (mysql JDBC driver),
Maven (packaging our application and running it),
Data Access Object (DAO) - helper class to communicate with DB,
DAO layer, Service layer,
Spring Data Rest (automatically creating Rest API given Entity and Primary key) -
Configuration, Pagination and Sorting

```Application Architechture Version 1 
  graph LR
      A[Employee REST Controller]-->B[Employee Service]
      B-->C[Employee DAO; JPA API]
      C-->D[DB]
```

```Application Architechture Version 2
  graph LR
      A[Employee REST Controller]-->B[Employee Service]
      B-->C[Employee DAO; Spring Data JPA]
      C-->D[DB]
```

```Application Architechture Version 3
  graph LR
      A[Spring Data REST `/employees`]-->B[Employee DAO; Spring Data JPA]
      B-->C[DB]
```