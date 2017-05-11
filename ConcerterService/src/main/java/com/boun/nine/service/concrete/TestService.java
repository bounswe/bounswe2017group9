package com.boun.nine.service.concrete;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path("/test")
public class TestService {
	@GET
	@Path("check")
	@Produces("text/plain")
	public String check(){
		return "API Works!";
	}
}
