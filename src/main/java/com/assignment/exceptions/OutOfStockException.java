package com.assignment.exceptions;

import com.assignment.model.Payment;
import com.assignment.model.Product;
import com.fasterxml.jackson.databind.node.ObjectNode;



/*
 * Exception if product quantity is less than order quantity
 * */
@SuppressWarnings("serial")
public class OutOfStockException extends RuntimeException{
	
	private final ObjectNode paymentDescription;
	
	public OutOfStockException() {
		super("product out of stock");
		paymentDescription = null;
	}
	
	public OutOfStockException(Product product) {
		super("only " + product.getQuantity() + " left in the store");
		paymentDescription = null;
	}
	
	public OutOfStockException(int qty) {
		super(qty +" is invalid quantity for a product");
		paymentDescription = null;
	}

	public OutOfStockException(Payment payment) {
		super("only " + payment.getOrder().getProduct().getQuantity() + " left in the store");
		paymentDescription = payment.getDescription("only " + payment.getOrder().getProduct().getQuantity() + " left in the store");
	}

	public ObjectNode getPaymentDescription() {
		return paymentDescription;
	}
	
}
