package com.weeklymeal.weeklymeal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.weeklymeal.weeklymeal.dto.LoginRequest;
import com.weeklymeal.weeklymeal.dto.UserDto;
import com.weeklymeal.weeklymeal.dto.UserDtoMapper;
import com.weeklymeal.weeklymeal.entity.User;
import com.weeklymeal.weeklymeal.repository.UserRepository;
import com.weeklymeal.weeklymeal.service.AuthService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/api/v1/auth")
public class LoginController {
	
	@Autowired
	AuthService authService;
	
	@Autowired
	UserRepository userRepository;
	
    @PostMapping("/login")
    public UserDto login(@RequestBody LoginRequest loginRequest, HttpServletRequest request) {
        String userName = loginRequest.getUserName();
        String password = loginRequest.getPassword();

        //Authenticates the user
        if (authService.authenticate(userName, password)) {
            HttpSession session = request.getSession(true);
            session.setAttribute("user", userName);
            
            User user = userRepository.findByUserName(userName);
            return UserDtoMapper.toUserDTO(user);
        } else {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid username or password");
        }
    }
    
    @PostMapping("/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return "Logout successful";
    }
}
