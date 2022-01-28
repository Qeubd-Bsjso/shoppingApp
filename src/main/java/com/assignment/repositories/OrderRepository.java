package com.assignment.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.assignment.model.Order;
import com.assignment.model.User;

public interface OrderRepository extends CrudRepository<Order,Long>{
	
	
	//find orders by user
	public List<Order> findByUser(User user);
}
