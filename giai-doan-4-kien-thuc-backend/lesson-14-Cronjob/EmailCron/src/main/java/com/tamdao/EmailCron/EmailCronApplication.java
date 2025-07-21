package com.tamdao.EmailCron;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class EmailCronApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmailCronApplication.class, args);
	}

}
