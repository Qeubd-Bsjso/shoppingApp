package com.assignment.exceptions;

import com.assignment.model.Payment;
import com.fasterxml.jackson.databind.node.ObjectNode;

/*
 * Exception to simulate no response from payment server
 * */
@SuppressWarnings("serial")
public class NoResponseException extends RuntimeException{
	
	private final ObjectNode paymentDescription;
	
	public NoResponseException(){
		super("no response from server");
		paymentDescription = null;
	}

	public NoResponseException(Payment payment) {
		super("no response from server");
		paymentDescription = payment.getDescription("no response from payment server");
	}

	public ObjectNode getPaymentDescription() {
		return paymentDescription;
	}
}
