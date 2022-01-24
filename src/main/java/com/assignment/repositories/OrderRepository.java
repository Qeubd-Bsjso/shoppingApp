package com.assignment.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.assignment.shoppingApp.Order;
import com.assignment.shoppingApp.User;

public interface OrderRepository extends CrudRepository<Order,Long>{
	
	
	//find orders by user
	public List<Order> findByUser(User user);
}
