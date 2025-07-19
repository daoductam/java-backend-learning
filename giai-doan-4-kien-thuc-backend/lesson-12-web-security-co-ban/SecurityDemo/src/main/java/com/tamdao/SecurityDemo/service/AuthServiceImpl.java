package com.tamdao.SecurityDemo.service;

import com.tamdao.SecurityDemo.dto.UserCreateRequest;
import com.tamdao.SecurityDemo.entity.User;
import com.tamdao.SecurityDemo.repository.AuthRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AuthServiceImpl implements AuthService {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private AuthRepository authRepository;

    @Override
    // SQL Injection
    public boolean loginInsecure(String username, String password) {
        String sql = "SELECT * FROM users WHERE username = '" + username + "' AND password = '" + password + "'";
        Query query = entityManager.createNativeQuery(sql, User.class);
        User user = null;
        try {
            user = (User) query.getSingleResult();
        } catch (Exception e) {
            log.warn("Login failed: {}", e.getMessage());
        }
        log.info("user la: {}", user);
        return user != null;
    }

    @Override
    public boolean loginSecure(String username, String password) {
        User user = authRepository.secureLogin(username, password);
        log.info("user la: {}", user);
        return user != null;
    }

    @Override
    public String register(UserCreateRequest request) {
        if (authRepository.existsByUsername(request.getUsername())) {
            throw  new RuntimeException("usernaem ton tai");
        }

        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(request.getPassword());
        authRepository.save(user);

        return "User dang ky thanh cong";
    }
}
