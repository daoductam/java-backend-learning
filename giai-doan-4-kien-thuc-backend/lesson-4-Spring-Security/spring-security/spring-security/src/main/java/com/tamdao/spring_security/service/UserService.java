package com.tamdao.spring_security.service;

import com.tamdao.spring_security.dto.request.UserCreationRequest;
import com.tamdao.spring_security.dto.response.UserResponse;
import com.tamdao.spring_security.dto.request.UserUpdateRequest;

import java.util.List;


public interface UserService {
    UserResponse createUser(UserCreationRequest request);
    UserResponse getMyInfo();
    UserResponse updateUser(String userId, UserUpdateRequest request);
    void deleteUser(String userId);
    List<UserResponse> getUsers();
    UserResponse getUser(String id);

}
