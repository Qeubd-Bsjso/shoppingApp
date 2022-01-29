package com.assignment.exceptions;

import com.assignment.model.Payment;
import com.fasterxml.jackson.databind.node.ObjectNode;

/*
 * Exception is order is made by other user
 * */
@SuppressWarnings("serial")
public class InvalidOrderIdException extends RuntimeException{
	
	private final ObjectNode paymentDescription; 
	
	public InvalidOrderIdException(){
		super("invalid order id");
		this.paymentDescription = null;
	}
	
	public InvalidOrderIdException(int userId, long orderId) {
		super("order with id " + orderId + " is not made by user " + userId);
		this.paymentDescription = null;
	}

	public InvalidOrderIdException(Payment payment) {
		super("order with id " + payment.getOrder().getId() + " is not made by user " + payment.getUser().getId());
		this.paymentDescription = payment.getDescription("order with id " + payment.getOrder().getId() + " is not made by user " + payment.getUser().getId());
	}

	public ObjectNode getPaymentDescription() {
		return paymentDescription;
	}
	
	
}
