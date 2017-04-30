package com.boun.nine.service.interfaces;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

@Path("user")
public interface IUserResource {
	@GET
	@Path("{email}/{password}")
	@Produces("application/json")
	public String login(@PathParam("email") String email,@PathParam("password") String password);
	@POST
	@Consumes("application/json")
	@Produces("text/plain")
	public String signUp(String body);
	
}
