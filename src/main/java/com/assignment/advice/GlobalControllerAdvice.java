package com.assignment.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.assignment.exceptions.AlreadyPaidForException;
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
	
	@ExceptionHandler(AlreadyPaidForException.class)
	public ResponseEntity<ObjectNode> handleAlreadyPaidForException(AlreadyPaidForException alreadyPaidForException){
		if(alreadyPaidForException.getPaymentDescription() != null) 
			return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(alreadyPaidForException.getPaymentDescription()); 
		ObjectNode node = new ObjectMapper().createObjectNode();
		node.put("description", alreadyPaidForException.getMessage());
		return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(node);
	}
	
	@ExceptionHandler(FailedFromBankException.class)
	public ResponseEntity<ObjectNode> handleFailedFromBankException(FailedFromBankException failedFromBankException){
		if(failedFromBankException.getPaymentDescription() != null)
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(failedFromBankException.getPaymentDescription());
		ObjectNode node = new ObjectMapper().createObjectNode();
		node.put("description", failedFromBankException.getMessage());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(node);
	}
	
	@ExceptionHandler(InvalidAmountException.class)
	public ResponseEntity<ObjectNode> handleInvalidAmountException(InvalidAmountException invalidAmountException){
		if(invalidAmountException.getPaymentDescription() != null)
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(invalidAmountException.getPaymentDescription());
		ObjectNode node = new ObjectMapper().createObjectNode();
		node.put("description", invalidAmountException.getMessage());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(node);
	}
	
	@ExceptionHandler(InvalidCouponException.class)
	public ResponseEntity<ObjectNode> handleInvalidCouponException(InvalidCouponException invalidCouponException){
		ObjectNode node = new ObjectMapper().createObjectNode();
		node.put("description", invalidCouponException.getMessage());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(node);
	}
	
	@ExceptionHandler(InvalidOrderIdException.class)
	public ResponseEntity<ObjectNode> handleInvalidOrderIdException(InvalidOrderIdException invalidOrderIdException){
		if(invalidOrderIdException.getPaymentDescription() != null)
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(invalidOrderIdException.getPaymentDescription());
		ObjectNode node = new ObjectMapper().createObjectNode();
		node.put("description", invalidOrderIdException.getMessage());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(node);
	}
	
	@ExceptionHandler(NoResponseException.class)
	public ResponseEntity<ObjectNode> handleNoResponseException(NoResponseException noResponseException){
		if(noResponseException.getPaymentDescription() != null)
			return ResponseEntity.status(HttpStatus.GATEWAY_TIMEOUT).body(noResponseException.getPaymentDescription());
		ObjectNode node = new ObjectMapper().createObjectNode();
		node.put("description", noResponseException.getMessage());
		return ResponseEntity.status(HttpStatus.GATEWAY_TIMEOUT).body(node);
	}
	
	@ExceptionHandler(OrderNotFoundException.class)
	public ResponseEntity<ObjectNode> handleOrderNotFoundException(OrderNotFoundException orderNotFoundException){
		ObjectNode node = new ObjectMapper().createObjectNode();
		node.put("description", orderNotFoundException.getMessage());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(node);
	}
	
	@ExceptionHandler(OutOfStockException.class)
	public ResponseEntity<ObjectNode> handleOutOfStockException(OutOfStockException outOfStockException){
		if(outOfStockException.getPaymentDescription() != null)
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(outOfStockException.getPaymentDescription());
		ObjectNode node = new ObjectMapper().createObjectNode();
		node.put("description", outOfStockException.getMessage());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(node);
	}
	
	@ExceptionHandler(ProductNotFoundException.class)
	public ResponseEntity<ObjectNode> handleProductNotFoundException(ProductNotFoundException productNotFoundException){
		ObjectNode node = new ObjectMapper().createObjectNode();
		node.put("description", productNotFoundException.getMessage());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(node);
	}
	
	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<ObjectNode> handleUserNotFoundException(UserNotFoundException userNotFoundException){
		ObjectNode node = new ObjectMapper().createObjectNode();
		node.put("description", userNotFoundException.getMessage());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(node);
	}
	
	
	@Override
	protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex,
			org.springframework.http.HttpHeaders headers, HttpStatus status, WebRequest request) {
		ObjectNode node = new ObjectMapper().createObjectNode();
		node.put("description", "request method not supported");
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(node);
	}
	
}
