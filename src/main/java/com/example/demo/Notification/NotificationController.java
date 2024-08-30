package com.example.demo.Notification;

import com.example.demo.Fcm.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1")
public class NotificationController {

    private final NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @PostMapping("/send")
    public ResponseEntity<Response> sendNotification(@RequestBody NotificationRequest notificationRequest) {
        System.out.println("Received request with phoneNumber: " + notificationRequest.getPhoneNumber());
        try {
            // 전화번호를 사용하여 알림을 보냅니다.
            notificationService.sendNotification(
                    notificationRequest.getPhoneNumber(),
                    notificationRequest.getTitle(),
                    notificationRequest.getBody()
            );
            // JSON 응답을 반환
            return ResponseEntity.ok(new Response("success"));
        } catch (Exception e) {
            e.printStackTrace();
            // 예외 발생 시 JSON 응답을 반환
            return ResponseEntity.status(500).body(new Response("error"));
        }
    }
}
