package com.example.demo.Fcm;

import com.example.demo.DB.User;
import com.example.demo.DB.UserRepository;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FcmService {

    @Autowired
    private UserRepository userRepository;

    public void saveFcmToken(String phoneNumber, String fcmToken) {
        // 사용자 엔티티 생성 및 저장
        Optional<User> existingUser = userRepository.findByPhoneNumber(phoneNumber);
        if (existingUser.isPresent()) {
            User user = existingUser.get();
            user.setFcmToken(fcmToken);
            userRepository.save(user);
        } else {

            User user = new User(phoneNumber, fcmToken);
            userRepository.save(user);
        }
    }

    public void sendNotification(String phoneNumber, String title, String body) throws Exception {
        // 사용자 전화번호를 통해 FCM 토큰을 조회
        User user = userRepository.findByPhoneNumber(phoneNumber).orElseThrow(() -> new Exception("User not found"));

        String token = user.getFcmToken();

        // Notification 객체를 Builder를 통해 생성
        Notification notification = Notification.builder()
                .setTitle(title)
                .setBody(body)
                .build();

        // Message 객체에 Notification 객체를 설정
        Message message = Message.builder()
                .setToken(token)
                .setNotification(notification)
                .build();

        // FirebaseMessaging을 사용하여 메시지 전송
        String response = FirebaseMessaging.getInstance().send(message);
        System.out.println("Successfully sent message: " + response);
    }
}
