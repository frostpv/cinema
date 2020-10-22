package com.dev.cinema.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movies")
public class MovieController {

    @PostMapping("/")
    public void addMovie() {

    }

    @GetMapping("/")
    public void getMovie() {

    }
}
