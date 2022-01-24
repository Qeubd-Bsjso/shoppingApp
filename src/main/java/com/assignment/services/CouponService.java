package com.assignment.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assignment.exceptions.InvalidCouponException;
import com.assignment.repositories.CouponRepository;
import com.assignment.shoppingApp.Coupon;

@Service
public class CouponService {

	private final CouponRepository couponRepository;
	
	@Autowired
	public CouponService(CouponRepository couponRepository) {
		super();
		this.couponRepository = couponRepository;
	}


	/*
	 * Function  : addCoupon
	 * 
	 * adds a new coupon to database
	 * 
	 * @param {name} : name for new coupon
	 * @param {per} : percentage discount with coupon
	 * 
	 * @return : void
	 * 
	 * */
	public void addCoupon(String name, Integer per) {
		Coupon c = new Coupon(name, per);
		couponRepository.save(c);
	}
	
	
	
	
	/*
	 * Function : getCoupons
	 * 
	 * returns a list of all coupons in database
	 * 
	 * @param : void
	 * 
	 * @return {List<Coupons>} : list of all coupons 
	 * 
	 * */
	public List<Coupon> getCoupons() {
		return (List<Coupon>) couponRepository.findAll();
	}
	
	
	
	
	
	/*
	 * Function : getCouponByName
	 * 
	 * returns reference to the Coupon with a given name
	 * 
	 * @param {coupon} : name of coupon
	 * 
	 * @return {Coupon} : reference to coupon
	 * 
	 * 
	 * @throws InvalidCouponException : if coupon with given name does not exists in database
	 * */
	public Coupon getCouponByName(String coupon) throws InvalidCouponException {
		Optional<Coupon> cpn = couponRepository.findById(coupon);
		if(cpn.isEmpty()) throw new InvalidCouponException(coupon);
		return cpn.get();
	}
	
}
