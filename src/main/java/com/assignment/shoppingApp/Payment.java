package com.assignment.shoppingApp;

import java.sql.Date;
import java.util.Objects;
import java.util.Random;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.assignment.exceptions.AlreadyPaidForException;
import com.assignment.exceptions.FailedFromBankException;
import com.assignment.exceptions.InvalidAmountException;
import com.assignment.exceptions.InvalidOrderIdException;
import com.assignment.exceptions.NoResponseException;


/*
 * 
 * Payment class 
 * 
 * transactions for a given order
 * */
@Entity
public class Payment {
	
	@Id
	@GeneratedValue(generator = "sequence-generator")
    @GenericGenerator(
      name = "sequence-generator",
      strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
      parameters = {
        @Parameter(name = "sequence_name", value = "payment_sequence"),
        @Parameter(name = "initial_value", value = "1010000001"),
        @Parameter(name = "increment_size", value = "7")
        }
    )
	private Long transactionId;
	
	private Date date;
	
	@ManyToOne
	private Order order;
	
	@ManyToOne
	private User user;
	
	private String status;
	

	/*
	 * Default constructor
	 * */
	public Payment() {
		super();
	}


	/*
	 * Constructor
	 * */
	public Payment(Order order, User user)  {
		super();
		this.order = order;
		this.user = user;
		this.date = new Date(new java.util.Date().getTime());
		this.status = null;
	}

	
	/*
	 * Getters and Setter
	 * */
	
	public Long getTransactionId() {
		return transactionId;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}



	public Date getDate() {
		return date;
	}


	public void setDate(Date date) {
		this.date = date;
	}


	public Order getOrder() {
		return order;
	}


	public void setOrder(Order order) {
		this.order = order;
	}


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}
	
	public void validate(double amount) throws AlreadyPaidForException, InvalidOrderIdException, InvalidAmountException, NoResponseException, FailedFromBankException {
		if(user.getId() != order.getUser().getId()){
			this.setStatus("failed"); 
			throw new InvalidOrderIdException();
		}
		if(order.isPaid()){
			this.setStatus("failed"); 
			throw new AlreadyPaidForException();
		}
		if(order.getAmount() != amount) {
			this.setStatus("failed");
			throw new InvalidAmountException();
		}
		int rand = new Random().nextInt(4);
		if(rand == 0) {
			this.setStatus("failed");
			throw new NoResponseException();
		}
		if(rand == 1) {
			this.setStatus("failed");
			throw new FailedFromBankException();
		}
		this.setStatus("successful");
	}


	@Override
	public int hashCode() {
		return Objects.hash(transactionId);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Payment other = (Payment) obj;
		return Objects.equals(transactionId, other.transactionId);
	}
	
	
	
}
