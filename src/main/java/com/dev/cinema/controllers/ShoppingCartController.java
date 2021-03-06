package com.dev.cinema.controllers;

import com.dev.cinema.dto.shoppingcart.ShoppingCartResponseDto;
import com.dev.cinema.mapper.ShoppingCartMapper;
import com.dev.cinema.service.MovieSessionService;
import com.dev.cinema.service.ShoppingCartService;
import com.dev.cinema.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/shopping-carts")
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
    public void addMovieSession(Authentication auth, @RequestParam Long sessionId) {
        shoppingCartService.addSession(movieSessionService.findById(sessionId),
                userService.findByEmail(auth.getName()).get());
    }

    @GetMapping
    public ShoppingCartResponseDto getByUserId(Authentication auth) {
        return shoppingCartMapper
                .shoppingCartToDto(shoppingCartService
                        .getByUser(userService.findByEmail(auth.getName()).get()));
    }
}
