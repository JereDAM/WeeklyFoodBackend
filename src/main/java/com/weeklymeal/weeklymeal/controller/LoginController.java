package com.weeklymeal.weeklymeal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.weeklymeal.weeklymeal.dto.LoginRequest;
import com.weeklymeal.weeklymeal.service.AuthService;

@RestController
public class LoginController {
	
	@Autowired
	static AuthService authService;
	
	@PostMapping("/login")
	public String login(@RequestBody LoginRequest loginRequest) {
	    String userName = loginRequest.getUserName();
	    String password = loginRequest.getPassword();

	    if (authService.authenticate(userName, password)) {
	        return "Login successful";
	    } else {
	        throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid username or password");
	    }
	}
}
