package com.example.demo.Fcm;

import com.example.demo.DB.User;
import com.example.demo.DB.UserRepository;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.ArrayList;
import java.util.List;

@Service
public class FcmService {

    @Autowired
    private UserRepository userRepository;

    // FCM 토큰을 저장하거나 업데이트하는 메서드
    public void saveFcmToken(String phoneNumber, String fcmToken, String name, String imageUrl) {
        // 전화번호를 기준으로 사용자를 조회
        Optional<User> existingUser = userRepository.findByPhoneNumber(phoneNumber);

        if (existingUser.isPresent()) {
            // 사용자가 이미 존재하면 FCM 토큰을 업데이트
            User user = existingUser.get();
            user.setFcmToken(fcmToken);
            userRepository.save(user);
        } else {
            // 사용자가 존재하지 않으면 새로운 사용자 생성
            User user = new User(phoneNumber, fcmToken, name, imageUrl);
            userRepository.save(user);
        }
    }

    // FCM 알림을 전송하는 메서드
    public void sendNotification(String phoneNumber, String title, String body) throws Exception {
        // 사용자 전화번호를 통해 FCM 토큰을 조회
        User user = userRepository.findByPhoneNumber(phoneNumber)
                .orElseThrow(() -> new Exception("Please register first."));

        String token = user.getFcmToken();

        // Notification 객체 생성
        Notification notification = Notification.builder()
                .setTitle(title)
                .setBody(body)
                .build();

        // Message 객체에 Notification 객체 설정
        Message message = Message.builder()
                .setToken(token)
                .setNotification(notification)
                .build();

        // FirebaseMessaging을 사용하여 메시지 전송
        String response = FirebaseMessaging.getInstance().send(message);
        System.out.println("Successfully sent message: " + response);
    }
    public List<Boolean> areUsersRegistered(List<String> phoneNumbers) {
        List<Boolean> result = new ArrayList<>();
        for (String phoneNumber : phoneNumbers) {
            boolean isRegistered = userRepository.findByPhoneNumber(phoneNumber).isPresent();
            result.add(isRegistered);
        }
        return result;
    }
    public String getImageUrlByPhoneNumber(String phoneNumber) {
        Optional<User> user = userRepository.findByPhoneNumber(phoneNumber);

        return user.map(User::getprofileUrl).orElse("https://aca5-211-193-229-239.ngrok-free.app/v1/images/default.jpg");
    }
    // 프로필 이미지 URL 업데이트 메서드
    public void updateProfileImageUrl(String phoneNumber, String newprofileUrl) {
        // 전화번호로 사용자 조회
        Optional<User> optionalUser = userRepository.findByPhoneNumber(phoneNumber);

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            // 새로운 이미지 URL로 업데이트
            user.setprofileUrl(newprofileUrl);
            userRepository.save(user); // 변경사항 저장
        } else {
            // 사용자를 찾지 못하면 예외 발생
            throw new RuntimeException("사용자를 찾을 수 없습니다.");
        }
    }

    // 여러 사용자의 정보 반환 메서드
    public List<UserResponse> getUserInfoByPhoneNumbers(List<String> phoneNumbers) {
        List<UserResponse> userResponses = new ArrayList<>();

        for (String phoneNumber : phoneNumbers) {
            // 전화번호로 사용자 조회
            Optional<User> optionalUser = userRepository.findByPhoneNumber(phoneNumber);

            if (optionalUser.isPresent()) {
                User user = optionalUser.get();
                // 사용자 정보로 UserResponse 객체 생성
                UserResponse userResponse = new UserResponse(
                        user.getName(),
                        user.getPhoneNumber(),
                        user.getprofileUrl()
                );
                userResponses.add(userResponse);
            }
        }
        return userResponses;
    }
}
