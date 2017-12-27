package boun.group9.webservice.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

import com.google.gson.Gson;

import boun.group9.webservice.helper.Database;

@SpringBootApplication
public class Application extends SpringBootServletInitializer{
	public static String username="hilaldonmez";
	public static String password="123456";

	public static Gson gson = new Gson();
	public static final int MODE_GET = 0;
	public static final int MODE_UPDATE = 1;

	public static void main(String[] args) {
		SpringApplication.run(Application.class,args);
	}
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(Application.class);
	}
}
