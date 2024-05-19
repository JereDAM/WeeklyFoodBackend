package com.weeklymeal.weeklymeal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.weeklymeal.weeklymeal.dto.UserDto;
import com.weeklymeal.weeklymeal.entity.User;
import com.weeklymeal.weeklymeal.service.UserService;

@RestController
@RequestMapping("/api/v1")
public class UserController {
	
	@Autowired
	UserService userService;
	
	//Get ALL users from database
	@GetMapping("/users")
	public List<User> allUsers(){
		return userService.getAllUsers();
	}
	
	//Get an specific user via id
	@GetMapping("/user/{userId}")
	public User findUserById(@PathVariable Long userId) {
		return userService.findById(userId);
	}
	
	//Create a new user
	@PostMapping("/user")
	public ResponseEntity<UserDto> createUser(@RequestBody User user){
		return userService.createUser(user);
	}
	
	//Delete a user via Id
	@DeleteMapping("/user/{userId}")
	public String deleteUser(@PathVariable Long userId) {
		return userService.deleteUser(userId);
	}
	
	//Update a user via Id
	@PutMapping("user/{userId}")
	public ResponseEntity<User> updateUser(@PathVariable Long userId, @RequestBody User user){
		return userService.updateUser(userId, user);
	}
	
}
