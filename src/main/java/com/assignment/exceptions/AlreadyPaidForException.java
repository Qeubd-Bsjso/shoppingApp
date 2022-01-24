package com.assignment.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;



/*
 * Exception if order is already paid for  
 * */
@SuppressWarnings("serial")
@ResponseStatus(code = HttpStatus.METHOD_NOT_ALLOWED)
public class AlreadyPaidForException extends Exception{
	
	public AlreadyPaidForException() {
		super("Order is aleady paid for.");
	}
}
