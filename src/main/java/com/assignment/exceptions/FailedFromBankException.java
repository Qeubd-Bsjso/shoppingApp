package com.assignment.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


/*
 * Exception to simulate the negative response from bank
 * */
@SuppressWarnings("serial")
@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class FailedFromBankException extends Exception{
	public FailedFromBankException(){
		super("Payment Failed from back");
	}
}
