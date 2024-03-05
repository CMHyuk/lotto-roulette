package com.lotto.roulette.backend.command.lotteryhistory.infrastructure.cache;

import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@CacheConfig
@Configuration
public class LotteryHistoryCacheConfig {

    private static final List<String> CACHE_NAMES =
            List.of("topPrize", "lotteryHistory", "LotteryNumberFrequency", "LotteryNumberTopSix");

    @Bean
    public CacheManager cacheManager() {
        SimpleCacheManager cacheManager = new SimpleCacheManager();
        cacheManager.setCaches(createCaches());
        return cacheManager;
    }

    private List<Cache> createCaches() {
        List<Cache> caches = new ArrayList<>();
        for (String cacheName : CACHE_NAMES) {
            caches.add(new ConcurrentMapCache(cacheName));
        }
        return caches;
    }
}
