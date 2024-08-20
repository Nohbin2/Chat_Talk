package com.example.demo.controller;

import java.util.List;

public class MessageRequest {
    private List<String> userId;
    private String msg;

    public List<String> getUserId() {
        return userId;
    }

    public void setUserId(List<String> userId) {
        this.userId = userId;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}