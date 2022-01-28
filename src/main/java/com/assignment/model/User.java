package com.assignment.model;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;



/*
 * class User
 * for all the users in database
 * */
@Entity
public class User {
	
	@Id
	@Column(name = "id")
	private int id;
	
	@Column(name = "name")
	private String name;
	
	
	@OneToMany
	private Set<Order> orders;
	
	@ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "user_coupon",
    		joinColumns = @JoinColumn(name = "id"),
    		inverseJoinColumns = @JoinColumn(name = "name")
    )
	private Set<Coupon> coupons;

	
	/*
	 * Default constructor
	 * */
	User(){
		
	}
	
	
	/*
	 * constructor
	 * */
	public User(int id, String name) {
		this.id = id;
		this.name = name;
		this.orders = new HashSet<>();
		this.coupons = new HashSet<>();
	}
	

	
	/*
	 * Getters and Setters
	 * */
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public Set<Order> getOrders() {
		return orders;
	}



	public void setOrders(Set<Order> orders) {
		this.orders = orders;
	}



	public void addOrder(Order order) {
		this.orders.add(order);
	}
	
	

	public Set<Coupon> getCoupons() {
		return coupons;
	}

	public void setCoupons(Set<Coupon> coupons) {
		this.coupons = coupons;
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
		User other = (User) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + "]";
	}
	
	
	
}
