package com.tamdao.SecurityDemo.controller;

import com.tamdao.SecurityDemo.dto.UserCreateRequest;
import com.tamdao.SecurityDemo.dto.UserLoginRequest;
import com.tamdao.SecurityDemo.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@Slf4j

public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("/login-insecure")
    public ResponseEntity<String> loginInsecure(@RequestBody UserLoginRequest request) {
        boolean result = authService.loginInsecure(request.getUsername(),  request.getPassword());
        return result ? ResponseEntity.ok("LOGIN THANH CONG") :
                ResponseEntity.status(401).body("LOGIN THAT BAI");
    }

    @PostMapping("/login-secure")
    public ResponseEntity<String> loginSecure(@RequestBody UserLoginRequest request) {
        boolean result = authService.loginSecure(request.getUsername(),  request.getPassword());
        return result ? ResponseEntity.ok("LOGIN THANH CONG") :
                ResponseEntity.status(401).body("LOGIN THAT BAI");
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody UserCreateRequest request) {
        try {
            String result = authService.register(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(result);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }
}
