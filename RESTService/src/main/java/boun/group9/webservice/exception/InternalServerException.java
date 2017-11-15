package boun.group9.webservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.INTERNAL_SERVER_ERROR,reason="Server cannot process this request.")
public class InternalServerException extends RuntimeException{
	public InternalServerException() {
		super();
	}
}
