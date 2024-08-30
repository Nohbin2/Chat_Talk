package com.example.demo.Fcm;

public class FcmRequest {
    private String phoneNumber;
    private String fcmToken;

    // 기본 생성자 (필수)
    public FcmRequest() {
    }

    // 전체 필드 생성자
    public FcmRequest(String phoneNumber, String fcmToken) {
        this.phoneNumber = phoneNumber;
        this.fcmToken = fcmToken;
    }

    // Getter 및 Setter
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
