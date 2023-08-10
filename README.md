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



















