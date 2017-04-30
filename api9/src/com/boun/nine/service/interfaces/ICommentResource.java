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
	@POST
	@Consumes("application/json")
	@Produces("text/plain")
	public String writeComment(String body);
	@GET
	@Produces("application/json")
	public String getAllComments();
}
