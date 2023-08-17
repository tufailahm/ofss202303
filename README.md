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



INSERT INTO products2 values(1,'Mouse',default,98)


SELECT * FROM products2;
SELECT * FROM orders;

CREATE TABLE orders
(
		orderId NUMBER CONSTRAINT pkoid PRIMARY KEY,
		deliveryLocation varchar(20),
		productId NUMBER
)


FK - orders (productId)      - products2 (productId)

---creating fk after creation of a table
ALTER TABLE orders ADD CONSTRAINT fkpkid FOREIGN KEY (productId) REFERENCES products2(productId)
ON DELETE CASCADE;

INSERT INTO orders values(191782,'Pune',1);
INSERT INTO orders values(191783,'Mumbai',1818);		--


DELETE FROM products2 WHERE PRODUCTID = 1;






----------CONSTRAINTS 
--Table level
--column level




CREATE TABLE products3
(
	productId NUMBER ,
	productName varchar(20) ,
	quantityOnHand NUMBER  DEFAULT 100,
	price NUMBER CONSTRAINT pricecheck1  CHECK (price > 0),		--COLUMN LEVEL cons
	CONSTRAINT productpkid1 PRIMARY KEY(productId),				-- TABLE LEVEL cons
	CONSTRAINT productpknamenotnull1 UNIQUE(productName)
	
)


INSERT INTO products3 values(1,'Mouse',default,98)



CREATE TABLE products4
(
	productId NUMBER ,
	ssnCode NUMBER,
	productName varchar(20) ,
	quantityOnHand NUMBER  DEFAULT 100,
	price NUMBER CONSTRAINT pricecheck12  CHECK (price > 0),		--COLUMN LEVEL cons
	CONSTRAINT productpkid12 PRIMARY KEY(productId,ssnCode),	-- TABLE LEVEL cons
	CONSTRAINT productpknamenotnull12 UNIQUE(productName)
)





-------creating table using subquery

CREATE TABLE 	dept80
  AS     SELECT  employee_id, last_name, 
            salary*12 ANNSAL, 
            hire_date    FROM    employees    WHERE   department_id = 80;


         ADD a COLUMN email
         DROP hire_date COLUMN
         CHANGE the LENGTH OF last_name TO 45 
    


--ADD a COLUMN email
ALTER TABLE dept80 ADD email varchar(30);
--DROP hire_date COLUMN
ALTER TABLE dept80 DROP COLUMN hire_date;
  --CHANGE the LENGTH OF last_name TO 45
ALTER TABLE dept80 MODIFY last_name varchar(45);

-------------------


SELECT *
FROM   dictionary
WHERE  table_name = 'USER_OBJECTS';


SELECT object_name, object_type, created, status
FROM   user_objects
ORDER BY object_type;

SELECT table_name
FROM   user_tables;

SELECT column_name, data_type, data_length,
       data_precision, data_scale, nullable
FROM   user_tab_columns
WHERE  table_name = 'EMPLOYEES'; 



SELECT column_name, data_type, data_length,
       data_precision, data_scale, nullable
FROM   user_tab_columns
WHERE  table_name = 'PRODUCTS2'; 

SELECT constraint_name, constraint_type,
       search_condition, r_constraint_name, 
       delete_rule, status
FROM   user_constraints
WHERE  table_name = 'PRODUCTS2'; 


SELECT constraint_name, constraint_type,
       search_condition, r_constraint_name, 
       delete_rule, status
FROM   user_constraints
WHERE  table_name = 'PRODUCTS4'; 


-----------------------Views
--Use CASE : 
CREATE OR REPLACE VIEW EMPVIEW
as
SELECT employee_id, first_name,salary,MANAGER_ID ,DEPARTMENT_ID ,email
FROM EMPLOYEES e 
WHERE DEPARTMENT_ID =90
AND MANAGER_ID =100
WITH CHECK OPTION;

SELECT * FROM empview;		-- DML via VIEW also

UPDATE empview SET manager_id = 102 WHERE employee_id = 102;		--will NOT WORK
																	-- bcoz of check option

SELECT * FROM EMPLOYEES e ;


CREATE OR REPLACE VIEW EMPEXECUTIVES
as
SELECT employee_id, first_name,salary,MANAGER_ID ,DEPARTMENT_ID 
FROM EMPLOYEES e 
WHERE SALARY >10000
WITH READ ONLY



SELECT * FROM EMPEXECUTIVES;

------Hands On
-- CREATE a view to display employee_id,first_name,department_name 
-- Use join 
-- we should should be able to modify the data -- use read only option



CREATE OR REPLACE VIEW empView 
AS 
SELECT EMPLOYEE_ID , FIRST_NAME , d.DEPARTMENT_NAME  FROM EMPLOYEES e NATURAL JOIN DEPARTMENTS d 
WITH READ ONLY ;


