package com.assignment.exceptions;



/*
 * Exception is coupon is invalid or already used by user
 * */
@SuppressWarnings("serial")
public class InvalidCouponException extends RuntimeException{

	public InvalidCouponException() {
		super("coupon not found");
	}
	
	public InvalidCouponException(String name) {
		super("coupon with name " + name + " not found");
	}
	
	public InvalidCouponException(int userId, String name) {
		super("user with id " + userId + " has already used the coupon " + name );
	}
	
}
