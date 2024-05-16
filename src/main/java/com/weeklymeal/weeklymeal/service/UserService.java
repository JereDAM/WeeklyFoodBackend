package com.weeklymeal.weeklymeal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.weeklymeal.weeklymeal.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	UserRepository userRepository;
	
}
