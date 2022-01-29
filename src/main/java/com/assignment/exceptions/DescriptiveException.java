package com.assignment.exceptions;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.databind.node.ObjectNode;

@SuppressWarnings("serial")
public abstract class DescriptiveException extends RuntimeException{
	DescriptiveException(){
		super();
	}
	
	public DescriptiveException(String message){
		super(message);
	}
	
	abstract public ObjectNode getDescription();
	
	abstract public HttpStatus getStatus();
	
}
