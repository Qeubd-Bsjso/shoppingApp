package com.assignment.exceptions;

import org.springframework.http.HttpStatus;

import com.assignment.model.Payment;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

/*
 * Exception to simulate no response from payment server
 * */
@SuppressWarnings("serial")
public class NoResponseException extends DescriptiveException{
	
	private final ObjectNode description;
	
	public NoResponseException(){
		super("no response from server");
		description = new ObjectMapper().createObjectNode();
		description.put("description", "no response from server");
	}

	public NoResponseException(Payment payment) {
		super("no response from server");
		description = payment.getDescription("no response from payment server");
	}

	public ObjectNode getDescription() {
		return description;
	}

	@Override
	public HttpStatus getStatus() {
		return HttpStatus.GATEWAY_TIMEOUT;
	}
}
