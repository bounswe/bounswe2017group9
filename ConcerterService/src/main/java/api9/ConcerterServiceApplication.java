package api9;

import org.glassfish.jersey.server.ResourceConfig;

public class ConcerterServiceApplication extends ResourceConfig{
	public ConcerterServiceApplication(){
		packages("com.boun.nine.service.concrete");
	}
}
