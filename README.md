"# ofss202303" 


Web Service
==============
A web service is a software system that supports interoperable machine-to-machine interaction over a network


SOAP	- wsdl
REST	- url	


==================

One Application to interact with another Application

Rest Web service
================


Use case : We want to create a rest url to welcome a user


Welcome 


@GET	- getting results
@POST	- create/insert
@PUT	- update
@DELETE	- delete


Use case : a crud rest api 	- Add /Delete /Update a product


http://localhost:8080/RESTfulExample/rest/products		-GET
http://localhost:8080/RESTfulExample/rest/products/1816		-GET

http://localhost:8080/RESTfulExample/rest/products		-POST




import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Employee {
		private int empId;
		private String empName;
		public int getEmpId() {
			return empId;
		}
		public void setEmpId(int empId) {
			this.empId = empId;
		}
		public String getEmpName() {
			return empName;
		}
		public void setEmpName(String empName) {
			this.empName = empName;
		}
		
}

----------

package com.resttraining;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/employee")
public class EmployeeResource {

    // This method is called if XML is requested
    @GET
    @Produces({MediaType.APPLICATION_XML})
    public Employee getXML() {
        Employee emp = new Employee();
        emp.setEmpId(100);
        emp.setEmpName("Ahmed_XML");
        return emp;
    }

   

}


=================

Using Response class to send different status codes

package com.resttraining;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

@Path("/employees")
public class EmployeeResource {

	//converted immtable list to mutable
	List<Employee> allEmployees = new ArrayList<>(Arrays.asList(
				new Employee(100, "Anu", "Chennai", 98000),
				new Employee(200, "Jaya", "Jaipur", 18000),
				new Employee(300, "Gayathri", "Chennai", 68000),
				new Employee(400, "Harish", "Mumbai", 98220),
				new Employee(500, "Ahmed", "Bangalore", 92200)
			));
			
	@GET
	//@Produces(MediaType.APPLICATION_XML)
	public List<Employee> getAllEmployee() {
		System.out.println("GETTING ALL EMPLOYEES");
		return allEmployees;
	}
	
	@GET
	@Path("/{employeeId}")
	public ResponseBuilder getEmployee(
			@PathParam("employeeId")Integer employeeId,
			@QueryParam("noOfRecords")Integer noOfRecords)
	{
		System.out.println("NoOfRecords wanted is :" +noOfRecords);
		Employee emp = allEmployees.stream().filter(t -> t.getEmployeeId().equals(employeeId)).findFirst().get();
		
		return Response.status(204).entity(emp);
	}
	
	@DELETE
	@Path("/{employeeId}")
	public List<Employee> deleteEmployee(@PathParam("employeeId")Integer employeeId) 
	{
		//delete the employee from allEmployees list
		allEmployees.removeIf(t -> t.getEmployeeId().equals(employeeId));
		return allEmployees;
	}
	
	@POST
	public List<Employee> insertEmployee(Employee employee) 
	{
		//add the employee to the allEmployees list
		allEmployees.add(employee);
		return allEmployees;
	}
	
	
	
	@PUT
	@Path("/{employeeId}")
	public List<Employee> updateEmployee(@PathParam("employeeId")Integer employeeId,Employee employee) 
	{
		//update the employee from allEmployees list
		for(int i =0;i<allEmployees.size(); i++) {
			Employee oldData = allEmployees.get(i);
			if( oldData.getEmployeeId().equals(employeeId))
			{
				allEmployees.set(i, employee);
			}
		}
		return allEmployees;
	}
}




Status codes roles :

200
404
500
201	- create
204	



XML 


Safe and Idempotent methods
======================




Oracle WebLogic Server 14c: Administration Essentials
===================================

Weblogic Server


	Admin Server		-1 
	Manager Server		-0 or many
	Node Manager		- separate process that accepts remote connections



Domain
==========
AdminServer
weblogic
weblogic1


Node Manager
weblogic
weblogic1












---------------

1. start to start the server 




--How to create new domain

domain - orcldomain


-----------How to deploy our web application in weblogic


war/jar	



7004


Web logic Cluster
* welogic version all should the same

192.168.33.102:8011,192.168.44.102:8012








-----------------
Oracle 19c

alter session set "_ORACLE_SCRIPT"=true;

 CREATE USER daniel IDENTIFIED BY daniel DEFAULT TABLESPACE "USERS" TEMPORARY TABLESPACE "TEMP";


 grant create session to daniel;

grant all privileges to daniel;

connect daniel/daniel;


=======================
DBeaver		- universal ui for db




