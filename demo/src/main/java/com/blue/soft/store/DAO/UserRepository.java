package com.blue.soft.store.DAO;

import org.springframework.data.repository.CrudRepository;

import com.blue.soft.store.entity.User;

public interface UserRepository extends CrudRepository<User, String> {

	public User findByNameAndPassword(String name, String password);
	
}
