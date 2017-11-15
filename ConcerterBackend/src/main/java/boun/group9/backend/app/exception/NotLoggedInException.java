package boun.group9.backend.app.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.UNAUTHORIZED,reason="Not logged in.")
public class NotLoggedInException extends RuntimeException{
	public NotLoggedInException() {
		super();
	}
}
