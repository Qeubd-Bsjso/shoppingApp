package com.assignment.shoppingApp.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.assignment.shoppingApp.Order;
import com.assignment.shoppingApp.Payment;

public interface PaymentRepository extends CrudRepository<Payment,Long>{
	
	//find payment by Id
	public Optional<Payment> findById(Long id);
	
	//find payments for given order
	public List<Payment> findByOrder(Order id);
}
