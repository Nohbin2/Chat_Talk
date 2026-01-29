package com.example.demo.controller;

import com.example.demo.controller.MessageRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1")
public class MessageController {

    @GetMapping("/msgRev")
    public String receiveMessage(@RequestParam List<String> userId, // List<String>를 RequestParam으로 처리
                                 @RequestParam String msg) { // msg는 단일 문자열
        System.out.println("Received message: " + msg + " for users: " + userId);
        return "success";
    }

    @Autowired
    private MessagingService messagingService;

    @PostMapping("/msgSend")
    public String sendMessage(@RequestBody MessageRequest request) {
        System.out.println("Sending message: " + request.getMsg() + " to users: " + request.getUserId());

        return "success";

    }

    @GetMapping("/register")
    public String registerAPI(@RequestBody String token) {
        System.out.println(token);
        return "success";
    }
}
