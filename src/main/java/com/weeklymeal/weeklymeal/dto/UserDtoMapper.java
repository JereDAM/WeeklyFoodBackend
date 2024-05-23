package com.weeklymeal.weeklymeal.dto;

import com.weeklymeal.weeklymeal.entity.User;

public class UserDtoMapper {

	public static UserDto toUserDTO(User user) {
        return new UserDto(user.getId(), user.getUserName(), user.getEmail(), user.getRole());
    }

}
