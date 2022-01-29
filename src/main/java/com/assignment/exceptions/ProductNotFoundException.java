package com.assignment.exceptions;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

/*
 * Exception if product not found 
 * */
@SuppressWarnings("serial")
public class ProductNotFoundException extends DescriptiveException{

	private final ObjectNode description;
	
	public ProductNotFoundException() {
		super("product not found");
		description = new ObjectMapper().createObjectNode();
		description.put("description", "product not found");
	}
	
	public ProductNotFoundException(int id) {
		super("product with id " + id + " not found");
		description = new ObjectMapper().createObjectNode();
		description.put("description", "product with id " + id + " not found");
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
