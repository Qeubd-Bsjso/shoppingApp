package com.assignment.exceptions;

import org.springframework.http.HttpStatus;

import com.assignment.model.Payment;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

/*
 * Exception if order is already paid for  
 * */
@SuppressWarnings("serial")
public class AlreadyPaidForException extends DescriptiveException{
	
	private final ObjectNode description;
	
	public AlreadyPaidForException() {
		super("order is aleady paid for");
		description = new ObjectMapper().createObjectNode();
		description.put("description", "order is aleady paid for");
	}
	
	public AlreadyPaidForException(Payment payment) {
		super("order is aleady paid for");
		description = payment.getDescription("Order is already paid for");
	}

	@Override
	public HttpStatus getStatus() {
		return HttpStatus.NOT_FOUND;
	}

	@Override
	public ObjectNode getDescription() {
		return description;
	}
	
	
}
