package com.assignment.exceptions;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

/*
 * Exception is coupon is invalid or already used by user
 * */
@SuppressWarnings("serial")
public class InvalidCouponException extends DescriptiveException{

	private final ObjectNode description;
	
	public InvalidCouponException() {
		super("coupon not found");
		description = new ObjectMapper().createObjectNode();
		description.put("description", "coupon not found");
	}
	
	public InvalidCouponException(String name) {
		super("coupon with name " + name + " not found");
		description = new ObjectMapper().createObjectNode();
		description.put("description", "coupon with name " + name + " not found");
	}
	
	public InvalidCouponException(int userId, String name) {
		super("user with id " + userId + " has already used the coupon " + name );
		description = new ObjectMapper().createObjectNode();
		description.put("description", "user with id " + userId + " has already used the coupon " + name );
	}

	@Override
	public ObjectNode getDescription() {
		return description;
	}

	@Override
	public HttpStatus getStatus() {
		return HttpStatus.NOT_FOUND;
	}
	
}
