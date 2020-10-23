package com.dev.cinema.controllers;

import com.dev.cinema.dto.user.UserRequestDto;
import com.dev.cinema.mapper.UserMapper;
import com.dev.cinema.service.AuthenticationService;
import lombok.SneakyThrows;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {
    private final AuthenticationService authenticationService;
    private final UserMapper userMapper;

    public AuthenticationController(AuthenticationService service, UserMapper mapper) {
        this.authenticationService = service;
        this.userMapper = mapper;
    }

    @SneakyThrows
    @PostMapping("/registration")
    public void doUserRegister(@RequestBody UserRequestDto userRequestDto) {
        authenticationService.register(userRequestDto.getEmail(), userRequestDto.getPassword());
    }
}
