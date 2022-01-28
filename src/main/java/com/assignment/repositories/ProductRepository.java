package com.assignment.repositories;

import org.springframework.data.repository.CrudRepository;

import com.assignment.model.Product;

public interface ProductRepository extends CrudRepository<Product,Integer>{

}
