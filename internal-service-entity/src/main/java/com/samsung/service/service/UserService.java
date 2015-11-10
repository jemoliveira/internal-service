package com.samsung.service.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.samsung.service.model.UserBean;
import com.samsung.service.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public UserBean findByEmail(String email){
        return userRepository.findByEmail(email);
    }
    
    public UserBean findByName(String name){
        return userRepository.findByName(name);
    }
}
