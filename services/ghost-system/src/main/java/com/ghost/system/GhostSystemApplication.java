package com.ghost.system;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author yaolsœ
 * @version 1.0
 * @since 2025/6/2 05:41
 */
@SpringBootApplication
@EnableDiscoveryClient
public class GhostSystemApplication {
    public static void main(String[] args) {
        SpringApplication.run(GhostSystemApplication.class, args);
    }
}
