package com.boun.nine.service.test;

import javax.ws.rs.core.Application;

import org.junit.Assert;
import org.junit.Test;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;

import com.boun.nine.service.concrete.ConcertResource;

public class ConcertResourceTest extends JerseyTest{
	@Override
	protected Application configure(){
		return new ResourceConfig(ConcertResource.class);
	}
	@Test
	public void singleConcertTest(){
		String response = target("concert/5").request().get(String.class);
		String expected = "{\"id\":5,\"name\":\"RockFest\",\"ownerId\":5,\"artistName\":\"Fatih Guven\",\"locationX\":36.05,\"locationY\":46.02,\"minPrice\":50.0,\"maxPrice\":100.0,\"rate\":0.0}";
		Assert.assertTrue(expected.equals(response));
	}
	@Test
	public void multiConcertTest(){
		String response = target("concert").request().get(String.class);
		String expected = "[{\"id\":5,\"name\":\"RockFest\",\"ownerId\":5,\"artistName\":\"Fatih Guven\",\"locationX\":36.05,\"locationY\":46.02,\"minPrice\":50.0,\"maxPrice\":100.0,\"rate\":0.0},{\"id\":6,\"name\":\"RockFest\",\"ownerId\":5,\"artistName\":\"Efehan Atici\",\"locationX\":36.05,\"locationY\":46.02,\"minPrice\":50.0,\"maxPrice\":100.0,\"rate\":0.0}]";
		Assert.assertTrue(expected.equals(response));
	}
}
