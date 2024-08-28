package com.example.demo.Fcm;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import org.springframework.stereotype.Service;

@Service
public class FcmService {

    private static final String FIXED_TITLE = "Default Title";
    private static final String FIXED_BODY = "Default Body";

    public void sendNotification(String token) throws Exception {
        // Notification 객체를 Builder를 통해 생성
        Notification notification = Notification.builder()
                .setTitle(FIXED_TITLE)
                .setBody(FIXED_BODY)
                .build();

        // Message 객체에 Notification 객체를 설정
        Message message = Message.builder()
                .setToken(token)
                .setNotification(notification)
                .build();

        // FirebaseMessaging을 사용하여 메시지 전송
        String response = FirebaseMessaging.getInstance().send(message);
        System.out.println("Successfully message: " + response);
    }
}



