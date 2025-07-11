package com.tamdao.payment;

import com.tamdao.payment.dto.CreateTransactionRequest;
import com.tamdao.payment.dto.CreateTransactionResponse;
import com.tamdao.payment.dto.GetUserBalanceRequest;
import com.tamdao.payment.dto.GetUserBalanceResponse;
import com.tamdao.payment.entity.User;
import com.tamdao.payment.repository.UserRepository;
import com.tamdao.payment.service.TransactionService;
import com.tamdao.payment.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@Slf4j
@SpringBootApplication
public class PaymentApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(PaymentApplication.class, args);

		UserRepository userRepository = context.getBean(UserRepository.class);
		UserService userService = context.getBean(UserService.class);
		TransactionService transactionService = context.getBean(TransactionService.class);

		User user1 = userRepository.save(User.builder().balance(100).build());

		GetUserBalanceResponse getUserBalanceResponse = userService
				.getUserBalance(GetUserBalanceRequest.builder().userId(user1.getId()).build());
		log.info("GetUserBalanceResponse={}", getUserBalanceResponse);

		CreateTransactionResponse createTransactionResponse1 = transactionService
				.createTransaction(CreateTransactionRequest.builder().userId(user1.getId()).idempotentKey("abc").amount(1).build());
		log.info("CreateTransactionResponse1={}", createTransactionResponse1);
		CreateTransactionResponse createTransactionResponse2 = transactionService
				.createTransaction(CreateTransactionRequest.builder().userId(user1.getId()).idempotentKey("abc1").amount(100).build());
		log.info("CreateTransactionResponse2={}", createTransactionResponse2);
	}

}
