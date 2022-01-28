package com.assignment.Controllers;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
import com.assignment.model.Order;
import com.assignment.model.Payment;
import com.assignment.model.User;
import com.assignment.services.OrderService;
import com.assignment.services.PaymentService;
import com.assignment.services.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

@Controller
public class UserController {
	private final UserService userService;
	private final OrderService orderService;
	private final PaymentService paymentService;
	
	@Autowired
	public UserController(UserService userService, OrderService orderService, PaymentService paymentService) {
		super();
		this.userService = userService;
		this.orderService = orderService;
		this.paymentService = paymentService;
	}
	
	
	
	/*
	 *  Mapping to make an order for a given quantity and coupon 
	 *  
	 *  
	 *  
	 *  return : JSON of order
	 *   
	 * */
	@PostMapping("/{userid}/order")
	public ResponseEntity<ObjectNode> order(@PathVariable("userid") int id, @RequestParam("qty") int qty, @RequestParam(name="coupon", required=false) String coupon) {
		try {
			
			//create order object
			Order order = orderService.createOrder(id, qty, coupon, 10);
			
			//on successful creation return object as JSON
			ObjectMapper mapper = new ObjectMapper();
			ObjectNode node = mapper.createObjectNode();
			node.put("orderId", order.getId());
			node.put("userId", order.getUser().getId());
			node.put("quantiry", order.getQuantity());
			node.put("amount", order.getAmount());
			if(order.getCoupon() != null)
				node.put("coupon", order.getCoupon().getName());
			return ResponseEntity.status(HttpStatus.OK).body(node);
		} catch (ProductNotFoundException | UserNotFoundException e) {
			// user or product not found
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
		catch(OutOfStockException e) {
			// If enough quantity of product is not available
			ObjectMapper mapper = new ObjectMapper();
			ObjectNode node = mapper.createObjectNode();
			node.put("discription", "Invalid Quantity");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(node);
		}
		catch(InvalidCouponException e) {
			// If coupon is invalid or aleady used
			ObjectMapper mapper = new ObjectMapper();
			ObjectNode node = mapper.createObjectNode();
			node.put("discription", "Invalid Coupon");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(node);
		}
	}
	
	
	
	
	
	
	/*
	 * Mapping to pay for an order
	 * 
	 * 
	 * return : JSON of transaction
	 * 
	 * */
	@PostMapping("/{user_id}/{order_id}/pay")
	public ResponseEntity<ObjectNode> pay(@PathVariable("user_id") int userId, @PathVariable("order_id") long orderId, @RequestParam("amount") double amount) {
		Payment payment = null;
		try {
			
			// try to make payment
			payment = paymentService.makePayment(userId, orderId);
			
			// validate the payment
			// throws errors if payment is unsuccessful
			paymentService.validate(payment, amount);
			
		} catch (UserNotFoundException e) {
			// for invalid userId
			ObjectMapper mapper = new ObjectMapper();
			ObjectNode node = mapper.createObjectNode();
			node.put("discription", "Payment Failed because user with id "+ userId + " not found.");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(node);
		} catch (OrderNotFoundException e) {
			// for invalid orderId
			ObjectMapper mapper = new ObjectMapper();
			ObjectNode node = mapper.createObjectNode();
			node.put("discription", "Payment Failed because order with id "+ orderId + " not found.");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(node);
		} catch (AlreadyPaidForException e) {
			// if order is already paid for
			ObjectMapper mapper = new ObjectMapper();
			ObjectNode node = mapper.createObjectNode();
			node.put("userId", payment.getUser().getId());
			node.put("orderId", payment.getOrder().getAmount());
			node.put("transactionId", payment.getTransactionId());
			node.put("status", payment.getStatus());
			node.put("discription", "Order is already paid for");
			return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(node);
		} catch (InvalidOrderIdException e) {
			// if order is made by some other user
			ObjectMapper mapper = new ObjectMapper();
			ObjectNode node = mapper.createObjectNode();
			node.put("userId", payment.getUser().getId());
			node.put("orderId", payment.getOrder().getAmount());
			node.put("transactionId", payment.getTransactionId());
			node.put("status", payment.getStatus());
			node.put("discription", "Payment Failed due to invalid order id");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(node);
		} catch (InvalidAmountException e) {
			// if order amount is not equal to payment amount
			ObjectMapper mapper = new ObjectMapper();
			ObjectNode node = mapper.createObjectNode();
			node.put("userId", payment.getUser().getId());
			node.put("orderId", payment.getOrder().getAmount());
			node.put("transactionId", payment.getTransactionId());
			node.put("status", payment.getStatus());
			node.put("discription", "Payment Failed as amount is invalid");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(node);
		} catch (NoResponseException e) {
			// if there is no response from payment server
			ObjectMapper mapper = new ObjectMapper();
			ObjectNode node = mapper.createObjectNode();
			node.put("userId", payment.getUser().getId());
			node.put("orderId", payment.getOrder().getAmount());
			node.put("transactionId", payment.getTransactionId());
			node.put("status", payment.getStatus());
			node.put("discription", "No response from payment server");
			return ResponseEntity.status(HttpStatus.GATEWAY_TIMEOUT).body(node);
		} catch (FailedFromBankException e) {
			// if bank fails transaction
			ObjectMapper mapper = new ObjectMapper();
			ObjectNode node = mapper.createObjectNode();
			node.put("userId", payment.getUser().getId());
			node.put("orderId", payment.getOrder().getAmount());
			node.put("transactionId", payment.getTransactionId());
			node.put("status", payment.getStatus());
			node.put("discription", "Payment Failed from bank");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(node);
		}
		
		// successful transaction
		ObjectMapper mapper = new ObjectMapper();
		ObjectNode node = mapper.createObjectNode();
		node.put("userId", payment.getUser().getId());
		node.put("orderId", payment.getOrder().getAmount());
		node.put("transactionId", payment.getTransactionId());
		node.put("status", payment.getStatus());
		return ResponseEntity.status(HttpStatus.OK).body(node);
	}
	
	
	
	
	/*
	 * Mapping to get list of orders made by a given user
	 * 
	 * 
	 * return : array of JSON of orders made by a user
	 * 
	 * */
	@GetMapping("/{user_id}/orders")
	public ResponseEntity<List<ObjectNode>> getOrders(@PathVariable("user_id") int userId) {
		try {
			
			// get user from userId
			User user = userService.getUserById(userId);
			
			List<ObjectNode> res = new ArrayList<>();
			
			// iterate over orders of given user
			for(Order order : user.getOrders()) {
				ObjectNode node = new ObjectMapper().createObjectNode();
				node.put("orderId", order.getId());
				node.put("amount", order.getAmount());
				node.put("date", order.getDate().toString());
				node.put("coupon", order.getCoupon()==null?null:order.getCoupon().getName());
				res.add(node);
			}
			return ResponseEntity.status(HttpStatus.OK).body(res);
		} catch (UserNotFoundException e) {
			// invalid userId
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
	}
	
	
	
	
	/*
	 * Mapping for all the transactions for a given order
	 * 
	 * 
	 * return : array of JSON of transactions
	 * 
	 * */
	@GetMapping("/{user_id}/orders/{order_id}")
	public ResponseEntity<List<ObjectNode>> getOrdersById(@PathVariable("user_id") int userId, @PathVariable("order_id") int orderId) {
		try {
			List<ObjectNode> res = new ArrayList<>();
			
			// iterate over all payments
			for(Payment payment : paymentService.getPaymentsByOrderId(userId, orderId)) {
				ObjectNode node = new ObjectMapper().createObjectNode();
				node.put("orderId", payment.getOrder().getId());
				node.put("amount", payment.getOrder().getAmount());
				node.put("date", payment.getDate().toString());
				if(payment.getOrder().getCoupon() != null)
					node.put("coupon",payment.getOrder().getCoupon().getName());
				node.put("transactionId", payment.getTransactionId());
				node.put("status", payment.getStatus());
				res.add(node);
			}
			
			// return array if successful
			return ResponseEntity.status(HttpStatus.OK).body(res);
		} catch (UserNotFoundException e) {
			// invalid userId
			ObjectNode node = new ObjectMapper().createObjectNode();
			node.put("userId", userId);
			node.put("description", "User not found");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(List.of(node));
		} catch (OrderNotFoundException e) {
			// invalid orderId
			ObjectNode node = new ObjectMapper().createObjectNode();
			node.put("orderId", orderId);
			node.put("description", "Order not found");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(List.of(node));
		} catch (InvalidOrderIdException e) {
			// if order is made by some other user
			ObjectNode node = new ObjectMapper().createObjectNode();
			node.put("orderId", orderId);
			node.put("description", "Order not found");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(List.of(node));
		}
	}
	
}