SELECT * FROM empView;



CREATE OR REPLACE VIEW EMPVIEWPROG
as
SELECT employee_id, first_name,salary,MANAGER_ID ,DEPARTMENT_ID ,email,JOB_ID 
FROM EMPLOYEES e 
WHERE JOB_ID ='IT_PROG'
WITH CHECK OPTION;


SELECT * FROM EMPVIEWPROG;




--Synomyn
--sequence

CREATE SYNONYM offices 
   FOR locations;

SELECT * FROM offices

--sequence
---auto generated numbers


--use CASE : The product id should be GENERATED FROM a SEQUENCE

CREATE SEQUENCE products_seq
 START WITH     1000
 INCREMENT BY   10
 NOCACHE
 NOCYCLE;

SELECT * FROM products2;

insert INTO products2 values(products_seq.nextval,'Sony',1,1);

-----------------
--PL_SQL (Anonymous pl-sql)
DECLARE
    v_fname varchar(20);
BEGIN
	SELECT first_name INTO v_fname FROM EMPLOYEES e WHERE EMPLOYEE_ID =105;
	DBMS_OUTPUT.PUT_LINE('The first name is ' || v_fname);
END;

-------------------

DECLARE
    num1 NUMBER;
    num2 NUMBER;
    res NUMBER;
BEGIN
	num1 := 10;
	num2 := 20;
    res := num1 +num2;
	DBMS_OUTPUT.PUT_LINE('The sum of two numbers are : ' || res);
END;

--------------------
-- use case : create a plsql program to update 105 salary to be same as 100's salary

DECLARE
	nsal NUMBER;
BEGIN
	SELECT salary INTO nsal FROM EMPLOYEES e WHERE EMPLOYEE_ID =100;		--100
	UPDATE EMPLOYEES SET SALARY = nsal WHERE EMPLOYEE_ID =105;
END;


SELECT * FROM EMPLOYEES e ;


-----Create a plsql code to update all employees salary to 20% increment who works in same department
-----as Neena

DECLARE
	dept NUMBER;
BEGIN
	SELECT DEPARTMENT_ID INTO dept FROM EMPLOYEES e WHERE LOWER(FIRST_NAME) = 'neena';
	UPDATE EMPLOYEES SET SALARY = SALARY + SALARY *20/100 WHERE DEPARTMENT_ID = dept;
END;








-----------------

DECLARE
    v_fname varchar(20);
   	nsalary NUMBER;
BEGIN
	SELECT first_name,salary INTO v_fname,nsalary FROM EMPLOYEES e WHERE EMPLOYEE_ID =100;
	
	DBMS_OUTPUT.PUT_LINE('The first name is ' || v_fname);
END;





----------------------
--SQL
SELECT  'The first name is ' || first_name  FROM EMPLOYEES e WHERE EMPLOYEE_ID =100;









DECLARE
    v_fname varchar(20);
   	nsalary NUMBER;
BEGIN
	SELECT first_name,salary INTO v_fname,nsalary FROM EMPLOYEES e WHERE EMPLOYEE_ID =100;
	DBMS_OUTPUT.PUT_LINE('The first name is ' || v_fname);
	DBMS_OUTPUT.PUT_LINE('The salary  is ' || nsalary);
END;

----------------------

DECLARE
    v_fname employees.first_name%type;
   	nsalary employees.salary%type;
BEGIN
	SELECT first_name,salary INTO v_fname,nsalary FROM EMPLOYEES e WHERE EMPLOYEE_ID =100;
	DBMS_OUTPUT.PUT_LINE('The first name is ' || v_fname);
	DBMS_OUTPUT.PUT_LINE('The salary  is ' || nsalary);
END;

----------Hands on
DECLARE
  n_sales NUMBER := 100000;
  n_commission NUMBER( 10, 2 ) := 0;
BEGIN
  IF n_sales > 200000 THEN
    n_commission := n_sales * 0.1;
  ELSE
    n_commission := n_sales * 0.05;
  END IF;
 	dbms_output.put_line(n_commission);
END;


-- CREATE a  plsql program to update employee id 100 salary to 20% 
-- if he/she is from same department
---AS neena ELSE give him AS raise OF 10%

--use %type


DECLARE
	dept EMPLOYEES.DEPARTMENT_ID%TYPE;
BEGIN
	SELECT DEPARTMENT_ID INTO dept FROM EMPLOYEES e WHERE LOWER(FIRST_NAME) = 'neena';
	UPDATE EMPLOYEES SET SALARY = SALARY + SALARY *20/100 WHERE DEPARTMENT_ID = dept;
	UPDATE EMPLOYEES SET SALARY = SALARY + SALARY *10/100 WHERE DEPARTMENT_ID NOT IN dept;
END;


------------------Exception handling


DECLARE
  v_lname VARCHAR2(15);
