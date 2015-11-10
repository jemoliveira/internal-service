package com.samsung.service.repository;

import org.springframework.data.repository.CrudRepository;

import com.samsung.service.model.UserBean;

public interface UserRepository extends CrudRepository<UserBean, Integer> {
	
    UserBean findByEmail(String email);
    UserBean findByName(String name);
    
}
