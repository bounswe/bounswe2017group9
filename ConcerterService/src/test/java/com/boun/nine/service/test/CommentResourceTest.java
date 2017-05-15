package com.boun.nine.service.test;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Response;

import org.junit.Assert;
import org.junit.Test;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;

import com.boun.nine.service.concrete.CommentResource;

public class CommentResourceTest extends JerseyTest{
	@Override
	protected Application configure(){
		return new ResourceConfig(CommentResource.class);
	}
	@Test
	public void multiCommentTest(){
		String response = target("comment").request().get(String.class);
		String expected = "[{\"id\": 1,\"concertId\": 0,\"owner_id\": 0,\"up_vote\": 24,\"down_vote\": 3,\"content\": \"I liked RockFest\"},{\"id\": 2,\"concertId\": 0,\"owner_id\": 12,\"up_vote\":, \"down_vote\": 5,\"content\": \"I really liked RockFest\"},{\"id\": 4,\"concertId\": 0,\"owner_id\": 10,\"up_vote\": 118,\"down_vote\": 4,\"content\": \"Rockfest was a great performance\"}]";
		Assert.assertTrue(expected.equals(response));
	}
	@Test
	public void createCommentTest(){
		Response response = target("comment").request().post(Entity.json("{\"id\": 1,\"concertId\": 0,\"owner_id\": 0,\"up_vote\": 24,\"down_vote\": 3,\"content\": \"I liked RockFest\"}"));
		Assert.assertTrue(response.getStatus()==200);
	}
}
