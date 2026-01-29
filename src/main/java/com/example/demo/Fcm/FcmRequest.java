package com.example.demo.Fcm;

import java.util.List;

public class FcmRequest {
    private String phoneNumber;
    private String fcmToken;
    private String name;
    private List<String> phoneNumbers;
    private String imageUrl;

    // 기본 생성자 (필수)
    public FcmRequest() {
    }

    // 전체 필드 생성자
    public FcmRequest(String phoneNumber, String fcmToken, String name) {
        this.phoneNumber = phoneNumber;
        this.fcmToken = fcmToken;
        this.name = name;
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
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }

    public List<String> getPhoneNumbers() {
        return phoneNumbers;
    }

    public void setPhoneNumbers(List<String> phoneNumbers) {
        this.phoneNumbers = phoneNumbers;
    }
    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
