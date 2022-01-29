package com.assignment.exceptions;



/*
 * Exception if user not found
 * */
@SuppressWarnings("serial")
public class UserNotFoundException extends RuntimeException{

	public UserNotFoundException() {
		super("user not found");
	}
	
	public UserNotFoundException(int id) {
		super("user with id " + id + " not found");
	}

}
