package api9;
import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import com.boun.nine.service.concrete.ConcertResource;

@ApplicationPath("/rest")
public class NineService extends Application{
	private Set<Object> singletons = new HashSet<Object>();
	private Set<Class<?>> empty = new HashSet<Class<?>>();
	public NineService(){
		singletons.add(new TestService());
		singletons.add(new ConcertResource());
	}
	@Override
	public Set<Class<?>> getClasses(){
		return empty;
	}
	@Override
	public Set<Object> getSingletons(){
		return singletons;
	}
}
