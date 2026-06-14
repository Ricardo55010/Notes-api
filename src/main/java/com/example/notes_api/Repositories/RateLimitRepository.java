package com.example.notes_api.Repositories;

import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class RateLimitRepository {
    private final Map<String, AtomicInteger> requestCountsPerIpAddress = new ConcurrentHashMap<>();

    public static RateLimitRepository rateLimitRepository;


    public Map<String, AtomicInteger> getRequestCountsPerIpAddress() {
        return rateLimitRepository.requestCountsPerIpAddress;
    }

    public static RateLimitRepository getInstance() {
        if (rateLimitRepository == null) {
            rateLimitRepository = new RateLimitRepository();
        }
        return rateLimitRepository;
    }
}
