package com.dev.cinema.controllers;

import com.dev.cinema.dto.user.UserRequestDto;
import com.dev.cinema.mapper.UserMapper;
import com.dev.cinema.model.User;
import com.dev.cinema.service.AuthenticationService;
import lombok.SneakyThrows;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/register")
public class AuthenticationController {
    private final AuthenticationService authenticationService;
    private final UserMapper userMapper;

    public AuthenticationController(AuthenticationService authenticationService, UserMapper userMapper) {
        this.authenticationService = authenticationService;
        this.userMapper = userMapper;
    }

    @SneakyThrows
    @PostMapping("/")
    public void doUserRegister(@RequestBody UserRequestDto userRequestDto) {
        User user = userMapper.dtoToUser(userRequestDto);
        authenticationService.register(user.getEmail(), user.getPassword());
    }
}
