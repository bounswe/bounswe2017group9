package api9;

import org.glassfish.jersey.server.ResourceConfig;

/**
 * Creates a dynamic web application with default JAX-RS template.
 * @author ffguven
 * @version 1.0
 * @since 15-05-2017
 * 
 */
public class ConcerterServiceApplication extends ResourceConfig{
	public ConcerterServiceApplication(){
		packages("com.boun.nine.service.concrete");
	}
}
