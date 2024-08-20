package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TextController {

    @GetMapping("/sendText")
    public String sendText(@RequestParam(value = "message", defaultValue = "Hello, World!") String message) {
        return "Received message: " + message;
    }
}
