package com.dev.cinema.service;

import com.dev.cinema.model.User;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserService userService;
    private final ShoppingCartService shoppingCartService;

    public AuthenticationServiceImpl(UserService userService, ShoppingCartService cartService) {
        this.userService = userService;
        this.shoppingCartService = cartService;
    }

    @Override
    public User register(String email, String password) {
        User user = new User(email, password);
        User userFromDb = userService.add(user);
        shoppingCartService.registerNewShoppingCart(userFromDb);
        return userFromDb;
    }
}

