package com.example.demo;

import com.example.demo.controller.AuthService;
import com.example.demo.controller.MessagingService;
import com.google.firebase.auth.FirebaseAuth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
public class DemoApplication implements CommandLineRunner {

	@Autowired
	private AuthService authService;

	@Autowired
	private MessagingService messagingService;

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// 사용자 생성 예제
		String result = authService.createUser("osh0tsix@gmail.com", "Fpdlqms576#");
		System.out.println(result);

		// 메시지 전송 예제
		String token = "fM6fD4lnRW6tmntdTZy4ot:APA91bF_tS3z_Oz8iqTUPSJXIkYqJiH_1IricHxQ9Qaffd6BqkU0TUlS-Ywti7f9Pvjnfi4yCLh2R1uDTsoUexap0WzeB_jNq1oqSSWan-lUmPWoy1c862JBw87xWj3hSvpO6tSIYY6z";
		String response = messagingService.sendNotification("Hello", "World", token);
		System.out.println(response);
	}
}
