package com.ghost.support.register;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author yaolsœ
 * @version 1.0
 * @since 2025/6/2 05:33
 */

@SpringBootApplication
@EnableEurekaServer
public class GhostEurekaServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(GhostEurekaServerApplication.class,args);
    }
}