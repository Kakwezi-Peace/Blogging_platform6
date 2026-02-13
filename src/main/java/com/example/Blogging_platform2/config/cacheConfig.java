package com.example.Blogging_platform2.config;
import org.springframework.cache.caffeine.CaffeineCacheManager;

import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

@Configuration
@EnableCaching
public class cacheConfig {

    @Bean
    public CacheManager cacheManager() {

        // Define cache names for all your models
        CaffeineCacheManager cacheManager = new CaffeineCacheManager( "users", "reviews", "posts", "comments", "tags","activityLogs","postViews" );
        cacheManager.setCaffeine(
                Caffeine.newBuilder()
                        .recordStats()
                        .expireAfterWrite(10, TimeUnit.MINUTES) // entries expire after 10 minutes
                        .maximumSize(1000) // limit cache size
        );
        return (CacheManager) cacheManager;
    }
}