SELECT * FROM EMPLOYEES e ;

SELECT * FROM DEPARTMENTS d ;


SELECT DEPARTMENT_ID ,DEPARTMENT_NAME  FROM DEPARTMENTS d ;/

SELECT *
FROM   dual;

SELECT sysdate FROM dual;


SELECT 2+3 FROM dual;


SELECT last_name, salary, salary + 300
FROM   employees;

-------Task1
---WAQ TO PRINT last_name, salary , AND 10% INCREMENTED SALARY AS WELL

SELECT last_name, salary, salary+0.1*salary
FROM employees e;


SELECT last_name, salary, COMMISSION_PCT 
FROM employees e;

SELECT last_name, salary, COMMISSION_PCT , salary+salary*COMMISSION_PCT MonthlySalary
FROM employees e;
-----Problem 


--colum aliases---

SELECT last_name, salary, COMMISSION_PCT , salary+salary*COMMISSION_PCT MonthlySalary
FROM employees e;

SELECT last_name, salary, COMMISSION_PCT , salary+salary*COMMISSION_PCT AS MonthlySalary
FROM employees e;


SELECT last_name, salary, COMMISSION_PCT , salary+salary*COMMISSION_PCT AS "Monthly Salary"
FROM employees e;


SELECT first_name || ' ' || last_name AS "FULL Name"
FROM employees e;


SELECT DISTINCT job_id FROM EMPLOYEES e ;

----------------Where 


SELECT last_name, salary, COMMISSION_PCT , salary+salary*COMMISSION_PCT AS "Monthly Salary"
FROM employees e
WHERE DEPARTMENT_ID = 90;


SELECT last_name, salary, COMMISSION_PCT , salary+salary*COMMISSION_PCT AS "Monthly Salary"
FROM employees e
WHERE MANAGER_ID  = 103;

SELECT last_name, salary, COMMISSION_PCT , salary+salary*COMMISSION_PCT AS "Monthly Salary"
FROM employees e
WHERE DEPARTMENT_ID = 90 AND  MANAGER_ID  = 103;

SELECT last_name, salary, COMMISSION_PCT , salary+salary*COMMISSION_PCT AS "Monthly Salary"
FROM employees e
WHERE DEPARTMENT_ID = 90 OR  MANAGER_ID  = 103;

----Not work
SELECT last_name, job_id, department_id
FROM   employees
WHERE  last_name = 'whalen' ;


SELECT last_name 
FROM   employees
WHERE  hire_date = '17-OCT-11' ;

SELECT last_name, hire_date 
FROM   employees
WHERE  hire_date = '2011-10-17' ;

-----BETWEEN
SELECT last_name, salary
FROM   employees
WHERE  salary NOT BETWEEN 2500 AND 3500 ;

-------LIKE
SELECT last_name, salary, COMMISSION_PCT , salary+salary*COMMISSION_PCT AS "Monthly Salary"
FROM employees e
WHERE first_name = 'A%'

SELECT first_name, last_name, salary, COMMISSION_PCT , salary+salary*COMMISSION_PCT AS "Monthly Salary"
FROM employees e
WHERE first_name like 'A%'

SELECT first_name, last_name, salary, COMMISSION_PCT , salary+salary*COMMISSION_PCT AS "Monthly Salary"
FROM employees e
WHERE first_name like '%a' 



SELECT employee_id, last_name, salary, manager_id
FROM   employees
WHERE  manager_id IN (100, 101, 201) ;

--WAQ to find all the employees whose first name second charatcer is either  e or  i 

SELECT * FROM EMPLOYEES WHERE first_name LIKE '_e%' OR first_name LIKE '_i%';

SELECT *
FROM employees
WHERE SUBSTR(first_name, 2, 1) IN ('e', 'i');


SELECT * FROM EMPLOYEES WHERE first_name IN('_e%','_i%')



------------Order by

SELECT FIRST_NAME ,LAST_NAME ,SALARY  FROM EMPLOYEES e ;

SELECT FIRST_NAME ,LAST_NAME ,SALARY  FROM EMPLOYEES e 
WHERE SALARY > 10000
ORDER BY SALARY 

SELECT FIRST_NAME ,LAST_NAME ,SALARY  FROM EMPLOYEES e 
WHERE SALARY > 10000
ORDER BY SALARY desc


SELECT FIRST_NAME ,LAST_NAME ,SALARY  FROM EMPLOYEES e 
WHERE SALARY > 10000
ORDER BY SALARY DESC, FIRST_NAME asc

---Where
---LIKE
---IN
---BETWEEN
---AND OR NOT
---ORDER by

