package com.ganaptayeTradBot.auth;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KotakNeoAuthConfig {

    @Bean
    public KotakNeoAuthService kotakNeoAuthService() {
        return new KotakNeoAuthService();
    }

    @Bean
    public KotakNeoAuthUtils kotakNeoAuthUtils() {
        return new KotakNeoAuthUtils();
    }
}
