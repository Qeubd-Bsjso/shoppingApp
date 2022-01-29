package com.assignment.exceptions;


/*
 * Exception if order not found
 * */
@SuppressWarnings("serial")
public class OrderNotFoundException extends RuntimeException{
	
	public OrderNotFoundException() {
		super("order not found");
	}
	
	public OrderNotFoundException(long id) {
		super("order with id " + id + " not found");
	}
	
}
