package api9;
import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import com.boun.nine.service.concrete.CommentResource;
import com.boun.nine.service.concrete.ConcertResource;
import com.boun.nine.service.concrete.UserResource;

@ApplicationPath("/rest")
public class NineService extends Application{
	private Set<Object> singletons = new HashSet<Object>();
	private Set<Class<?>> empty = new HashSet<Class<?>>();
	public NineService(){
		singletons.add(new TestService());
		singletons.add(new ConcertResource());
		singletons.add(new UserResource());
		singletons.add(new CommentResource());
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
