package com.assignment.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;



/*
 * Exception if amount of order is different from payment amount
 * */
@SuppressWarnings("serial")
@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class InvalidAmountException extends Exception{
	public InvalidAmountException(){
		super("Invalid amount for the order");
	}
}
