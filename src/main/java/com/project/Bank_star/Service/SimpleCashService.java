package com.project.Bank_star.Service;

import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Service
public class SimpleCashService {
    private final Map<String, CacheEntry> cache = new ConcurrentHashMap<>();
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    public SimpleCashService() {
        scheduler.scheduleAtFixedRate(this::cleanup, 5, 5, TimeUnit.MINUTES);
    }

    public void put(String key, Object value, long ttlMinutes) {
        cache.put(key, new CacheEntry(value, System.currentTimeMillis() + ttlMinutes * 60 * 1000));
    }

    @SuppressWarnings("unchecked")
    public <T> T get(String key) {
        CacheEntry entry = cache.get(key);
        if (entry != null && !entry.isExpired()) {
            return (T) entry.getValue();
        }
        cache.remove(key);
        return null;
    }

    public boolean contains(String key) {
        CacheEntry entry = cache.get(key);
        if (entry != null && !entry.isExpired()) {
            return true;
        }
        cache.remove(key);
        return false;
    }

    public void remove(String key) {
        cache.remove(key);
    }

    public void clear() {
        cache.clear();
    }

    private void cleanup() {
        long now = System.currentTimeMillis();
        cache.entrySet().removeIf(entry -> entry.getValue().isExpired(now));
    }

    private static class CacheEntry {
        private final Object value;
        private final long expiryTime;

        public CacheEntry(Object value, long expiryTime) {
            this.value = value;
            this.expiryTime = expiryTime;
        }

        public Object getValue() {
            return value;
        }

        public boolean isExpired() {
            return isExpired(System.currentTimeMillis());
        }

        public boolean isExpired(long currentTime) {
            return currentTime > expiryTime;
        }
    }
}