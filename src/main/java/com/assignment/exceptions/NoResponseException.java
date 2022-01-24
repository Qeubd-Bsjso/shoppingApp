package com.assignment.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


/*
 * Exception to simulate no response from payment server
 * */
@SuppressWarnings("serial")
@ResponseStatus(code = HttpStatus.GATEWAY_TIMEOUT)
public class NoResponseException extends Exception{
	public NoResponseException(){
		super("No response from server.");
	}
}
