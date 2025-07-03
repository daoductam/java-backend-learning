package com.tamdao.spring_security.controller;

import com.tamdao.spring_security.dto.BaseResponse;
import com.tamdao.spring_security.dto.UserCreationRequest;
import com.tamdao.spring_security.dto.UserResponse;
import com.tamdao.spring_security.dto.UserUpdateRequest;
import com.tamdao.spring_security.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    BaseResponse<List<UserResponse>> getUsers() {
        return BaseResponse.<List<UserResponse>>builder()
                .data(userService.getUsers())
                .build();
    }

    @GetMapping("/{userId}")
    BaseResponse<UserResponse> getUser(@PathVariable String userId) {
        return BaseResponse.<UserResponse>builder()
                .data(userService.getUser(userId))
                .build();
    }

    @PostMapping
    BaseResponse<UserResponse> createUser(@RequestBody @Valid UserCreationRequest request) {
        return BaseResponse.<UserResponse>builder()
                .data(userService.createUser(request))
                .build();
    }

    @PutMapping("/{userId}")
    BaseResponse<UserResponse> updateUser(@PathVariable String userId, @RequestBody UserUpdateRequest request) {
        return BaseResponse.<UserResponse>builder()
                .data(userService.updateUser(userId, request))
                .build();
    }

    @DeleteMapping("/{userId}")
    BaseResponse<String> deleteUser(@PathVariable String userId) {
        userService.deleteUser(userId);
        return BaseResponse.<String>builder().
                data("User has been deleted")
                .build();
    }
}