----------sort the employees on hire date(latest first) and then salary greater


SELECT *
FROM employees
ORDER BY hire_date DESC, salary DESC;


----------------------Character 


SELECT * FROM EMPLOYEES e WHERE FIRST_NAME  = 'David'

SELECT * FROM EMPLOYEES e WHERE lower(FIRST_NAME)  = 'neena'

SELECT upper(first_name) FROM EMPLOYEES e ;

SELECT employee_id, CONCAT(first_name, last_name) NAME,
LENGTH(last_name), INSTR(last_name, 'a') "Contains 'a'?"
FROM   employees
WHERE  SUBSTR(last_name, -1, 1) = 'n';

----------------------

SELECT last_name,
  UPPER(CONCAT(SUBSTR(LAST_NAME, 1, 8), '_US'))
FROM   employees
WHERE  department_id = 60;


SELECT employee_id AS Even_Numbers, last_name
FROM employees 
WHERE MOD(employee_id,2) = 0;

SELECT employee_id AS Even_Numbers, last_name
FROM employees 
WHERE MOD(employee_id,2) = 0;


SELECT employee_id AS Odd_Numbers, last_name
FROM employees 
WHERE MOD(employee_id,2) = 1;


SELECT last_name, (SYSDATE-hire_date)/7 AS WEEKS
FROM   employees
WHERE  department_id = 90;


SELECT MONTHS_BETWEEN
           ('01-SEP-18','11-JAN-17')
           FROM dual;

          
----WAQ to print first_name and experience in years for all employees
          Steven	17
          Neena		15
          
          hire_date 

SELECT FIRST_NAME , round(MONTHS_BETWEEN(sysdate,hire_date)/12) FROM EMPLOYEES e ; 
SELECT first_name,round(MONTHS_BETWEEN(SYSDATE , HIRE_DATE)/12) AS experience FROM EMPLOYEES e ;
SELECT first_name, 2023 - EXTRACT (YEAR FROM hire_date) AS experience FROM EMPLOYEES;

SELECT first_name, ROUND((MONTHS_BETWEEN(SYSDATE, hire_date)/12),0) AS "Years of XP"
FROM employees e;


----------------Conversion function

SELECT first_name, last_name, department_id
FROM employees WHERE department_id < CONCAT('9', '0');


SELECT first_name, last_name, department_id
FROM employees WHERE department_id < '90'


SELECT employee_id, TO_CHAR(hire_date, 'MM/YY') Month_Hired
FROM   employees
WHERE  last_name = 'Higgins';


SELECT last_name,
       TO_CHAR(hire_date, 'fmDD Month YYYY')
       AS HIREDATE
FROM   employees;


SELECT last_name, TO_CHAR(hire_date, 'DD-Mon-YYYY')
FROM  employees
WHERE hire_date < TO_DATE('01 Jan, 10','DD Mon,RR');



SELECT last_name, salary, COMMISSION_PCT , salary+salary*COMMISSION_PCT MonthlySalary
FROM employees e;
-----Problem 

----Solution through NVL
SELECT last_name, salary, nvl(COMMISSION_PCT,0) , salary+salary*NVL(COMMISSION_PCT,0) MonthlySalary
FROM employees e;

-- REAL time example OF NVL
 SELECT first_name , nvl(manager_id,100) FROM EMPLOYEES e 
 WHERE MANAGER_ID IS null

 SELECT first_name , nvl(DEPARTMENT_ID,90)  FROM EMPLOYEES e 
 WHERE DEPARTMENT_ID  IS null

 
 ---NVL2
 SELECT last_name,  salary, commission_pct,
       NVL2(commission_pct, 
            'SAL+COMM', 'SAL') income
FROM   employees WHERE department_id IN (50, 80);


-------hands on
Please print LIKE this USING NVL2
			             (0 FOR NULL)
FIRST_name   salary	commission		monthlysalary		annualsalary



SELECT FIRST_NAME ,SALARY ,COMMISSION_PCT 
,NVL2(COMMISSION_PCT,SALARY +SALARY *COMMISSION_PCT ,SALARY) MONTHLY_SALARY,
NVL2(COMMISSION_PCT,SALARY +SALARY *COMMISSION_PCT ,SALARY)*12 ANNUAL_SALARY  
FROM EMPLOYEES e ;


SELECT first_name, salary, (nvl(commission_pct,0)*salary) AS commission,
NVL2(commission_pct*salary, salary+(nvl(commission_pct,0)*salary), salary) AS "Monthly Salary",
(NVL2(commission_pct*salary, salary+(nvl(commission_pct,0)*salary), salary))*12 AS "Annual Salary"
FROM EMPLOYEES;



