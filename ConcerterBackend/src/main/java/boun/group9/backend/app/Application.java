package boun.group9.backend.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class Application {
	public static final String APIEndpoint = "localhost:8081/RESTService-0.0.3-SNAPSHOT";
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
