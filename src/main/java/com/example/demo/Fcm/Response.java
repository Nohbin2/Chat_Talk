package com.example.demo.Fcm;

public class Response {
    private String result;

    // 기본 생성자
    public Response() {
    }

    // 생성자
    public Response(String result) {
        this.result = result;
    }

    // Getter 및 Setter
    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