----------

SELECT * FROM EMPLOYEES e ;

SELECT * FROM DEPARTMENTS d ;


SELECT DEPARTMENT_ID ,DEPARTMENT_NAME  FROM DEPARTMENTS d ;/

SELECT *
FROM   dual;

SELECT sysdate FROM dual;


SELECT 2+3 FROM dual;


SELECT last_name, salary, salary + 300
FROM   employees;

-------Task1
---WAQ TO PRINT last_name, salary , AND 10% INCREMENTED SALARY AS WELL

SELECT last_name, salary, salary+0.1*salary
FROM employees e;


SELECT last_name, salary, COMMISSION_PCT 
FROM employees e;

SELECT last_name, salary, COMMISSION_PCT , salary+salary*COMMISSION_PCT MonthlySalary
FROM employees e;
-----Problem 


--colum aliases---

SELECT last_name, salary, COMMISSION_PCT , salary+salary*COMMISSION_PCT MonthlySalary
FROM employees e;

SELECT last_name, salary, COMMISSION_PCT , salary+salary*COMMISSION_PCT AS MonthlySalary
FROM employees e;


SELECT last_name, salary, COMMISSION_PCT , salary+salary*COMMISSION_PCT AS "Monthly Salary"
FROM employees e;


SELECT first_name || ' ' || last_name AS "FULL Name"
FROM employees e;


SELECT DISTINCT job_id FROM EMPLOYEES e ;

----------------Where 


SELECT last_name, salary, COMMISSION_PCT , salary+salary*COMMISSION_PCT AS "Monthly Salary"
FROM employees e
WHERE DEPARTMENT_ID = 90;


SELECT last_name, salary, COMMISSION_PCT , salary+salary*COMMISSION_PCT AS "Monthly Salary"
FROM employees e
WHERE MANAGER_ID  = 103;

SELECT last_name, salary, COMMISSION_PCT , salary+salary*COMMISSION_PCT AS "Monthly Salary"
FROM employees e
WHERE DEPARTMENT_ID = 90 AND  MANAGER_ID  = 103;

SELECT last_name, salary, COMMISSION_PCT , salary+salary*COMMISSION_PCT AS "Monthly Salary"
FROM employees e
WHERE DEPARTMENT_ID = 90 OR  MANAGER_ID  = 103;

----Not work
SELECT last_name, job_id, department_id
FROM   employees
WHERE  last_name = 'whalen' ;


SELECT last_name 
FROM   employees
WHERE  hire_date = '17-OCT-11' ;

SELECT last_name, hire_date 
FROM   employees
WHERE  hire_date = '2011-10-17' ;

-----BETWEEN
SELECT last_name, salary
FROM   employees
WHERE  salary NOT BETWEEN 2500 AND 3500 ;

-------LIKE
SELECT last_name, salary, COMMISSION_PCT , salary+salary*COMMISSION_PCT AS "Monthly Salary"
FROM employees e
WHERE first_name = 'A%'

SELECT first_name, last_name, salary, COMMISSION_PCT , salary+salary*COMMISSION_PCT AS "Monthly Salary"
FROM employees e
WHERE first_name like 'A%'

SELECT first_name, last_name, salary, COMMISSION_PCT , salary+salary*COMMISSION_PCT AS "Monthly Salary"
FROM employees e
WHERE first_name like '%a' 



SELECT employee_id, last_name, salary, manager_id
FROM   employees
WHERE  manager_id IN (100, 101, 201) ;

--WAQ to find all the employees whose first name second charatcer is either  e or  i 

SELECT * FROM EMPLOYEES WHERE first_name LIKE '_e%' OR first_name LIKE '_i%';

SELECT *
FROM employees
WHERE SUBSTR(first_name, 2, 1) IN ('e', 'i');


SELECT * FROM EMPLOYEES WHERE first_name IN('_e%','_i%')



------------Order by

SELECT FIRST_NAME ,LAST_NAME ,SALARY  FROM EMPLOYEES e ;

SELECT FIRST_NAME ,LAST_NAME ,SALARY  FROM EMPLOYEES e 
WHERE SALARY > 10000
ORDER BY SALARY 

SELECT FIRST_NAME ,LAST_NAME ,SALARY  FROM EMPLOYEES e 
WHERE SALARY > 10000
ORDER BY SALARY desc


