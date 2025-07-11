package com.tamdao.payment.service;

import com.tamdao.payment.dto.GetUserBalanceRequest;
import com.tamdao.payment.dto.GetUserBalanceResponse;
import com.tamdao.payment.entity.User;
import com.tamdao.payment.exception.AppException;
import com.tamdao.payment.exception.ErrorCode;
import com.tamdao.payment.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public GetUserBalanceResponse getUserBalance(GetUserBalanceRequest request) {
        final User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new AppException(ErrorCode.UserNotFoundException));
        return GetUserBalanceResponse.builder().balance(user.getBalance()).build();
    }
}
