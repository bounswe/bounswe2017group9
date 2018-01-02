package boun.group9.webservice.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

import com.google.gson.Gson;

import boun.group9.webservice.helper.Database;
/**
 * Main class to initialize and configure application before start
 * @author ffguven
 * 
 */
@SpringBootApplication
public class Application extends SpringBootServletInitializer{
	public static String username="root"; // username for mysql root user
	public static String password="4519root"; // password for mysql root user

	public static Gson gson = new Gson(); // object to parse from json or parse to json
	public static final int MODE_GET = 0; // whether Database class get some data or not 
	public static final int MODE_UPDATE = 1; // whether Database class update data on db or not

	public static void main(String[] args) {
		SpringApplication.run(Application.class,args); // initialize Spring Boot application
	}
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(Application.class); // set configuration class for Spring boot application
	}
}
