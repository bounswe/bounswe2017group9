package boun.group9.webservice.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.google.gson.Gson;

import boun.group9.webservice.helper.Database;

@SpringBootApplication
public class Application {
	public static final String username = "<username>";
	public static final String password = "<password>";
	public static Gson gson = new Gson();
	public static final int MODE_GET = 0;
	public static final int MODE_UPDATE = 1;
	public static void main(String[] args) {
		SpringApplication.run(Application.class,args);
	}
}
