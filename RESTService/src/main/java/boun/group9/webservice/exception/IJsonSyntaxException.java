package boun.group9.webservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.BAD_REQUEST,reason="Invalid JSON syntax.")
public class IJsonSyntaxException extends RuntimeException{
	public IJsonSyntaxException() {
		super();
	}
}