SELECT FIRST_NAME ,LAST_NAME ,SALARY  FROM EMPLOYEES e 
WHERE SALARY > 10000
ORDER BY SALARY DESC, FIRST_NAME asc

---Where
---LIKE
---IN
---BETWEEN
---AND OR NOT
---ORDER by

----------sort the employees on hire date(latest first) and then salary greater


SELECT *
FROM employees
ORDER BY hire_date DESC, salary DESC;


----------------------Character 


SELECT * FROM EMPLOYEES e WHERE FIRST_NAME  = 'David'

SELECT * FROM EMPLOYEES e WHERE lower(FIRST_NAME)  = 'neena'

SELECT upper(first_name) FROM EMPLOYEES e ;

SELECT employee_id, CONCAT(first_name, last_name) NAME,
LENGTH(last_name), INSTR(last_name, 'a') "Contains 'a'?"
FROM   employees
WHERE  SUBSTR(last_name, -1, 1) = 'n';

----------------------

SELECT last_name,
  UPPER(CONCAT(SUBSTR(LAST_NAME, 1, 8), '_US'))
FROM   employees
WHERE  department_id = 60;


SELECT employee_id AS Even_Numbers, last_name
FROM employees 
WHERE MOD(employee_id,2) = 0;

SELECT employee_id AS Even_Numbers, last_name
FROM employees 
WHERE MOD(employee_id,2) = 0;


SELECT employee_id AS Odd_Numbers, last_name
FROM employees 
WHERE MOD(employee_id,2) = 1;


SELECT last_name, (SYSDATE-hire_date)/7 AS WEEKS
FROM   employees
WHERE  department_id = 90;


SELECT MONTHS_BETWEEN
           ('01-SEP-18','11-JAN-17')
           FROM dual;

          
----WAQ to print first_name and experience in years for all employees
          Steven	17
          Neena		15
          
          hire_date 

SELECT FIRST_NAME , round(MONTHS_BETWEEN(sysdate,hire_date)/12) FROM EMPLOYEES e ; 
SELECT first_name,round(MONTHS_BETWEEN(SYSDATE , HIRE_DATE)/12) AS experience FROM EMPLOYEES e ;
SELECT first_name, 2023 - EXTRACT (YEAR FROM hire_date) AS experience FROM EMPLOYEES;

SELECT first_name, ROUND((MONTHS_BETWEEN(SYSDATE, hire_date)/12),0) AS "Years of XP"
FROM employees e;


----------------Conversion function

SELECT first_name, last_name, department_id
FROM employees WHERE department_id < CONCAT('9', '0');


SELECT first_name, last_name, department_id
FROM employees WHERE department_id < '90'


SELECT employee_id, TO_CHAR(hire_date, 'MM/YY') Month_Hired
FROM   employees
WHERE  last_name = 'Higgins';


SELECT last_name,
       TO_CHAR(hire_date, 'fmDD Month YYYY')
       AS HIREDATE
FROM   employees;


SELECT last_name, TO_CHAR(hire_date, 'DD-Mon-YYYY')
FROM  employees
WHERE hire_date < TO_DATE('01 Jan, 10','DD Mon,RR');



SELECT last_name, salary, COMMISSION_PCT , salary+salary*COMMISSION_PCT MonthlySalary
FROM employees e;
-----Problem 

----Solution through NVL
SELECT last_name, salary, nvl(COMMISSION_PCT,0) , salary+salary*NVL(COMMISSION_PCT,0) MonthlySalary
FROM employees e;

-- REAL time example OF NVL
 SELECT first_name , nvl(manager_id,100) FROM EMPLOYEES e 
 WHERE MANAGER_ID IS null

 SELECT first_name , nvl(DEPARTMENT_ID,90)  FROM EMPLOYEES e 
 WHERE DEPARTMENT_ID  IS null

 
 ---NVL2
 SELECT last_name,  salary, commission_pct,
       NVL2(commission_pct, 
            'SAL+COMM', 'SAL') income
FROM   employees WHERE department_id IN (50, 80);


-------hands on
Please print LIKE this USING NVL2
			             (0 FOR NULL)
FIRST_name   salary	commission		monthlysalary		annualsalary



SELECT FIRST_NAME ,SALARY ,COMMISSION_PCT 
,NVL2(COMMISSION_PCT,SALARY +SALARY *COMMISSION_PCT ,SALARY) MONTHLY_SALARY,
NVL2(COMMISSION_PCT,SALARY +SALARY *COMMISSION_PCT ,SALARY)*12 ANNUAL_SALARY  
FROM EMPLOYEES e ;


