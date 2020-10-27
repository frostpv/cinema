package com.dev.cinema.mapper;

import com.dev.cinema.dto.user.UserResponseDto;
import com.dev.cinema.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public UserResponseDto userToDto(User user) {
        return new UserResponseDto(user.getId(), user.getEmail());
    }
}
