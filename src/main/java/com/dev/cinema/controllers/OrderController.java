package com.dev.cinema.controllers;

import com.dev.cinema.model.Order;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @PostMapping("/complete")
    public void completeOrder() {

    }

    @GetMapping
    public List<Order> getUserOrder(@RequestParam Long userId){
        return new ArrayList<>();
    }

}
