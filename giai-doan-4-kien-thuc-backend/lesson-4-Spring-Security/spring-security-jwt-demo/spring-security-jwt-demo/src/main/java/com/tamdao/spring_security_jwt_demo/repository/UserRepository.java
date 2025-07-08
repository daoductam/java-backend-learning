package com.tamdao.spring_security_jwt_demo.repository;

import com.tamdao.spring_security_jwt_demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository  extends JpaRepository<User , Long> {
    Optional<User> findByUsername(String username);


}
