package com.example.demo.Notification;
import com.example.demo.DB.User;
import com.example.demo.DB.UserRepository;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {
    @Autowired
    private UserRepository userRepository;
    // 메서드 정의
    public void sendNotification(String phoneNumber, String title, String body) throws Exception {
        // FCM 토큰을 가져오는 로직
        User user = userRepository.findById(phoneNumber).orElseThrow(() -> new Exception("User not found"));
        String token = user.getFcmToken();

        // Notification 객체를 Builder를 통해 생성
        Notification notification = Notification.builder()
                .setTitle(title)
                .setBody(body)
                .build();

        // Message 객체에 Notification 객체를 설정
        Message message = Message.builder()
                .setToken(token) // 실제 FCM 토큰을 사용해야 합니다.
                .setNotification(notification)
                .build();

        // FirebaseMessaging을 사용하여 메시지 전송
        String response = FirebaseMessaging.getInstance().send(message);
        System.out.println("Successfully sent message: " + response);
    }
}
