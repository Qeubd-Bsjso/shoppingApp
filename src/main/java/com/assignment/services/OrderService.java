package com.assignment.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.assignment.exceptions.InvalidCouponException;
import com.assignment.exceptions.OrderNotFoundException;
import com.assignment.exceptions.OutOfStockException;
import com.assignment.exceptions.ProductNotFoundException;
import com.assignment.exceptions.UserNotFoundException;
import com.assignment.model.Coupon;
import com.assignment.model.Order;
import com.assignment.model.Product;
import com.assignment.model.User;
import com.assignment.repositories.OrderRepository;

@Service
public class OrderService {

	private final OrderRepository orderRepository;

	private final UserService userService;
	private final ProductService productService;
	private final CouponService couponService;

	public OrderService(OrderRepository orderRepository, UserService userService, ProductService productService, CouponService couponService) {
		super();
		this.orderRepository = orderRepository;
		this.userService = userService;
		this.productService = productService;
		this.couponService = couponService;
	}




	/*
	 * Function : createOrder
	 *
	 * creates an order entry with given userId, quantity, coupon, productId
	 *
	 *  @param {userId} : id of user who orders product
	 *  @param {qty} : quantity of product ordered
	 *  @param {coupon} : name of coupon if applicable
	 *  @param {productId} : id of product ordered
	 *
	 *  @return {Order} : reference to a new Order object
	 *
	 *  @throws {ProductNotFoundException} : if productId is invalid
	 *  @throws {UserNotFoundException} : if userId is invalid
	 *  @throws {OutOfStockException} : if quantity of order is more that product available or less than 1
	 *  @throws {InvalidCouponException} : if coupon is invalid
	 * */
	public Order createOrder(int userId, int qty, String coupon, int productId) throws ProductNotFoundException, UserNotFoundException, OutOfStockException, InvalidCouponException {

		// reference to product with productId
		Product product = productService.getProductById(productId);

		//reference to user with userId
		User user = userService.getUserById(userId);

		// check if quantity is valid
		if(qty < 1)
			throw new OutOfStockException(qty);
		if(qty > product.getQuantity()) {
			throw new OutOfStockException(product);
		}

		// create order object
		Order order = new Order(qty, user, product);

		if(coupon != null) {
			// check if coupon is valid
			if(user.getCoupons().contains(new Coupon(coupon, 1))) throw new InvalidCouponException(user.getId(), coupon);

			// add coupon to order
			Coupon cpn = couponService.getCouponByName(coupon);
			order.setCoupon(cpn);

			// add to user
			user.getCoupons().add(cpn);
		}

		// associate order to user
		user.addOrder(order);

		// save to database
		orderRepository.save(order);
		userService.saveUser(user);
		//productService.saveProduct(product);

		return order;

	}




	/*
	 * Function : getOrderById
	 *
	 * returns a reference to Order with a given id from database
	 *
	 * @param {id} : order id
	 *
	 * @return {Order} : reference to order
	 *
	 * @throws {OrderNotFoundException} : if order not found in database
	 * */
	public Order getOrderById(long id) throws OrderNotFoundException {
		Optional<Order> order = orderRepository.findById(id);
		if(order.isEmpty()) throw new OrderNotFoundException(id);
		return order.get();
	}




	/*
	 * Function : getOrderByUserId
	 *
	 * returns the list of all the orders made by a user
	 *
	 * @param {id} : user id
	 *
	 * @return {List<Order>} : list of all the orders made by user
	 *
	 * @throws {UserNotFoundException} : if user is not present in database
	 * */
	public List<Order> getOrderByUserId(int id) throws UserNotFoundException{
		User user = userService.getUserById(id);
		return orderRepository.findByUser(user);
	}
}
