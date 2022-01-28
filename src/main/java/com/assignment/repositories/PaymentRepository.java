package com.assignment.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.assignment.model.Order;
import com.assignment.model.Payment;

public interface PaymentRepository extends CrudRepository<Payment,Long>{
	
	//find payment by Id
	public Optional<Payment> findById(Long id);
	
	//find payments for given order
	public List<Payment> findByOrder(Order id);
}
