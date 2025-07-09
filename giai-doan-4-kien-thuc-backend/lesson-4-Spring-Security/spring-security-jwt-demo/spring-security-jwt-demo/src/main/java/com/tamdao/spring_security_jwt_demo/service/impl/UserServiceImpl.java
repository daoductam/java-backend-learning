package com.tamdao.spring_security_jwt_demo.service.impl;

import com.tamdao.spring_security_jwt_demo.dto.UserRequest;
import com.tamdao.spring_security_jwt_demo.entity.User;
import com.tamdao.spring_security_jwt_demo.repository.UserRepository;
import com.tamdao.spring_security_jwt_demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService
{
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    @Override
    public void createUser(UserRequest request) {
        if (userRepository.findByUsername(request.getUsername()).isPresent()) {
            throw new RuntimeException("Username already exists");
        }

        User newUser = new User();
        newUser.setUsername(request.getUsername());
        newUser.setPassword(passwordEncoder.encode(request.getPassword()));
        newUser.setRoles(List.of("USER"));

        userRepository.save(newUser);
    }
}
