package com.assignment.exceptions;

import com.assignment.model.Payment;
import com.fasterxml.jackson.databind.node.ObjectNode;

/*
 * Exception if amount of order is different from payment amount
 * */
@SuppressWarnings("serial")
public class InvalidAmountException extends RuntimeException{
	
	private final ObjectNode paymentDescription;
	
	public InvalidAmountException(){
		super("invalid amount for the order");
		paymentDescription = null;
	}

	public InvalidAmountException(Payment payment) {
		super("invalid amount for the order, amount needed = " + payment.getOrder().getAmount());
		paymentDescription = payment.getDescription("invalid amount for the order, amount needed = " + payment.getOrder().getAmount());
	}

	public ObjectNode getPaymentDescription() {
		return paymentDescription;
	}
}
