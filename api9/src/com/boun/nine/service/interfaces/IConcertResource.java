package com.boun.nine.service.interfaces;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
@Path("concert")
public interface IConcertResource {
	@GET
	@Path("{id}")
	@Produces("application/json")
	public String getConcert(@PathParam("id") int id);
	@POST
	@Consumes("application/json")
	@Produces("text/plain")
	public String createConcert(String body);
	@PUT
	@Consumes("application/json")
	@Path("{id}")
	public void updateConcert(int id);
	@DELETE
	@Path("{id}")
	public void deleteConcert(int id);
	@GET
	@Path("{concertName}")
	@Produces("application/json")
	public String searchConcert(String concertName);
	@GET
	public String getAllConcerts();
}
