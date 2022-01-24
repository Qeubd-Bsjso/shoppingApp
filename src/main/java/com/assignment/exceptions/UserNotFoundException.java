package com.assignment.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


/*
 * Exception if user not found
 * */
@SuppressWarnings("serial")
@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class UserNotFoundException extends Exception{

	public UserNotFoundException() {
		super("User not found.");
	}
	
	public UserNotFoundException(int id) {
		super("User with id " + id + " not found.");
	}
}
