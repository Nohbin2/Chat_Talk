package com.example.demo.Fcm;

import com.example.demo.DB.UserRepository;
import org.springframework.core.io.PathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

@RestController
@RequestMapping("/v1")
public class FcmRegister {

    private final FcmService fcmService;
    private UserRepository userRepository;

    public FcmRegister(FcmService fcmService) {
        this.fcmService = fcmService;
    }

    @PostMapping("/register")
    public ResponseEntity<Response> sendFcm(@RequestBody FcmRequest fcmRequest) {
        try {
            System.out.println("token :" + fcmRequest.getFcmToken());

            // 기본 프로필 이미지 URL 설정
            String defaultImageUrl = "https://aca5-211-193-229-239.ngrok-free.app/v1/images/default.jpg";

            // 기본 프로필 이미지 URL과 함께 사용자 정보 저장
            fcmService.saveFcmToken(fcmRequest.getPhoneNumber(), fcmRequest.getFcmToken(), fcmRequest.getName(), defaultImageUrl);

            // JSON 응답 반환
            return ResponseEntity.ok(new Response("회원가입 성공"));
        } catch (Exception e) {
            e.printStackTrace();
            // 예외 발생 시 JSON 응답 반환
            return ResponseEntity.status(500).body(new Response("오류"));
        }
    }

    @PostMapping("/send")
    public ResponseEntity<Response> sendNotification(@RequestBody NotificationRequest notificationRequest) {
        try {
            // 알림 전송
            fcmService.sendNotification(notificationRequest.getPhoneNumber(), notificationRequest.getTitle(), notificationRequest.getBody());
            return ResponseEntity.ok(new Response("알림 전송"));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body(new Response("error"));
        }
    }

    // 전화번호 가입 여부 확인 및 사용자 정보 반환
    @PostMapping("/checkNumbers")
    public ResponseEntity<List<UserResponse>> checkMultipleRegistrations(@RequestBody FcmRequest fcmRequest) {
        try {
            List<String> phoneNumbers = fcmRequest.getPhoneNumbers();
            // 사용자 정보를 반환하는 메서드 호출
            List<UserResponse> userInfos = fcmService.getUserInfoByPhoneNumbers(phoneNumbers);
            return ResponseEntity.ok(userInfos);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).build();
        }
    }

    // 프로필 이미지 URL 업데이트
    @PostMapping("/updateImage/{phoneNumber}")
    public ResponseEntity<Response> updateProfileImage(@PathVariable String phoneNumber, @RequestBody Map<String, String> ProfileUrlRequest) {
        try {
            String newProfileUrl = ProfileUrlRequest.get("ProfileUrl");

            // 서버에서 받은 이미지 URL로 업데이트
            fcmService.updateProfileImageUrl(phoneNumber, newProfileUrl);

            return ResponseEntity.ok(new Response("프로필 이미지 업데이트 성공"));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body(new Response("프로필 이미지 업데이트 오류"));
        }
    }

    @GetMapping("/image/{phoneNumber}")
    public ResponseEntity<Map<String, String>> getImageByPhoneNumber(@PathVariable String phoneNumber) {
        // 전화번호에 대한 이미지 파일 이름을 가져옴
        String imageFileName = Optional.ofNullable(fcmService.getImageUrlByPhoneNumber(phoneNumber))
                .filter(name -> !name.isEmpty())
                .orElse("default.jpg"); // 기본 이미지 파일 이름

        // 이미지 URL 생성
        String profileUrl = imageFileName;

        // JSON 형태로 이미지 URL 응답
        Map<String, String> response = new HashMap<>();
        response.put("profileUrl", profileUrl);

        return ResponseEntity.ok(response);
    }
}
