package com.assignment.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;



/*
 * Exception is coupon is invalid or already used by user
 * */
@SuppressWarnings("serial")
@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class InvalidCouponException extends Exception{

	public InvalidCouponException() {
		super("Coupon not found.");
	}
	
	public InvalidCouponException(String name) {
		super("User with name " + name + " not found.");
	}
}
