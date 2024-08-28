package com.example.demo.Fcm;

public class NotificationRequest {
    private String token;
    private String title;
    private String body;

    // 기본 생성자 (필수)
    public NotificationRequest() {
    }

    // 모든 필드를 포함하는 생성자
    public NotificationRequest(String token, String title, String body) {
        this.token = token;
        this.title = title;
        this.body = body;
    }

    // Getter 메서드
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
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

