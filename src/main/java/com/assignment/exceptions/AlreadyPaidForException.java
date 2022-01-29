package com.assignment.exceptions;

import com.assignment.model.Payment;
import com.fasterxml.jackson.databind.node.ObjectNode;

/*
 * Exception if order is already paid for  
 * */
@SuppressWarnings("serial")
public class AlreadyPaidForException extends RuntimeException{
	
	private final ObjectNode paymentDescription;
	
	public AlreadyPaidForException() {
		super("order is aleady paid for");
		paymentDescription = null;
	}
	
	public AlreadyPaidForException(Payment payment) {
		super("order is aleady paid for");
		paymentDescription = payment.getDescription("Order is already paid for");
	}

	public ObjectNode getPaymentDescription() {
		return paymentDescription;
	}
}
