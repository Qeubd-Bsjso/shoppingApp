package com.assignment.exceptions;

import org.springframework.http.HttpStatus;

import com.assignment.model.Payment;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

/*
 * Exception if amount of order is different from payment amount
 * */
@SuppressWarnings("serial")
public class InvalidAmountException extends DescriptiveException{
	
	
	private final ObjectNode description;
	
	public InvalidAmountException(){
		super("invalid amount for the order");
		description = new ObjectMapper().createObjectNode();
		description.put("description", "invalid amount for the order");
		
	}

	public InvalidAmountException(Payment payment) {
		super("invalid amount for the order, amount needed = " + payment.getOrder().getAmount());
		description = payment.getDescription("invalid amount for the order, amount needed = " + payment.getOrder().getAmount());
	}

	@Override
	public ObjectNode getDescription() {
		return description;
	}

	@Override
	public HttpStatus getStatus() {
		return HttpStatus.BAD_REQUEST;
	}
}
