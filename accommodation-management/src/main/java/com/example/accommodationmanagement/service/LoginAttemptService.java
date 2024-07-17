package com.example.accommodationmanagement.service;

import org.springframework.stereotype.Service;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

@Service
public class LoginAttemptService {

    private final int MAX_ATTEMPT = 3;
    private final long LOCK_TIME_DURATION = TimeUnit.MINUTES.toMillis(5);
    private ConcurrentHashMap<String, Integer> attemptsCache = new ConcurrentHashMap<>();
    private ConcurrentHashMap<String, Integer> lockTimeCache = new ConcurrentHashMap<>();

    public void loginSucceeded(String key) {
        attemptsCache.remove(key);
//        lockTimeCache.remove(key);
    }

    public void loginFailed(String key) {
        int attempts = attemptsCache.getOrDefault(key, 0);
        attempts++;
//        if (attempts >= MAX_ATTEMPT) {
//            lockTimeCache.put(key, System.currentTimeMillis());
//        }
        attemptsCache.put(key, attempts);
    }

    public boolean isBlocked(String key) {
//        if (lockTimeCache.containsKey(key)) {
//            long lockTime = lockTimeCache.get(key);
//            if (System.currentTimeMillis() - lockTime > LOCK_TIME_DURATION) {
//                lockTimeCache.remove(key);
//                attemptsCache.remove(key);
//                return false;
//            }
//            return true;
//        }
//        return false;
        return attemptsCache.getOrDefault(key, 0) >= MAX_ATTEMPT;
    }

}