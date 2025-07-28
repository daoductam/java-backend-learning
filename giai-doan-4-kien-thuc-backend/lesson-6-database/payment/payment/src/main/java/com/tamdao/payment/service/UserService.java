package com.tamdao.payment.service;

import com.tamdao.payment.dto.GetUserBalanceRequest;
import com.tamdao.payment.dto.GetUserBalanceResponse;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    GetUserBalanceResponse getUserBalance(GetUserBalanceRequest request);
}
