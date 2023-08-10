package com.resttraining;

  import javax.ws.rs.GET;
  import javax.ws.rs.Path;

  @Path("demo")
  public class Demo {


      @Path("sayHello")			//   http://localhost:8080/RESTfulExample/rest/demo/sayHello
      @GET
      public String sayHello() {
          return "Hello all";
      }
  }