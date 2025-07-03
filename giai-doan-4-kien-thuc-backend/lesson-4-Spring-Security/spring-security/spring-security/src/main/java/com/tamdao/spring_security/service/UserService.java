package com.tamdao.spring_security.service;

import com.tamdao.spring_security.dto.UserCreationRequest;
import com.tamdao.spring_security.dto.UserResponse;
import com.tamdao.spring_security.dto.UserUpdateRequest;
import com.tamdao.spring_security.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;


public interface UserService {
    UserResponse createUser(UserCreationRequest request);
    UserResponse updateUser(String userId, UserUpdateRequest request);
    void deleteUser(String userId);
    List<UserResponse> getUsers();
    UserResponse getUser(String id);
}
