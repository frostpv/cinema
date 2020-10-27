package com.dev.cinema.controllers;

import com.dev.cinema.dto.user.UserResponseDto;
import com.dev.cinema.mapper.UserMapper;
import com.dev.cinema.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserMapper userMapper;
    private final UserService userService;

    public UserController(UserMapper userMapper, UserService userService) {
        this.userMapper = userMapper;
        this.userService = userService;
    }

    @GetMapping("by-email")
    public UserResponseDto getByEmail(@RequestParam String userEmail) {
        return userMapper.userToDto(userService.findByEmail(userEmail).get());
    }
}
