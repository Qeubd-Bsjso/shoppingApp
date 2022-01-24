package com.assignment.repositories;

import org.springframework.data.repository.CrudRepository;

import com.assignment.shoppingApp.User;

public interface UserRepository extends CrudRepository<User,Integer>{

}
