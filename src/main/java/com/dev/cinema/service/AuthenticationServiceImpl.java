package com.dev.cinema.service;

import com.dev.cinema.exceptions.AuthenticationException;
import com.dev.cinema.model.User;
import com.dev.cinema.util.HashUtil;
import java.util.Optional;
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
    public User login(String email, String password) throws AuthenticationException {
        Optional<User> userFromDb = userService.findByEmail(email);
        if (userFromDb.isPresent()
                && isValid(userFromDb.get().getPassword(), password, userFromDb.get().getSalt())) {
            return userFromDb.get();
        }
        throw new AuthenticationException("Incorrect email " + email + " or password");
    }

    @Override
    public User register(String email, String password) {
        User user = new User(email, password);
        User userFromDb = userService.add(user);
        shoppingCartService.registerNewShoppingCart(userFromDb);
        return userFromDb;
    }

    private boolean isValid(String userPassword, String password, byte[] salt) {
        return userPassword.equals(HashUtil.hashPassword(password, salt));
    }
}

