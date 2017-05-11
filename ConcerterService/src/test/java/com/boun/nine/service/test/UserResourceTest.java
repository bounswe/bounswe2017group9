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
	@Test
	public void loginTest(){
		String response = target("user/\"hilaldnmz@gmail.com\"/\"1a\"").request().get(String.class);
		String expected = "{\"id\":11,\"facebook_id\":0,\"google_id\":0,"
				+ "\"name\":\"hilal\",\"username\":\"hll\","
				+ "\"email\":\"hilaldnmz@gmail.com\",\"password\":\"1a\"}";
		Assert.assertTrue(expected.equals(response));
	}
	@Test
	public void signUpTest(){
		Response response = target("user").request().post(Entity.json("{\"name\":\"hilal\",\"username\":\"hilaldnmz\",\"email\":\"hilaldonmez@gmail.com\","
				+ "\"password\":\"aaaa\"}"));
		Assert.assertTrue(response.getStatus()==200);
	
	}

}
