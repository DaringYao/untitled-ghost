package com.ghost.support.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author yaolsœ
 * @version 1.0
 * @since 2025/6/2 04:18
 */

@SpringBootApplication
@EnableDiscoveryClient
public class SupportGatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(SupportGatewayApplication.class, args);
    }
}
