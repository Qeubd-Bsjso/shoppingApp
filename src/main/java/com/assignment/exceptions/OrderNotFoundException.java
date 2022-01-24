package com.assignment.exceptions;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;


/*
 * Exception if order not found
 * */
@SuppressWarnings("serial")
@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class OrderNotFoundException extends Exception{
	public OrderNotFoundException() {
		super("Order not found.");
	}
	
	public OrderNotFoundException(long id) {
		super("Order with id " + id + " not found.");
	}
}
