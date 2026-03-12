package com.example.rate_limit_api.service;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Service;

@Service
public class RateLimiterService {

    private static final int MAX_REQUESTS_PER_MINUTE = 10;
    private static final long TIME_WINDOW = 60000; // 1 minute

    private ConcurrentHashMap<String, Integer> requestCounts = new ConcurrentHashMap<>();
    private ConcurrentHashMap<String, Long> windowStart = new ConcurrentHashMap<>();

    public synchronized boolean allowRequest(String ip) {

        long currentTime = System.currentTimeMillis();

        windowStart.putIfAbsent(ip, currentTime);
        requestCounts.putIfAbsent(ip, 0);

        long startTime = windowStart.get(ip);

        if ((currentTime - startTime) < TIME_WINDOW) {

            int count = requestCounts.get(ip) + 1;
            requestCounts.put(ip, count);

            if (count > MAX_REQUESTS_PER_MINUTE) {
                return false;
            }

        } else {

            windowStart.put(ip, currentTime);
            requestCounts.put(ip, 1);
        }

        return true;
    }
}
