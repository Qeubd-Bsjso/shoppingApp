package com.assignment.exceptions;

import org.springframework.http.HttpStatus;

import com.assignment.model.Payment;
import com.assignment.model.Product;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;



/*
 * Exception if product quantity is less than order quantity
 * */
@SuppressWarnings("serial")
public class OutOfStockException extends DescriptiveException{
	
	private final ObjectNode description;
	
	public OutOfStockException() {
		super("product out of stock");
		description = new ObjectMapper().createObjectNode();
		description.put("description", "product out of stock");
	}
	
	public OutOfStockException(Product product) {
		super("only " + product.getQuantity() + " left in the store");
		description = new ObjectMapper().createObjectNode();
		description.put("description", "only " + product.getQuantity() + " left in the store");
	}
	
	public OutOfStockException(int qty) {
		super(qty +" is invalid quantity for a product");
		description = new ObjectMapper().createObjectNode();
		description.put("description", qty +" is invalid quantity for a product");
	}

	public OutOfStockException(Payment payment) {
		super("only " + payment.getOrder().getProduct().getQuantity() + " left in the store");
		description = payment.getDescription("only " + payment.getOrder().getProduct().getQuantity() + " left in the store");
	}

	public ObjectNode getDescription() {
		return description;
	}

	@Override
	public HttpStatus getStatus() {
		return HttpStatus.NOT_FOUND;
	}
	
}
