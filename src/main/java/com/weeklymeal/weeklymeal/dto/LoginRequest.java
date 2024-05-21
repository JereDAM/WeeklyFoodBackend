package com.weeklymeal.weeklymeal.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequest {
	private String userName;
	private String password;
}
