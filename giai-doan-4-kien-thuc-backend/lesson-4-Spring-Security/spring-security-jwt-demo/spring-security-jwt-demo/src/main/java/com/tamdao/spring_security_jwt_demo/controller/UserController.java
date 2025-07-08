package com.tamdao.spring_security_jwt_demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class UserController {
    @GetMapping("/user/hello")
    public String userHello() {
        return "Hello User";
    }

    @GetMapping("/admin/hello")
    public String adminHello() {
        return "Hello Admin";
    }
}