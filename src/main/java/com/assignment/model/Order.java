package com.assignment.model;

import java.sql.Date;
import java.util.Objects;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


/*
 * 
 * class Order
 * for the orders made by users
 * */
@Entity
@Table(name = "_order")
public class Order {
	
	@Id
	@GeneratedValue(generator = "sequence-generator")
    @GenericGenerator(
      name = "sequence-generator",
      strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
      parameters = {
        @Parameter(name = "sequence_name", value = "user_sequence"),
        @Parameter(name = "initial_value", value = "101"),
        @Parameter(name = "increment_size", value = "1")
        }
    )
	@Column(name = "id")
	private Long id;
	
	@JsonIgnore
	@Column(name = "quantity")
	private Integer quantity;
	
	@Column(name = "amount")
	private Double amount;
	
	
	/*
	 * Many orders can use same coupon for different users
	 * */
	@ManyToOne(optional = true, fetch = FetchType.LAZY)
	@JoinColumn(name="name",nullable=true)
	private Coupon coupon;

	@JsonIgnore
	@ManyToOne
	private User user;
	
	@JsonIgnore
	@ManyToOne
	private Product product;
	
	@JsonIgnore
	private boolean paid;
	
	private Date date;
	
	
	/*
	 * Default constructor
	 * */
	Order(){
		
	}
	
	
	
	/*
	 * constructor
	 * */
	public Order(int qty, User user, Product product){
		this.quantity = qty;
		this.user = user;
		this.product = product;
		this.amount = (double)qty * product.getPrice();
		this.coupon = null;
		this.paid = false;
		this.date = new Date(new java.util.Date().getTime());
	}
	
	
	
	/*
	 * Getters and setter
	 * */
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public Coupon getCoupon() {
		return coupon;
	}

	public void setCoupon(Coupon coupon) {
		if(this.coupon == null) {
			this.coupon = coupon;
			this.amount = (this.amount * (100-coupon.getPercentage()))/100;
		}
	}


	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
		
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public boolean isPaid() {
		return paid;
	}

	public void setPaid(boolean paid) {
		this.paid = paid;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Order other = (Order) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		ObjectMapper mapper = new ObjectMapper();
		ObjectNode node = mapper.createObjectNode();
		node.put("order_id", id);
		node.put("user_id", user.getId());
		node.put("quantity", quantity);
		node.put("amount", amount);
		node.put("coupon", (coupon != null?coupon.getName():null));
		try {
			return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(node);
		} catch (JsonProcessingException e) {
			return " { \"order_id\":" + id + ", \"userId\":"  + user.getId() + ", \"quantity\":" + quantity + ", \"amount\":" + amount + ", \"coupon\":" + (coupon != null?coupon.getName():null) + "}";
		}
	}
	
}
