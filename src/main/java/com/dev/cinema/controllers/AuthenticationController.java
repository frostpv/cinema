package com.dev.cinema.controllers;

import com.dev.cinema.dto.user.UserRegistrationDto;
import com.dev.cinema.service.AuthenticationService;
import lombok.SneakyThrows;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService service) {
        this.authenticationService = service;
    }

    @SneakyThrows
    @PostMapping("/registration")
    public void doUserRegister(@RequestBody UserRegistrationDto userRequestDto) {
        authenticationService.register(userRequestDto.getEmail(), userRequestDto.getPassword());
    }
}
