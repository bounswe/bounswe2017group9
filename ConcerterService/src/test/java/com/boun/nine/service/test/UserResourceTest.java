package com.boun.nine.service.test;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Response;

import org.junit.Assert;
import org.junit.Test;

import com.boun.nine.service.concrete.UserResource;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;

public class UserResourceTest extends JerseyTest{
	@Override
	protected Application configure(){
		return new ResourceConfig(UserResource.class);
	}
	
	/**
	 * Create a user instance whose email is hilaldnmz@gmail.com and password is 1a in database with sample.sql
	 * Check whether this instance can be created with login function
	 * Check whether the expected response and the response obtained are the same
	 * @return the successful result if they are the same 
	 * @return error if they are not equal  
	 */
	
	@Test
	public void loginTest(){
		String response = target("user/\"hilaldnmz@gmail.com\"/\"1a\"").request().get(String.class);
		String expected = "{\"id\":11,\"facebook_id\":0,\"google_id\":0,"
				+ "\"name\":\"hilal\",\"username\":\"hll\","
				+ "\"email\":\"hilaldnmz@gmail.com\",\"password\":\"1a\"}";
		Assert.assertTrue(expected.equals(response));
	}
	
	/**
	 * Create a user instance whose name is hilal, username is hilaldnmz,email is hilaldonmez@gmail.com, and password is aaaa
	 * @return the successful result if HTTP 200 is obtained as a respponse
	 * @return error otherwise
	 */
	
	@Test
	public void signUpTest(){
		Response response = target("user").request().post(Entity.json("{\"name\":\"hilal\",\"username\":\"hilaldnmz\",\"email\":\"hilaldonmez@gmail.com\","
				+ "\"password\":\"aaaa\"}"));
		Assert.assertTrue(response.getStatus()==200);
	
	}

}
