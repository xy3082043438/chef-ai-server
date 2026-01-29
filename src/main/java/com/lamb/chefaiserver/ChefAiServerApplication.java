package com.lamb.chefaiserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 应用入口：Chef AI 服务端。
 */
@SpringBootApplication
public class ChefAiServerApplication {

    /**
     * 启动 Spring Boot。
     *
     * @param args 启动参数
     */
    public static void main(String[] args) {
        SpringApplication.run(ChefAiServerApplication.class, args);
    }

}
