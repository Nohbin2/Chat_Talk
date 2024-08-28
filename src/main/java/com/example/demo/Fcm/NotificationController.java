package com.example.demo.Fcm;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1")
public class NotificationController {

    private final FcmService fcmService;

    public NotificationController(FcmService fcmService) {
        this.fcmService = fcmService;
    }

    @PostMapping("/register")
    public ResponseEntity<Response> updateToken(@RequestBody TokenRequest tokenRequest) {
        try {
            // FCM 토큰을 사용하여 알림을 보냅니다.
            fcmService.sendNotification(tokenRequest.getToken());
            // JSON 응답을 반환
            return ResponseEntity.ok(new Response("success"));
        } catch (Exception e) {
            e.printStackTrace();
            // 예외 발생 시 JSON 응답을 반환
            return ResponseEntity.status(500).body(new Response("error"));
        }
    }
}