SELECT first_name, salary, (nvl(commission_pct,0)*salary) AS commission,
NVL2(commission_pct*salary, salary+(nvl(commission_pct,0)*salary), salary) AS "Monthly Salary",
(NVL2(commission_pct*salary, salary+(nvl(commission_pct,0)*salary), salary))*12 AS "Annual Salary"
FROM EMPLOYEES;


----Aggregate functions

SELECT sum(salary) from employees

SELECT sum(salary) from employees WHERE DEPARTMENT_ID = 90;

SELECT count(EMPLOYEE_ID) from employees WHERE DEPARTMENT_ID = 90;

SELECT avg(salary) from employees WHERE DEPARTMENT_ID = 60;


SELECT count(*) from employees

SELECT AVG(commission_pct)
FROM   employees;

SELECT AVG(NVL(commission_pct, 0))
FROM   employees;

--------average salary for all
SELECT avg(salary) from employees 

--------average salary for specific department id
SELECT avg(salary) from employees WHERE DEPARTMENT_ID =90;

--Average salary in the 
--EMPLOYEES table for 
--each department
SELECT DEPARTMENT_ID , avg(salary) from employees 
GROUP BY DEPARTMENT_ID


SELECT DEPARTMENT_ID , sum(salary) from employees 
GROUP BY DEPARTMENT_ID


-----Count number of employees in each department

Department_id	   NoOfEmployees
90					8
60					6
..
SELECT department_id,count(employee_id) AS "NoOfEmployees"  FROM EMPLOYEES GROUP BY DEPARTMENT_ID;

-----Count number of employees in each department more than 10 count 

-- HAVING clause -- WHERE clause use IN GROUP BY clause TO FILTER 
SELECT DEPARTMENT_ID , count(EMPLOYEE_ID) AS NoOfEmployees FROM EMPLOYEES 
GROUP BY DEPARTMENT_ID
HAVING COUNT(EMPLOYEE_ID)>10   ;

-----------grouping more than one column

SELECT DEPARTMENT_ID , job_id , sum(salary) AS TotalSalary FROM EMPLOYEES 
GROUP BY DEPARTMENT_ID , job_id
ORDER BY department_id;


-----------get all department's highest earning employee first name of all those whose 
----------- salary > 10000
----------sort the result on salary (highest first)


SELECT DEPARTMENT_ID  , FIRST_NAME , max(salary) FROM EMPLOYEES e 
GROUP BY DEPARTMENT_ID ,FIRST_NAME
HAVING  max(salary) > 10000
ORDER BY max(SALARY) DESC


SELECT DEPARTMENT_ID ,FIRST_NAME ,MAX(SALARY)  FROM EMPLOYEES e 
GROUP BY DEPARTMENT_ID, FIRST_NAME  
HAVING max(SALARY) >10000 
ORDER BY max(salary) DESC;



-----------get all department's highest earning employee first name of all those whose 
----------- salary > 10000
----------sort the result on salary (highest first)

------solution
SELECT DEPARTMENT_ID, max(FIRST_NAME) , max(SALARY) AS "HighestSalary" 
FROM EMPLOYEES 
WHERE SALARY > 10000
GROUP BY DEPARTMENT_ID 
ORDER BY max(salary) DESC ;


-------------------Joins

-------------natural join

SELECT employee_id,job_id ,job_title FROM EMPLOYEES e NATURAL JOIN JOBS j 


SELECT employee_id, FIRST_NAME , DEPARTMENT_ID  ,DEPARTMENT_name FROM EMPLOYEES e
NATURAL JOIN DEPARTMENTS d ;


----------using clause

SELECT employee_id, FIRST_NAME , DEPARTMENT_ID  ,DEPARTMENT_name 
FROM EMPLOYEES e JOIN DEPARTMENTS d 
using(department_id)

--------using clause with alias
SELECT e.employee_id, e.FIRST_NAME , e.DEPARTMENT_ID  ,d.DEPARTMENT_name 
FROM EMPLOYEES e JOIN DEPARTMENTS d 
using(manager_id)


-----Department name 	city		10 minutes
SELECT DEPARTMENT_NAME, CITY FROM DEPARTMENTS NATURAL JOIN LOCATIONS ;

SELECT d.DEPARTMENT_NAME, l.CITY FROM DEPARTMENTS d JOIN LOCATIONS l USING(LOCATION_ID);


--------------on clause
--Use the ON clause to specify arbitrary conditions or specify the columns to join.


SELECT DEPARTMENT_NAME, CITY FROM DEPARTMENTS d JOIN LOCATIONS L
ON (d.LOCATION_ID = l.LOCATION_ID)

