package boun.group9.webservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.NO_CONTENT,reason="Email or password is wrong.")
public class UserNotFoundException extends RuntimeException{
	public UserNotFoundException(){
		super();
	}
}
