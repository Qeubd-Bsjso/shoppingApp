package com.assignment.repositories;

import org.springframework.data.repository.CrudRepository;

import com.assignment.model.User;

public interface UserRepository extends CrudRepository<User,Integer>{

}
