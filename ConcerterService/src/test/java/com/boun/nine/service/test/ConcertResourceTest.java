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

import com.boun.nine.data.Concert;
import com.boun.nine.service.concrete.ConcertResource;
import com.boun.nine.service.interfaces.ConnectedService;
import com.google.gson.Gson;

public class ConcertResourceTest extends JerseyTest{
	private static String query;
	private static ResultSet rs;
	private static String expected;
	@Override
	protected Application configure(){
		return new ResourceConfig(ConcertResource.class);
	}
	@Test
	public void singleConcertTest(){
		String response = target("concert/5").request().get(String.class);
		expected = "{\"id\":5,\"name\":\"RockFest\",\"ownerId\":5,\"artistName\":\"Fatih Guven\",\"locationX\":36.05,\"locationY\":46.02,\"minPrice\":50.0,\"maxPrice\":100.0,\"rate\":0.0}";
		Assert.assertTrue(expected.equals(response));
	}
	@Test
	public void multiConcertTest(){
		Gson gson = new Gson();
		Concert concert = new Concert();
		query = "SELECT * FROM concert;";
		expected = "[";
		try{
			rs = ConnectedService.executeQuery(query);
			while(rs.next()){
				concert.setId(rs.getInt("id"));
				concert.setName(rs.getString("name"));
				concert.setOwnerId(rs.getInt("owner_id"));
				concert.setArtistName(rs.getString("artist_name"));
				concert.setLocationX(rs.getDouble("location_x"));
				concert.setLocationY(rs.getDouble("location_y"));
				concert.setMinPrice(rs.getDouble("min_price"));
				concert.setMaxPrice(rs.getDouble("max_price"));
				concert.setRate(rs.getDouble("rate"));
				expected += gson.toJson(concert)+",";
			}
			expected = expected.substring(0, expected.length()-1);
			expected += "]";
		}catch(SQLException ex){
			ex.printStackTrace();
		}
		String response = target("concert").request().get(String.class);
		Assert.assertTrue(expected.equals(response));
	}
	@Test
	public void createConcertTest(){
		Response response = target("concert").request().post(Entity.json("{\"name\":\"Concerto\",\"ownerId\":6,\"artistName\":\"Ozgur Guven\",\"locationX\":6.05,\"locationY\":4.02,\"minPrice\":50.0,\"maxPrice\":100.0,\"rate\":0.0}"));
		Assert.assertTrue(response.getStatus()==200);
	}
}
