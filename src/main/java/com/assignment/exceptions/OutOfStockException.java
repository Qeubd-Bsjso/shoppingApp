package com.assignment.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.assignment.shoppingApp.Product;



/*
 * Exception if product quantity is less than order quantity
 * */
@SuppressWarnings("serial")
@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class OutOfStockException extends Exception{
	private static final String DEFAULT_MESSAGE = "Not enough products in stock";
	
	public OutOfStockException() {
		super(DEFAULT_MESSAGE);
	}
	
	public OutOfStockException(Product product) {
		super("Only " + product.getQuantity() + " left in the store.");
	}
	
}
