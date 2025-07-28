package com.tamdao.order.service;

import com.tamdao.order.exception.AppException;
import com.tamdao.order.exception.ErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class EmailService {
    @Autowired
    private JavaMailSender mailSender;

    public void sendMailWithRetry(String to, String subject, String content) {
        int maxRetries = 3;
        int attempt = 0;

        while (attempt < maxRetries) {
            try {
                SimpleMailMessage message = new SimpleMailMessage();
                message.setTo(to);
                message.setSubject(subject);
                message.setText(content);
                mailSender.send(message);
//                throw new MailException("Fake failure") {};

                log.info("Email sent successfully!");
                return;
            } catch (MailException e) {
                attempt++;
                log.error("Failed to send email. Attempt {}", attempt);
                if (attempt >= maxRetries) {
                    throw new RuntimeException("Gửi email thất bại sau " + attempt + " lần.");
                }
                try {
                    Thread.sleep(3000);

                } catch (InterruptedException ie) {
                    Thread.currentThread().interrupt();
                }
            }

        }
    }
}
