package boun.group9.webservice.test;

import static org.junit.jupiter.api.Assertions.*;

import java.net.HttpURLConnection;
import java.net.URL;

import boun.group9.data.Users;
class Test {
	private static int sampleConcertId = 41;
	private static int sampleUserID = 51;
	private static String sampleUserEmail = "tuser@gmail.com";
	private static String sampleUserPassword = "654321";
	private static String baseURL = "http://localhost:8081/RESTService-0.5.5/user";
	@org.junit.jupiter.api.Test
	void test() {
		fail("Not yet implemented");
	}
	@org.junit.jupiter.api.Test
	void testLogin() {
		Users user = new Users();
		user.setEmail(sampleUserEmail);
		user.setPassword(sampleUserPassword);
		try {
			URL url = new URL(baseURL+"/login");
			HttpURLConnection connection =(HttpURLConnection) url.openConnection();
			connection.setDoInput(true);
			connection.
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	@org.junit.jupiter.api.Test
	void testNewComment() {
		
	}
	@org.junit.jupiter.api.Test
	void testSignup() {
		
	}
	@org.junit.jupiter.api.Test
	void testGetConcert() {
		
	}
	@org.junit.jupiter.api.Test
	void testSearch() {
		
	}
	
}
