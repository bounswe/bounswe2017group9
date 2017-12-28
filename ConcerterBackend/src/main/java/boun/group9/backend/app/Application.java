package boun.group9.backend.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.google.gson.Gson;


@SpringBootApplication
public class Application {
	//public static final String API_ENDPOINT = "http://concerterservice-env.yzkzigt6vn.eu-central-1.elasticbeanstalk.com";
	public static final String API_ENDPOINT = "http://localhost:8081";
	public static final String SPOTIFY_CLIENT_ID = "bb34cac7478d4bc483f7711e0873b8b4";
	public static final String SPOTIFY_CLIENT_SECRET = "2e63389643f1467cbf44aea7249850e7";
	public static final String SPOTIFY_DEFAULT_HOST = "https://api.spotify.com";
	public static final String SPOTIFY_DEFAULT_AUTHENTICATION_HOST = "https://accounts.spotify.com";
	public static enum STATUS{
		SUCCESS,ERROR;
	}
	public static final Gson gson = new Gson();
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
