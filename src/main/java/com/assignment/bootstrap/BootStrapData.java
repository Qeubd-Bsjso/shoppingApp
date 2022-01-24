package com.assignment.bootstrap;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.assignment.shoppingApp.User;
import com.assignment.repositories.CouponRepository;
import com.assignment.repositories.ProductRepository;
import com.assignment.repositories.UserRepository;
import com.assignment.shoppingApp.Coupon;
import com.assignment.shoppingApp.Product;


/*
 * Bootstrap file
 * 
 * creates initial entries in database
 * 
 * */
@Component
public class BootStrapData implements CommandLineRunner{

	private final UserRepository userRepository;
	private final ProductRepository productRepository;
	private final CouponRepository couponRepository;
	
	public BootStrapData(UserRepository userRepository, ProductRepository productRepository, CouponRepository couponRepository) {
		super();
		this.userRepository = userRepository;
		this.productRepository = productRepository;
		this.couponRepository = couponRepository;
	}

	@Override
	public void run(String... args) throws Exception {
		
		// add default product to database
		Product p = new Product(10,"Default Product", (long)100, (long)100);
		this.productRepository.save(p);
		
		// add default user to database
		User u = new User(1001,"user1");
		userRepository.save(u);
		
		Coupon c1 = new Coupon("OFF5",5);
		Coupon c2 = new Coupon("OFF10",10);
		
		couponRepository.save(c1);
		couponRepository.save(c2);
		
		System.out.println("User: {{ \"id\" : 1001, \"name\" : \"user1\"}}");
	}

}
