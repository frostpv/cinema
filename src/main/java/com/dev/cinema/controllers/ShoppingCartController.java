package com.dev.cinema.controllers;

import com.dev.cinema.dto.shoppingcart.ShoppingCartResponseDto;
import com.dev.cinema.mapper.ShoppingCartMapper;
import com.dev.cinema.service.MovieSessionService;
import com.dev.cinema.service.ShoppingCartService;
import com.dev.cinema.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController("/shopping-carts")
public class ShoppingCartController {
    private final ShoppingCartMapper shoppingCartMapper;
    private final MovieSessionService movieSessionService;
    private final UserService userService;
    private final ShoppingCartService shoppingCartService;

    public ShoppingCartController(ShoppingCartMapper shoppingCartMapper,
                                  MovieSessionService movieSessionService,
                                  UserService userService,
                                  ShoppingCartService shoppingCartService) {
        this.shoppingCartMapper = shoppingCartMapper;
        this.movieSessionService = movieSessionService;
        this.userService = userService;
        this.shoppingCartService = shoppingCartService;
    }

    @PostMapping("/movie-sessions")
    public void addMovieSession(@RequestParam Long userId, @RequestParam Long sessionId) {
        shoppingCartService.addSession(movieSessionService.findById(sessionId),
                userService.findById(userId));
    }

    @GetMapping
    public ShoppingCartResponseDto getByUserId(@RequestParam Long userId) {
        return shoppingCartMapper
                .shoppingCartToDto(shoppingCartService.getByUser(userService.findById(userId)));
    }
}
