package com.dev.cinema.controllers;

import com.dev.cinema.model.ShoppingCart;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController("/shopping-carts")
public class ShoppingCartController {

    @PostMapping("/movie-sessions")
    public void addMovieSession(@RequestParam Long userId, @RequestParam Long sessionId){

    }

    @GetMapping
    public ShoppingCart getByUserId(@RequestParam Long userId) {
        return new ShoppingCart();
    }
}
