## Project folder
#### There are four project folders, which refer to the same CRM web service. The variation stems from using distinct spring boot application packages to write the code. The `03-spring-boot-rest-crud-employee-with-spring-data-jpa` folder is the main project folder that I add in most of the features (Web security and frontend application - thymeleaf).  

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

```
Application Architechture Version 1 
  graph LR
      A[Employee REST Controller]-->B[Employee Service]
      B-->C[Employee DAO; JPA API]
      C-->D[DB]
```

### 03-spring-boot-rest-crud-employee-with-spring-data-jpa
#### Using spring-data-jpa to generate Data Access Object (DAO)
No need to write EmployeeDAO interface and the corresponding DAO implementation

Further add Spring Security (Servlet Filters):
Protect the web resources by check the authenticate userID and password. If the user has the authorized role, user can access the resources.  
The noop and bcrypt script are in `spring-boot-employee-sql-script` folder, no need to write different java code. 

```
Application Architechture Version 2
  graph LR
      A[Employee REST Controller]-->B[Employee Service]
      B-->C[Employee DAO; Spring Data JPA]
      C-->D[DB]
```

### 04-spring-boot-rest-crud-employee-with-spring-data-rest
#### Using spring-data-rest to generate Rest end points
Don't need to write employee rest controller and Employee Service, spring data rest will create the end points for free. Just need the entity, the JPA repository, the Maven POM entry and the main spring boot application.

Change page size to 20 in the application.properties file.  
In EmployeeRepository (comment out the annotation) change the path from `/employees` to `/members`  

```
Application Architechture Version 3
  graph LR
      A[Spring Data REST `/employees`]-->B[Employee DAO; Spring Data JPA]
      B-->C[DB]
```
### Technical stack
Maven, Git, Spring boot, Spring MVC, Hibernate, JPA, Thymeleaf, Spring MVC, Postman

### Keywords
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
Spring Security (authenticaton and Authorization) - noop (plain text passwords), bcrypt (one way encryption algorithm)