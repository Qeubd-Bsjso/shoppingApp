package com.assignment.exceptions;

import org.springframework.http.HttpStatus;

import com.assignment.model.Payment;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

/*
 * Exception to simulate the negative response from bank
 * */
@SuppressWarnings("serial")
public class FailedFromBankException extends DescriptiveException{
	
	private final ObjectNode description;
	
	public FailedFromBankException(){
		super("payment failed from bank");
		description = new ObjectMapper().createObjectNode();
		description.put("description", "payment failed from bank");
	}

	public FailedFromBankException(Payment payment) {
		super("payment failed from bank");
		description = payment.getDescription("payment failed from bank");
	}

	public HttpStatus getStatus() {
		return HttpStatus.BAD_REQUEST;
	}

	@Override
	public ObjectNode getDescription() {
		return description;
	}

}
