package com.dev.cinema.mapper;

import com.dev.cinema.dto.user.UserRequestDto;
import com.dev.cinema.dto.user.UserResponseDto;
import com.dev.cinema.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public UserResponseDto userToDto(User user) {
        return new UserResponseDto(user.getId(), user.getEmail());
    }

    public User DtoToUser(UserRequestDto userRequestDto){
        User user = new User();
        user.setEmail(userRequestDto.getEmail());
        user.setPassword(userRequestDto.getPassword());
        return user;
    }
}
