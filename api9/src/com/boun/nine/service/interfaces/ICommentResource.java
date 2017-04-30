package com.boun.nine.service.interfaces;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
@Path("comment")
public interface ICommentResource {
	
	@GET
	@Path("{concert_id}")
	@Produces("application/json")
	public String getCommentsForConcert(@PathParam("concert_id") int id);
}
