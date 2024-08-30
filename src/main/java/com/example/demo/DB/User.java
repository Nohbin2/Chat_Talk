package com.example.demo.DB;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class User {

    @Id
    private String phoneNumber; // 전화번호를 기본 키로 사용

    private String fcmToken; // FCM 토큰

    // 기본 생성자
    public User() {
    }

    // 전체 필드 생성자
    public User(String phoneNumber, String fcmToken) {
        this.phoneNumber = phoneNumber;
        this.fcmToken = fcmToken;
    }

    // Getters and Setters
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getFcmToken() {
        return fcmToken;
    }

    public void setFcmToken(String fcmToken) {
        this.fcmToken = fcmToken;
    }
}
