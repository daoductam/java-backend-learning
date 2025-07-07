package com.tamdao.spring_security.controller;

import com.tamdao.spring_security.dto.response.BaseResponse;
import com.tamdao.spring_security.dto.request.UserCreationRequest;
import com.tamdao.spring_security.dto.response.UserResponse;
import com.tamdao.spring_security.dto.request.UserUpdateRequest;
import com.tamdao.spring_security.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    ResponseEntity<BaseResponse<List<UserResponse>>> getUsers() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();

        log.info("Username: {}", authentication.getName());
        authentication.getAuthorities().forEach(
                grantedAuthority -> log.info(grantedAuthority.getAuthority()));

        return ResponseEntity.status(HttpStatus.OK).body(
                BaseResponse.<List<UserResponse>>builder()
                        .code(HttpStatus.OK.value())
                        .message("Lấy danh sách user thành công")
                        .data(userService.getUsers())
                        .build()
        );
    }

    @GetMapping("/{userId}")
    ResponseEntity<BaseResponse<UserResponse>> getUser(@PathVariable String userId) {
        return ResponseEntity.status(HttpStatus.OK).body(
                BaseResponse.<UserResponse>builder()
                        .code(HttpStatus.OK.value())
                        .message("Lấy User thành công")
                        .data(userService.getUser(userId))
                        .build()
        ) ;
    }

    @GetMapping("/myInfo")
    ResponseEntity<BaseResponse<UserResponse>> getMyInfo() {
        return ResponseEntity.status(HttpStatus.OK).body(
                BaseResponse.<UserResponse>builder()
                        .code(HttpStatus.OK.value())
                        .message("Lấy User thành công")
                        .data(userService.getMyInfo())
                        .build()
        ) ;
    }

    @PostMapping
    ResponseEntity<BaseResponse<UserResponse>>  createUser(@RequestBody @Valid UserCreationRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                BaseResponse.<UserResponse>builder()
                        .code(HttpStatus.CREATED.value())
                        .message("Tạo User thành công")
                        .data(userService.createUser(request))
                        .build()
        ) ;
    }

    @PutMapping("/{userId}")
    ResponseEntity<BaseResponse<UserResponse>>  updateUser(@PathVariable String userId, @RequestBody UserUpdateRequest request) {
        return ResponseEntity.status(HttpStatus.OK).body(
                BaseResponse.<UserResponse>builder()
                        .code(HttpStatus.OK.value())
                        .message("Cập nhật thành công")
                        .data(userService.updateUser(userId, request))
                        .build()
        ) ;
    }

    @DeleteMapping("/{userId}")
    BaseResponse<String> deleteUser(@PathVariable String userId) {
        userService.deleteUser(userId);
        return  BaseResponse.<String>builder().
                data("User has been deleted")
                .build();
    }
}
