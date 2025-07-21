package com.tamdao.EmailCron.task;

import com.tamdao.EmailCron.entity.User;
import com.tamdao.EmailCron.repository.UserRepository;
import com.tamdao.EmailCron.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Component
public class PromotionTask {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmailService emailService;

//    @Scheduled(cron = "0 0 0 * * *") // 00h hàng ngày
    @Scheduled(cron = "0 */1 * * * *") // TEST: mỗi phút
    public void sendPromotionEmails() {
        try {
            String html = loadHtmlTemplate();
            List<User> users = userRepository.findAllBySubscribedTrue();

            for (User user : users) {
                String personalizedHtml = html.replace("Chào bạn", "Chào " + user.getFullName());
                emailService.sendHtmlEmail(user.getEmail(), "FLASH SALE hôm nay!", personalizedHtml);
                Thread.sleep(500); // chống gmail chặn spam
            }

            System.out.println("Gửi thành công cho " + users.size() + " người dùng.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String loadHtmlTemplate() throws IOException {
        ClassPathResource resource = new ClassPathResource("templates/promotion.html");
        return new String(resource.getInputStream().readAllBytes(), StandardCharsets.UTF_8);
    }

}
