package com.example.demo.Notification;

public class NotificationRequest {
    private String phoneNumber; // 전화번호
    private String title;       // 제목
    private String body;        // 본문

    // 기본 생성자 (필수)
    public NotificationRequest() {
    }

    // 모든 필드를 포함하는 생성자
    public NotificationRequest(String phoneNumber, String title, String body) {
        this.phoneNumber = phoneNumber;
        this.title = title;
        this.body = body;
    }

    // Getter 및 Setter
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
