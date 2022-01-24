package com.assignment.Controllers;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.assignment.services.CouponService;
import com.assignment.services.ProductService;
import com.assignment.shoppingApp.Coupon;
import com.assignment.shoppingApp.Product;


@Controller
public class InventoryController {
	
	private final ProductService productService;
	private final CouponService couponService;
	
	
	@Autowired
	public InventoryController(ProductService productService, CouponService couponService) {
		super();
		this.productService = productService;
		this.couponService = couponService;
	}


	
	/*
	 * Mapping to check inventory
	 * 
	 * */
	@GetMapping("/inventory")
	public ResponseEntity<Product> getInventory() {
		try {
			// reference to default product
			Product p = productService.getProductById(10);
			
			//return default product
			return ResponseEntity.status(HttpStatus.OK).body(p);
		} catch (Exception e) {
			// return null
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
	}
	
	
	
	/*
	 * Mapping to check the list of coupons
	 * 
	 * */
	
	@GetMapping("/fetchCoupons")
	public ResponseEntity<List<Coupon>> getCoupons() {
		//return the list of coupons
		return ResponseEntity.status(HttpStatus.OK).body(couponService.getCoupons());
	}
	
}