package com.tamdao.spring_security_2.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class WelcomeController {

    @GetMapping
    public Object sayHello(Authentication authentication) {
        return authentication.getPrincipal();
    }

    @GetMapping("/user")
    @PreAuthorize("hasRole('ROLE_USER')")
    public Object getPrincipal(Authentication authentication) {
        return authentication.getPrincipal();
    }
}
