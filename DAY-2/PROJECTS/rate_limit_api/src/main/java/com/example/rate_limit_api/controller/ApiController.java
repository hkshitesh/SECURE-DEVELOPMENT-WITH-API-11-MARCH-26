package com.example.rate_limit_api.controller;
import com.example.rate_limit_api.service.RateLimiterService;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/api")
public class ApiController {

    private final RateLimiterService rateLimiter;

    public ApiController(RateLimiterService rateLimiter) {
        this.rateLimiter = rateLimiter;
    }

    @GetMapping("/data")
    public String getData(HttpServletRequest request) {
        String clientIp = request.getRemoteAddr();

        if (!rateLimiter.allowRequest(clientIp)) {
            return "Rate limit exceeded. Maximum 10 requests per minute allowed.";
        }
        return "Secure API response";
    }
}
