package com.tamdao.spring_security.config;

import com.tamdao.spring_security.entity.User;
import com.tamdao.spring_security.enums.Role;
import com.tamdao.spring_security.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;

@Configuration
@Slf4j
public class ApplicationInitConfig {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Bean
    ApplicationRunner applicationRunner(UserRepository userRepository) {
        return args -> {
             if (userRepository.findByUsername("admin").isEmpty()) {
                 var roles = new HashSet<String>();
                 roles.add(Role.ADMIN.name());
                 User user = User.builder()
                         .username("admin")
                         .password(passwordEncoder.encode("admin"))
                         .roles(roles)
                         .build();
                 userRepository.save(user);
                 log.warn("Tao admin voi password mac dinh: admin");
             }
        };
    }
}
