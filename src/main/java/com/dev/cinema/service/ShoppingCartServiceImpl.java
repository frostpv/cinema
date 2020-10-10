package com.dev.cinema.service;

import com.dev.cinema.lib.Service;
import com.dev.cinema.model.MovieSession;
import com.dev.cinema.model.ShoppingCart;
import com.dev.cinema.model.User;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {
    @Override
    public void addSession(MovieSession movieSession, User user) {

    }

    @Override
    public ShoppingCart getByUser(User user) {
        return null;
    }

    @Override
    public void registerNewShoppingCart(User user) {

    }

    @Override
    public void clear(ShoppingCart shoppingCart) {

    }
}
