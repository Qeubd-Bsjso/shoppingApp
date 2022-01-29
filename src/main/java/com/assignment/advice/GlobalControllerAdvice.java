package com.assignment.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.assignment.exceptions.AlreadyPaidForException;
import com.assignment.exceptions.DescriptiveException;
import com.assignment.exceptions.FailedFromBankException;
import com.assignment.exceptions.InvalidAmountException;
import com.assignment.exceptions.InvalidCouponException;
import com.assignment.exceptions.InvalidOrderIdException;
import com.assignment.exceptions.NoResponseException;
import com.assignment.exceptions.OrderNotFoundException;
import com.assignment.exceptions.OutOfStockException;
import com.assignment.exceptions.ProductNotFoundException;
import com.assignment.exceptions.UserNotFoundException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

@ControllerAdvice
public class GlobalControllerAdvice extends ResponseEntityExceptionHandler{
	
	@ExceptionHandler({
	                   AlreadyPaidForException.class, 
	                   FailedFromBankException.class,
	                   InvalidAmountException.class,
	                   InvalidCouponException.class,
	                   InvalidOrderIdException.class,
	                   NoResponseException.class,
	                   OrderNotFoundException.class,
	                   OutOfStockException.class,
	                   ProductNotFoundException.class,
	                   UserNotFoundException.class
	})
	public ResponseEntity<ObjectNode> handleCustomExceptions(DescriptiveException e){ 
		return ResponseEntity.status(e.getStatus()).body(e.getDescription());
	}
	
	@Override
	protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex,
			org.springframework.http.HttpHeaders headers, HttpStatus status, WebRequest request) {
		ObjectNode node = new ObjectMapper().createObjectNode();
		node.put("description", "request method not supported");
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(node);
	}
	
}
