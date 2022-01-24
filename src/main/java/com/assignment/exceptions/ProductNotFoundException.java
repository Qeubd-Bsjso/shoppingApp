package com.assignment.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;



/*
 * Exception if product not found 
 * */
@SuppressWarnings("serial")
@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class ProductNotFoundException extends Exception{

	public ProductNotFoundException() {
		super("Product not found.");
	}
	
	public ProductNotFoundException(int id) {
		super("Product with id " + id + " not found.");
	}
}
