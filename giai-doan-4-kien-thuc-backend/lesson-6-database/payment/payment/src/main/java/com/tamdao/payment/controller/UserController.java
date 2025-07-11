package com.tamdao.payment.controller;

import com.tamdao.payment.dto.BaseResponse;
import com.tamdao.payment.dto.GetUserBalanceRequest;
import com.tamdao.payment.dto.GetUserBalanceResponse;
import com.tamdao.payment.service.UserService;
import io.micrometer.core.annotation.Timed;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Timed(histogram = true)
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @GetMapping("/{id}/balance")
    public ResponseEntity<BaseResponse<GetUserBalanceResponse>> getUserBalance(@PathVariable Integer id) {
        final GetUserBalanceResponse registrationResponse = userService
                .getUserBalance(GetUserBalanceRequest.builder().userId(id).build());
        return ResponseEntity.status(HttpStatus.OK).body(
                BaseResponse.<GetUserBalanceResponse>builder()
                        .message("Lay du lieu thanh cong")
                        .data(registrationResponse).build()
                );
    }
}
