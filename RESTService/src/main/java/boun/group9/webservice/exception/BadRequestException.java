package boun.group9.webservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.BAD_REQUEST,reason="Request params are wrong.")
public class BadRequestException extends RuntimeException{
	public BadRequestException() {
		super();
	}
}
