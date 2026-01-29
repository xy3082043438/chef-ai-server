package com.lamb.chefaiserver.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JacksonConfig {

    /**
     * 提供统一的 ObjectMapper（JSON 序列化/反序列化）。
     *
     * @return ObjectMapper 实例
     */
    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }
}
