package com.bindu.apigateway.controller;

import com.bindu.apigateway.service.RateLimiterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class TestController {

    @Autowired
    private RateLimiterService rateLimiterService;

    @GetMapping("/data")
    public ResponseEntity<String> getData(@RequestHeader("user") String user) {

        if (!rateLimiterService.allowRequest(user)) {
            return ResponseEntity.status(429).body("Too Many Requests ❌");
        }

        return ResponseEntity.ok("Success ✅");
    }
}