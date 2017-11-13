package boun.group9.backend.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.google.gson.Gson;


@SpringBootApplication
public class Application {
	public static final String API_ENDPOINT = "http://localhost:8081/RESTService-0.1.1-SNAPSHOT";
	public static enum STATUS{
		SUCCESS,ERROR;
	}
	public static final Gson gson = new Gson();
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
