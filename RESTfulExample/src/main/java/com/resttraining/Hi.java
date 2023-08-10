package com.resttraining;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("welcome")
public class Hi {

	@GET
	@Path("hi")
	public String sayHello() {
		return "Welcome in Rest API";
	}
	
}
