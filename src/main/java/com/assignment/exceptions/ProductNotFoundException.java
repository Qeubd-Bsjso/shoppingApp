package com.assignment.exceptions;




/*
 * Exception if product not found 
 * */
@SuppressWarnings("serial")
public class ProductNotFoundException extends RuntimeException{

	public ProductNotFoundException() {
		super("product not found");
	}
	
	public ProductNotFoundException(int id) {
		super("product with id " + id + " not found");
	}
}
