package com.example.demo;

import com.example.demo.Fcm.FcmService;
import com.example.demo.controller.AuthService;
import com.example.demo.controller.MessagingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.example.demo"})
public class DemoApplication {

	@Autowired
	private AuthService authService;

	@Autowired
	private MessagingService messagingService;

	@Autowired
	private FcmService fcmService;

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
}
