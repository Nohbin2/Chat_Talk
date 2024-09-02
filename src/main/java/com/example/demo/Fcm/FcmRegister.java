package com.example.demo.Fcm;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1")
public class FcmRegister {

    private final FcmService fcmService;

    public FcmRegister(FcmService fcmService) {
        this.fcmService = fcmService;
    }

    @PostMapping("/register")
    public ResponseEntity<Response> sendFcm(@RequestBody FcmRequest fcmRequest) {
        try {
            System.out.println("token :"+ fcmRequest.getFcmToken() + " " + fcmRequest.getPhoneNumber());
            //DB 저장
            fcmService.saveFcmToken(fcmRequest.getPhoneNumber(), fcmRequest.getFcmToken());
            // JSON 응답을 반환
            return ResponseEntity.ok(new Response("success"));
        } catch (Exception e) {
            e.printStackTrace();
            // 예외 발생 시 JSON 응답을 반환
            return ResponseEntity.status(500).body(new Response("error"));
        }
    }

    @PostMapping("/send")
    public ResponseEntity<Response> sendNotification(@RequestBody NotificationRequest notificationRequest) {
        try {
            // 알림 전송
            fcmService.sendNotification(notificationRequest.getPhoneNumber(), notificationRequest.getTitle(), notificationRequest.getBody());
            return ResponseEntity.ok(new Response("notification sent"));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body(new Response("error"));
        }
    }
}