---real time example ON clause ??

SELECT * FROM EMPLOYEES e ;
SELECT * FROM DEPARTMENTS d  ;


SELECT FIRST_NAME ,department_name FROM EMPLOYEES e JOIN DEPARTMENTS d 
ON e.EMPLOYEE_ID  = d.MANAGER_ID 

---INNER JOIN/EQUI

--------------OUTER JOIN

--LEFT
--RIGHT
---FULL


--ONLY matched DATA will mget displayed USING INNER JOIN
-- unmatched data will get displayed using outer join


--inner join
SELECT employee_id, FIRST_NAME , DEPARTMENT_ID  ,DEPARTMENT_name 
FROM EMPLOYEES e JOIN DEPARTMENTS d 
using(department_id)		-106 

---kimberley grant	-- he has not been assigned a department
--- many departments are not coming because no employees are there
SELECT * FROM departments;

--OUTER join
SELECT employee_id, FIRST_NAME , d.DEPARTMENT_ID  ,DEPARTMENT_name 
FROM EMPLOYEES e LEFT OUTER JOIN DEPARTMENTS d 
ON e.DEPARTMENT_ID  = d.DEPARTMENT_ID 

SELECT employee_id, FIRST_NAME , d.DEPARTMENT_ID  ,DEPARTMENT_name 
FROM EMPLOYEES e RIGHT OUTER JOIN DEPARTMENTS d 
ON e.DEPARTMENT_ID  = d.DEPARTMENT_ID 

SELECT employee_id, FIRST_NAME , d.DEPARTMENT_ID  ,DEPARTMENT_name 
FROM EMPLOYEES e FULL OUTER JOIN DEPARTMENTS d 
ON e.DEPARTMENT_ID  = d.DEPARTMENT_ID 	
	
	
---self join

SELECT * FROM EMPLOYEES e ;

Neena reports TO steven
..
John reports TO Nancy

SELECT e2.first_name || ' reports to ' ||  e1.first_name AS "Employee and Managers"
FROM EMPLOYEES e1 JOIN EMPLOYEES e2
ON (e1.EMPLOYEE_ID = e2.MANAGER_ID)


------------------------------------------------------------------------------------------------
--Use case Display all the employee name who is getting more than neena

--Approach1
SELECT salary FROM EMPLOYEES e  WHERE lower(FIRST_NAME) = 'neena';

SELECT FIRST_NAME  FROM EMPLOYEES e  WHERE SALARY > 17000;


--subquery --Approach2
SELECT FIRST_NAME  FROM EMPLOYEES e  
WHERE SALARY > (SELECT salary FROM EMPLOYEES e  WHERE lower(FIRST_NAME) = 'neena');


---hands on 
--- neena co workers (same department)
-- all the employees working in same department as neena

SELECT FIRST_NAME  FROM EMPLOYEES WHERE DEPARTMENT_ID = 
(SELECT DEPARTMENT_ID FROM EMPLOYEES WHERE LOWER(FIRST_NAME) = 'neena');

SELECT FIRST_NAME  FROM EMPLOYEES WHERE DEPARTMENT_ID IN 
(SELECT DEPARTMENT_ID FROM EMPLOYEES WHERE LOWER(FIRST_NAME) = 'david');


SELECT DEPARTMENT_ID  FROM EMPLOYEES  WHERE LOWER(FIRST_NAME) = 'david'
SELECT DEPARTMENT_ID  FROM EMPLOYEES  WHERE LOWER(FIRST_NAME) = 'neena'

SELECT last_name, job_id, salary
FROM   employees
WHERE  salary = 
                (SELECT MIN(salary)
                 FROM   employees);

