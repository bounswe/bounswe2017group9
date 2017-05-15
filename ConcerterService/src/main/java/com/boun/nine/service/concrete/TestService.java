package com.boun.nine.service.concrete;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 * 
 * @author ffguven
 * @version 1.0
 * @since 15/05/2017
 * This class is triggered if
 * {@code ConcerterService/rest/test} URL is requested with GET.
 * 
 */
@Path("/test")
public class TestService {
	/**
	 * This function is triggered if {@code ConcerterService/rest/test/check}.
	 * The URL stands for checking whether the API works or not.
	 * @return String
	 */
	@GET
	@Path("check")
	@Produces("text/plain")
	public String check(){
		return "API Works!";
	}
}
