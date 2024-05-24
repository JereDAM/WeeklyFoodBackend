package com.weeklymeal.weeklymeal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.weeklymeal.weeklymeal.entity.User;
import com.weeklymeal.weeklymeal.repository.UserRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class AuthService {
	
	@Autowired
	 private final UserRepository userRepository;

	//Finds the user via username and then checks the password
	    public boolean authenticate(String username, String password) {
	        User user = userRepository.findByUserName(username);
	        return user != null && user.getPassword().equals(password);
	    }

}
