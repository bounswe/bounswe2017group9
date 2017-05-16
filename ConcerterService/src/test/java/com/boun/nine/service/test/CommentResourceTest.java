package com.boun.nine.service.test;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Response;

import org.junit.Assert;
import org.junit.Test;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;

import com.boun.nine.data.Comment;
import com.boun.nine.service.concrete.CommentResource;
import com.boun.nine.service.interfaces.ConnectedService;
import com.google.gson.Gson;

public class CommentResourceTest extends JerseyTest{
	private static String query;
	private static ResultSet rs;
	private static String expected;
	@Override
	protected Application configure(){
		return new ResourceConfig(CommentResource.class);
	}
	@Test
	public void multiCommentTest(){
		Comment comment = new Comment();
		Gson gson = new Gson();
		query = "SELECT * FROM comment;";
		try{
			rs = ConnectedService.executeQuery(query);
			expected = "[";
			while(rs.next()){
				comment.setId(rs.getInt("id"));
				comment.setContent(rs.getString("content"));
				comment.setOwnerId(rs.getInt("owner_id"));
				comment.set_up_vote(rs.getInt("up_vote"));
				comment.set_down_vote(rs.getInt("down_vote"));
				expected += gson.toJson(comment)+",";
			}
			expected = expected.substring(0, expected.length()-1);
			expected += "]";
		}catch(SQLException ex){
			ex.printStackTrace();
		}
		String response = target("comment").request().get(String.class);
		Assert.assertTrue(expected.equals(response));
	}
	@Test
	public void createCommentTest(){
		Response response = target("comment").request().post(Entity.json("{\"id\": 1,\"concertId\": 0,\"owner_id\": 0,\"up_vote\": 24,\"down_vote\": 3,\"content\": \"I liked RockFest\"}"));
		Assert.assertTrue(response.getStatus()==200);
	}
}
