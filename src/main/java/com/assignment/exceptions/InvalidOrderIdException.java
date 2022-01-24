package com.assignment.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


/*
 * Exception is order is made by other user
 * */
@SuppressWarnings("serial")
@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class InvalidOrderIdException extends Exception{
	public InvalidOrderIdException(){
		super("Invalid UserId");
	}
}
