package com.resttraining;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/*
http://localhost:8080/RESTfulExample/rest/products		-GET
http://localhost:8080/RESTfulExample/rest/products/1816		-GET
	*/

@Path("products")
public class ProductResource {

	@GET
	@Path("msg/{guestName}") // http://localhost:8080/RESTfulExample/rest/products/msg/Ahmed
	public String getMessage(@PathParam("guestName") String gName) {
		return "Product Rest API is working!!! - Mr/Ms , " + gName;
	}

	@GET
	@Path("{productId}") // http://localhost:8080/RESTfulExample/rest/products/123 - GET
	@Produces(MediaType.APPLICATION_JSON)
	public Product getASingleProduct(@PathParam("productId") Integer productId) {
		// code here to search for the product and return
		Product product = new Product(productId, "HPLaptop", 2900, 78000);
		return product;
	}

	@GET // http://localhost:8080/RESTfulExample/rest/products- GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Product> getAllProducts() { // calling the dao to get the products
		List<Product> products = new ArrayList<Product>();
		products.add(new Product(122, "Bottle", 100, 2000));
		products.add(new Product(100, "HPLaptop", 2900, 78000));
		products.add(new Product(102, "HPLaptop", 2900, 78000));

		return products;
	}

	// Post

	@POST // http://localhost:8080/RESTfulExample/rest/products- POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response saveProduct(Product product) { // calling the dao to get the products

		List<Product> products = new ArrayList<Product>();
		products.add(new Product(122, "Bottle", 100, 2000));
		products.add(new Product(100, "HPLaptop", 2900, 78000));
		products.add(new Product(102, "HPLaptop", 2900, 78000));
		// save this object in DB

		if (product.getQuantityOnHand() > 0) {
			products.add(product);
			return Response.status(201).entity(products).build();
		} else {
			return Response.status(200).entity(products).build();
		}
	}

	boolean updated = false;

	@PUT // http://localhost:8080/RESTfulExample/rest/products - PUT
	@Path("{productId}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateProduct(@PathParam("productId") Integer pId, Product product) {
		List<Product> allproducts = new ArrayList<Product>();
		allproducts.add(new Product(122, "Bottle", 100, 2000));
		allproducts.add(new Product(100, "HPLaptop", 2900, 78000));
		allproducts.add(new Product(102, "Newspaper", 2900, 78000));

		for (Product p : allproducts) {
			if (p.getProductId() == pId) {
				if (p.getProductName() != null) {
					p.setProductName(product.getProductName());
				}
				if (p.getQuantityOnHand() != 0) {
					p.setQuantityOnHand(product.getQuantityOnHand());
				}
				if (p.getPrice() != 0) {
					p.setPrice(product.getPrice());
				}
			} else {
				updated = true;
			}
		}
		if (updated)
			return Response.status(200).entity(allproducts).build();
		else
			return Response.status(204).entity(allproducts).build();
	}

	// delete

	int counter = 0;
	boolean deleted = false;

	@DELETE // http://localhost:8080/RESTfulExample/rest/products/1911 - DELETE
	@Path("{productId}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response deleteProduct(@PathParam("productId") Integer pId) {
		List<Product> allproducts = new ArrayList<Product>();
		allproducts.add(new Product(122, "Bottle", 100, 2000));
		allproducts.add(new Product(100, "HPLaptop", 2900, 78000));
		allproducts.add(new Product(102, "Newspaper", 2900, 78000));

		for (Product p : allproducts) {
			if (p.getProductId() == pId) {
				allproducts.remove(counter);
				deleted = true;
				System.out.println("### the value of deleted :"+deleted);
				break;
			}
			counter++;
		}
		if (deleted)
			return Response.status(200).entity(allproducts).build();
		else
			return Response.status(202).entity(allproducts).build();
	}
}
