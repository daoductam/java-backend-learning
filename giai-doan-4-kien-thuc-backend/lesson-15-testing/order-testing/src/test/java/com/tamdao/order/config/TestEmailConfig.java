package com.tamdao.order.config;

import com.tamdao.order.service.EmailService;
import org.mockito.Mockito;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class TestEmailConfig {

    @Bean
    public EmailService emailService() {
        return Mockito.mock(EmailService.class);
    }
}
