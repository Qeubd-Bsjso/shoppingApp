package com.assignment.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assignment.exceptions.UserNotFoundException;
import com.assignment.shoppingApp.User;
import com.assignment.shoppingApp.repositories.UserRepository;

@Service
public class UserService {
	
	private final UserRepository userRepository;
	
	@Autowired
	public UserService(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}
	
	
	
	/*
	 * Function : addUser
	 * 
	 * adds a new user to database
	 * 
	 * @param {id} : user id
	 * @param {name} : user name
	 * 
	 * @return : void
	 * */
	public void addUser(int id, String name) {
		userRepository.save(new User(id, name));
	}
	
	
	
	/*
	 * Function : saveUser
	 * 
	 * saves or updates a given user
	 * 
	 * @param {user} : reference to user
	 * 
	 * @return : void
	 * */
	public void saveUser(User user) {
		userRepository.save(user);
	}
	
	
	
	
	/*
	 * Function : getUserById
	 * 
	 * returns reference to a user with given id
	 * 
	 * @param {id} : user id
	 * 
	 * @return {user} : reference to user
	 * 
	 * @throws {UserNotFoundException} : if user not present in database
	 * */
	public User getUserById(int id) throws UserNotFoundException {
		Optional<User> user = userRepository.findById(id); 
		if(user.isEmpty()) throw new UserNotFoundException(id); 
		return user.get();
	}
	
}
