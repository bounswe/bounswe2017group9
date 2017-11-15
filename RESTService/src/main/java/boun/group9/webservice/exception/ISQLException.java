package boun.group9.webservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.INTERNAL_SERVER_ERROR,reason="SQL Exception occured.")
public class ISQLException extends RuntimeException{
	public ISQLException() {
		super();
	}
}
