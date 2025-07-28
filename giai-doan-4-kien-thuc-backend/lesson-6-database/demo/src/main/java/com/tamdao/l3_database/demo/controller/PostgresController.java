package com.tamdao.l3_database.demo.controller;

import com.tamdao.l3_database.demo.dto.OrderDto;
import com.tamdao.l3_database.demo.dto.UserDto;
import com.tamdao.l3_database.demo.entity.Order;
import com.tamdao.l3_database.demo.entity.User;
import com.tamdao.l3_database.demo.service.OrderService;
import com.tamdao.l3_database.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class PostgresController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private UserService userService;

    @PostMapping("/users")
    public User createUser(@RequestBody UserDto request) {
        return userService.createUser(request);
    }

    @PostMapping("/orders")
    public Order createOrder(@RequestBody OrderDto order) {
        return orderService.createOrder(order);
    }

}
