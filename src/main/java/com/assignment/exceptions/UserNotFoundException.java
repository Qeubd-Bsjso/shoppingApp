package com.assignment.exceptions;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

/*
 * Exception if user not found
 * */
@SuppressWarnings("serial")
public class UserNotFoundException extends DescriptiveException{

	private final ObjectNode description;
	
	public UserNotFoundException() {
		super("user not found");
		description = new ObjectMapper().createObjectNode();
		description.put("description", "user not found");
	}
	
	public UserNotFoundException(int id) {
		super("user with id " + id + " not found");
		description = new ObjectMapper().createObjectNode();
		description.put("description", "user with id " + id + " not found");
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
