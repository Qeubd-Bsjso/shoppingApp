package com.assignment.exceptions;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

/*
 * Exception if order not found
 * */
@SuppressWarnings("serial")
public class OrderNotFoundException extends DescriptiveException{
	
	private final ObjectNode description;
	
	public OrderNotFoundException() {
		super("order not found");
		description = new ObjectMapper().createObjectNode();
		description.put("description", "order not found");
	}
	
	public OrderNotFoundException(long id) {
		super("order with id " + id + " not found");
		description = new ObjectMapper().createObjectNode();
		description.put("description", "order with id " + id + " not found");
	}

	@Override
	public ObjectNode getDescription() {
		return description;
	}

	@Override
	public HttpStatus getStatus() {
		return HttpStatus.NOT_FOUND;
	}
	
}
