package com.weeklymeal.weeklymeal.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.weeklymeal.weeklymeal.entity.User;
import com.weeklymeal.weeklymeal.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	UserRepository userRepository;
	
	//Finds a user via id
	public User findById(Long id) {
		return userRepository.findById(id).get();
	}
	
	//Get all the users from the database
	public List<User> getAllUsers(){
		return userRepository.findAll();
	}
	
	//Create a new user
	public ResponseEntity<User> createUser(User user){
		userRepository.save(user);
		return ResponseEntity.status(HttpStatus.CREATED).body(user);
	}
	
	//Delete a user via id
	public String deleteUser(Long id) {
		userRepository.deleteById(id);
		return "Usuario eliminado";
	}
	
	//Update the user
	public ResponseEntity<User> updateUser(Long id, User newUser){
		Optional<User> optionalUser = userRepository.findById(id);
		
		if(optionalUser.isPresent()) {
			User existingUser = optionalUser.get();
			existingUser.setUserName(newUser.getUserName());
			existingUser.setEmail(newUser.getEmail());
			existingUser.setPassword(newUser.getPassword());
			User updatedUser = userRepository.save(existingUser);
			return ResponseEntity.ok(updatedUser);
		}else {
			return ResponseEntity.notFound().build();
		}
	}
}