SELECT last_name, job_id, salary
FROM   employees
WHERE  salary = 
                (SELECT max(salary)


--Display all the employees with the lowest salary in each department.

	SELECT first_name, department_id, salary
FROM employees
WHERE (salary, department_id) IN
      (SELECT min(salary), department_id
       FROM employees
       GROUP BY department_id)
ORDER BY department_id;

--Display all the employees with the less salary than the average salary in each department.
SELECT FIRST_NAME, DEPARTMENT_ID ,salary FROM EMPLOYEES e  WHERE salary < ANY 
(SELECT avg(salary) FROM EMPLOYEES e2 GROUP BY DEPARTMENT_ID) ;
	
	
	SET 
	
	UNION
	UNION ALL
	MINUS
	INTERSECT
	
	
	--------------
	SELECT * FROM loan;
	SELECT * FROM customer;
	SELECT * FROM TRANDETAILS t ;


Performs querying on this:

All tables with records are created, now we will perform queries on these tables:


Problem#1:

Write a query to display the customer number, firstname, customer’s date of birth. 
Display in sorted order of date of birth year and within that sort by firstname.

Soln:
  select custid,fname,dob 
  from customer
  order by dob,fname

Problem#2:

Write a query to display the customer’s number, first name, and middle name. 
The customer’s who don’t have a middle name, for them display the last name. 
Give the alias name as Cust_Name.

Soln:
  select custid,fname,nvl(mname,ltname)  cust_name
  from customer


Problem#3:

Write a query to display account number, customer’s number, customer’s firstname,
lastname,account opening date.

Soln:
  select a.acnumber,a.custid,c.fname,c.ltname,a.aod
  from customer c,account a
  where c.custid=a.custid

Problem # 4:

Write a query to display the number of customer’s from Delhi. 
Give the count an alias name of Cust_Count.

Soln:
  select count(*) Cust_Count
  from customer
  where city in 'Delhi'

Problem # 5:

Write a query to display  the customer number, customer firstname,account number 
for the customer’s whose accounts were created after 15th of any month.

solution : 



Problem # 6:

Write a query to display the  customers firstname, city and account number who are not into business, service or studies.
Problem # 7:

Write a query to display city name and count of branches in that city. Give the count of branches an alias name of Count_Branch.
Problem # 8:

Write a query to display account id, customer’s firstname, customer’s lastname for the customer’s whose account is Active.
Problem # 9:

Write a query to display the customer’s number, customer’s firstname, branch id and loan amount for people who have taken loans.
Problem # 10:

Write a query to display customer number, customer name, account number where the account status is terminated.
	
	
	
	
	
-------------
----DML

SELECT * FROM DEPARTMENTS d ;

INSERT INTO DEPARTMENTS d VALUES (280,'GradHires',206,2700);
INSERT INTO DEPARTMENTS d VALUES (291,'LateralHires');		--error

INSERT INTO DEPARTMENTS d VALUES (291,'LateralHires',NULL,null);	-WORK

INSERT INTO DEPARTMENTS (department_id,DEPARTMENT_NAME) VALUES (292,'LateralHires');


INSERT INTO employees (employee_id, 
                 first_name, last_name, 
                 email, phone_number,
                 hire_date, job_id, salary, 
                 commission_pct, manager_id,
                 department_id)
VALUES		   (1113, 
                 'Louis', 'Popp', 
                 'LPOPPH', '515.124.4567', 
                 CURRENT_DATE, 'AC_ACCOUNT', 6900, 
                 NULL, 205, 110);

                SELECT * FROM EMPLOYEES e ;



UPDATE EMPLOYEES SET SALARY =salary+1000;

SELECT * FROM EMPLOYEES e 

	
	UPDATE   employees
SET      (job_id,salary)  = (SELECT  job_id,salary
                    	       FROM    employees 
                             WHERE   employee_id = 205)
         WHERE    employee_id    =  103;

	
     ---update salary to increase by 20% for all employees 
       -- who works in department_id - 90;
 UPDATE EMPLOYEES SET salary=salary+salary*0.20 WHERE DEPARTMENT_ID =90;       
        

DELETE FROM EMPLOYEES e WHERE DEPARTMENT_ID =19181;

----------WORKSTATION	- Tufail

SELECT FIRST_NAME ,salary FROM EMPLOYEES e WHERE EMPLOYEE_ID =100;


UPDATE EMPLOYEES 
SET SALARY =SALARY +3000
WHERE EMPLOYEE_ID =100

SAVEPOINT s1;

UPDATE EMPLOYEES 
SET SALARY =SALARY +5000
WHERE EMPLOYEE_ID =100

COMMIT;

ROLLBACK TO s1;


---------------DDL

--- Tufail way without naming the constraint
CREATE TABLE products1
(
	productId NUMBER PRIMARY KEY,
	productName varchar(20) NOT NULL UNIQUE,
	quantityOnHand NUMBER DEFAULT 100,
	price NUMBER CHECK ( price > 0)	
)

--- neha way naming the constraint
CREATE TABLE products2
(
	productId NUMBER CONSTRAINT productpkid PRIMARY KEY,
	productName varchar(20) CONSTRAINT productpknamenotnull UNIQUE,
	quantityOnHand NUMBER  DEFAULT 100,
	price NUMBER CONSTRAINT pricecheck  CHECK (price > 0)
)



INSERT INTO products2 values(1820,'Aroma',default,98);


SELECT * FROM products2;













