BEGIN
  SELECT last_name INTO v_lname 
  FROM employees
  WHERE first_name='Neena'; 
  DBMS_OUTPUT.PUT_LINE ('Neena''s last name is :' ||v_lname);
  EXCEPTION
  	WHEN TOO_MANY_ROWS THEN DBMS_OUTPUT.PUT_LINE('Too many rows fetched');
  	WHEN NO_DATA_FOUND THEN DBMS_OUTPUT.PUT_LINE('name does not exists');
 	 WHEN OTHERS THEN DBMS_OUTPUT.PUT_LINE('Some other errors');
END;

-------custom exception
DECLARE 
  v_deptno NUMBER := 500;
  v_name VARCHAR2(20) := 'Testing';
  e_invalid_department EXCEPTION;
BEGIN
  UPDATE departments
  SET department_name = v_name
  WHERE department_id = v_deptno;
  IF SQL%NOTFOUND THEN
    RAISE e_invalid_department;
  END IF;
  COMMIT;
EXCEPTION
WHEN e_invalid_department THEN
  DBMS_OUTPUT.PUT_LINE('No such department id.');
END;






























DECLARE
 e_insert_excep EXCEPTION;
 PRAGMA EXCEPTION_INIT(e_insert_excep, -01400);
BEGIN
 INSERT INTO departments  (department_id, department_name) VALUES (7801, NULL);
   DBMS_OUTPUT.PUT_LINE('INSERT OPERATION SUCCEDED');

EXCEPTION
 WHEN e_insert_excep THEN
   DBMS_OUTPUT.PUT_LINE('INSERT OPERATION FAILED');
   END;




-----------------------Procedure
CREATE OR REPLACE PROCEDURE  searchEmployee
(
	empId number
)
IS 
begin
DECLARE
    v_fname varchar(20);
   	nsalary NUMBER;
BEGIN
	SELECT first_name,salary INTO v_fname,nsalary FROM EMPLOYEES e WHERE EMPLOYEE_ID = empId;
	DBMS_OUTPUT.PUT_LINE('The first name is ' || v_fname);
END;
end;

----Calling procedure
begin
searchEmployee(100);
END;

begin
searchEmployee(105);
END;


----------------Create procedure to take emp id and new salary and update the salary of that 
--epm id to increment salary 

CREATE OR REPLACE PROCEDURE incrementSalary
(
	empId NUMBER ,
	sal NUMBER 	
)
IS 
BEGIN 
BEGIN 
	UPDATE EMPLOYEES SET SALARY = SALARY + sal WHERE EMPLOYEE_ID = empId;
END;
END;



begin
IncrementSalary(100,2000);
END;



SELECT * FROM EMPLOYEES e ;

----------------
------------------OUT parameter

CREATE OR REPLACE PROCEDURE query_emp
 (p_id     IN  employees.employee_id%TYPE,
  p_name   OUT employees.last_name%TYPE,
  p_salary OUT employees.salary%TYPE) IS
BEGIN
  SELECT  last_name, salary INTO p_name, p_salary
  FROM    employees
  WHERE   employee_id = p_id;
END query_emp;

------------executing
CREATE OR REPLACE PROCEDURE display
(
	p_id   IN  employees.employee_id%TYPE
)AS
  v_emp_name employees.last_name%TYPE;
  v_emp_sal  employees.salary%TYPE;
BEGIN
  query_emp(p_id, v_emp_name, v_emp_sal);
  DBMS_OUTPUT.PUT_LINE(v_emp_name||' earns '|| to_char(v_emp_sal, '$999,999.00'));
END display;

--------------------------
BEGIN
	display(100);
END;




CREATE OR REPLACE PROCEDURE query_emp2
 (
 p_id IN employees.employee_id%TYPE,
 sal OUT employees.SALARY%TYPE
  )
  IS
BEGIN
  SELECT  salary INTO  sal
  FROM    employees
  WHERE   employee_id = p_id;
END query_emp2;


------executing
DECLARE
salp NUMBER;
BEGIN
	query_emp2(200,salp);
	dbms_output.put_line('The salary recvd is ' || salp);
END;

------------------Function in oracle
create or replace function AddNumbers
(
     num1 number,
     num2 number
)
RETURN number
AS
BEGIN
      return num1+num2;
END;

--
select AddNumbers(29,13) from dual;

--------

BEGIN
dbms_output.put_line('The result is :' || addnumbers(12,222) );
end;

---------------------------------------------------------------------------

BEGIN
    declare
      employeeId  number := 176;
      sal employees.salary%TYPE;
      comm employees.commission_pct%TYPE;
      begin
       select salary,commission_pct into sal, comm from employees where employee_Id = employeeId;
        dbms_output.put_line('The total salary is :' || addnumbers(sal,comm) );
      end;
end;



-------------------------
-----Triggers


















