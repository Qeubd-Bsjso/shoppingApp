package com.assignment.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.assignment.exceptions.AlreadyPaidForException;
import com.assignment.exceptions.FailedFromBankException;
import com.assignment.exceptions.InvalidAmountException;
import com.assignment.exceptions.InvalidOrderIdException;
import com.assignment.exceptions.NoResponseException;
import com.assignment.exceptions.OrderNotFoundException;
import com.assignment.exceptions.UserNotFoundException;
import com.assignment.repositories.PaymentRepository;
import com.assignment.shoppingApp.Order;
import com.assignment.shoppingApp.Payment;
import com.assignment.shoppingApp.User;

@Service
public class PaymentService {
	private final PaymentRepository paymentRepository;
	
	private final OrderService orderService;
	private final UserService userService;
	
	
	public PaymentService(PaymentRepository paymentRepository, OrderService orderService, UserService userService) {
		super();
		this.paymentRepository = paymentRepository;
		this.orderService = orderService;
		this.userService = userService;
	}


	
	/*
	 * Function : makePayment
	 * 
	 * create a new payment object from given userId and orderId
	 * 
	 * @param {userId} : user id
	 * @param {orderId} : order id
	 * 
	 * @return {Payment} : reference to newly created payment object
	 * 
	 * @throws {UserNotFoundException} : if user not found in database
	 * @throws {OrderNotFoundException} : if order not found in database
	 * */
	public Payment makePayment(int userId, long orderId) throws UserNotFoundException, OrderNotFoundException {
		Order order = orderService.getOrderById(orderId);
		User user = userService.getUserById(userId);
		Payment payment = new Payment(order, user);
		return payment;
	}
	
	
	
	
	/*
	 * Function : validate 
	 * 
	 * validates a payment , throws error if anomalies found and payment is unsuccessful
	 * 
	 * @param {payment} : reference to payment object to be validated
	 * @param {amount} : amount of payment
	 * 
	 * @return : void
	 * 
	 * @throws {AlreadyPaidForException} : if order is already paid for
	 * @throws {InvalidOrderIdException} : if order is invalid
	 * @throws {InvalidAmountException} : if payment amount do not match order amount
	 * @throws {NoResponseException} : not response from payment server
	 * @throws {FailedFromBankException} : payment failed from bank
	 * */
	public void validate(Payment payment, double amount) throws AlreadyPaidForException, InvalidOrderIdException, InvalidAmountException, NoResponseException, FailedFromBankException {
		try {
			payment.validate(amount);
		} catch (AlreadyPaidForException | InvalidOrderIdException | InvalidAmountException | NoResponseException
				| FailedFromBankException e) {
			paymentRepository.save(payment);
			throw e;
		}
		payment.getOrder().setPaid(true);
		paymentRepository.save(payment);
	}
	
	
	
	
	
	/*
	 * Function : getPaymentByOrderId
	 * 
	 * returns list of all the payments done for a given order
	 * 
	 * @param {userId} : user id
	 * @param {orderId} : order id
	 * 
	 * @return {List<Payment>} : list of payments
	 * 
	 * @throws {UserNotFoundException} : user not present in database
	 * @throws {OrderNotFoundException} : order not present in database
	 * @throws {InvalidOrderIdException} : order is made by other user
	 * */
	public List<Payment> getPaymentsByOrderId(int userId, long orderId) throws UserNotFoundException, OrderNotFoundException, InvalidOrderIdException{
		User user = userService.getUserById(userId);
		Order order = orderService.getOrderById(orderId);
		if(user.getId() != order.getUser().getId()) throw new InvalidOrderIdException();
		return paymentRepository.findByOrder(order);
	}
	
}
