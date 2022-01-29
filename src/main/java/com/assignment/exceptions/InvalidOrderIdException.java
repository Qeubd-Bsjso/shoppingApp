package com.assignment.exceptions;

import org.springframework.http.HttpStatus;

import com.assignment.model.Payment;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

/*
 * Exception is order is made by other user
 * */
@SuppressWarnings("serial")
public class InvalidOrderIdException extends DescriptiveException{
	
	private final ObjectNode description; 
	
	public InvalidOrderIdException(){
		super("invalid order id");
		description = new ObjectMapper().createObjectNode();
		description.put("description", "invalid order id");
	}
	
	public InvalidOrderIdException(int userId, long orderId) {
		super("order with id " + orderId + " is not made by user " + userId);
		description = new ObjectMapper().createObjectNode();
		description.put("description", "order with id " + orderId + " is not made by user " + userId);
	}

	public InvalidOrderIdException(Payment payment) {
		super("order with id " + payment.getOrder().getId() + " is not made by user " + payment.getUser().getId());
		this.description = payment.getDescription("order with id " + payment.getOrder().getId() + " is not made by user " + payment.getUser().getId());
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
