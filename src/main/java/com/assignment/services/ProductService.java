package com.assignment.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assignment.exceptions.ProductNotFoundException;
import com.assignment.repositories.ProductRepository;
import com.assignment.shoppingApp.Product;

@Service
public class ProductService {
	
	private final ProductRepository productRepository;
	
	@Autowired
	public ProductService(ProductRepository productRepository) {
		super();
		this.productRepository = productRepository;
	}


	/*
	 * Function : addProduct
	 * 
	 * add a new product to database
	 * 
	 * @param {id} : product id
	 * @param {name} : product name
	 * @param {quantity} : quantity of product
	 * @param {price} : price of product
	 * 
	 * @return : void
	 * */
	public void addProduct(int id, String name, long quantity, long price) {
		productRepository.save(new Product(id, name, quantity, price));
	}
	
	
	
	
	/*
	 * Function : saveProduct
	 * 
	 * saves or update a given product
	 * 
	 * @param {product} : reference to product
	 * 
	 * @return : void
	 * */
	public void saveProduct(Product product) {
		productRepository.save(product);
	}
	
	
	
	/*
	 * Function : getProductById
	 * 
	 * returns reference to product with a given id
	 * 
	 * @param {id} : product id
	 * 
	 * @return {Product} : reference to product
	 * 
	 * @throws {ProductNotFoundException} : product not found in database
	 * */
	public Product getProductById(int id) throws ProductNotFoundException {
		Optional<Product> product = productRepository.findById(id);
		if(product.isEmpty()) throw new ProductNotFoundException(id);
		return product.get();
	}
	
	
}
