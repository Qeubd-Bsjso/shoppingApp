package com.assignment.repositories;

import org.springframework.data.repository.CrudRepository;

import com.assignment.shoppingApp.Product;

public interface ProductRepository extends CrudRepository<Product,Integer>{

}
