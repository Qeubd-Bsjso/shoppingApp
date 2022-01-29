package com.assignment.exceptions;

import com.assignment.model.Payment;
import com.fasterxml.jackson.databind.node.ObjectNode;

/*
 * Exception to simulate the negative response from bank
 * */
@SuppressWarnings("serial")
public class FailedFromBankException extends RuntimeException{
	
	private final ObjectNode paymentDescription;
	
	public FailedFromBankException(){
		super("payment failed from bank");
		paymentDescription = null;
	}

	public FailedFromBankException(Payment payment) {
		super("payment failed from bank");
		paymentDescription = payment.getDescription("payment failed from bank");
	}

	public ObjectNode getPaymentDescription() {
		return paymentDescription;
	}

}
