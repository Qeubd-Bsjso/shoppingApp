package com.assignment.model;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;


/*
 * class Coupon
 * for all the applicable coupons
 * */

@Entity
public class Coupon {
	
	@Id
	private String name;
	
	private Integer percentage;
	
	/*
	 * One user can use many coupons
	 * One coupon can be used by all users once 
	 * */
	@ManyToMany(cascade = CascadeType.ALL, mappedBy = "coupons")
	@JsonIgnore
	private Set<User> users;
	

	/*
	 * Default constructor
	 * */
	public Coupon() {
		super();
	}

	
	/*
	 * constructor
	 * */
	public Coupon(String name, Integer percentage) {
		super();
		this.name = name;
		this.percentage = percentage;
		this.users = new HashSet<>();
	}

	
	/*
	 * 
	 * Getters and Setters
	 * */
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getPercentage() {
		return percentage;
	}

	public void setPercentage(Integer percentage) {
		this.percentage = percentage;
	}

	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

	@Override
	public int hashCode() {
		return Objects.hash(name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Coupon other = (Coupon) obj;
		return Objects.equals(name, other.name);
	}
	
	
}
