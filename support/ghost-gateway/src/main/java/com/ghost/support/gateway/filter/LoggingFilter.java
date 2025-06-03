package com.ghost.support.gateway.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.net.URI;

@Component
@Slf4j
public class LoggingFilter implements GlobalFilter {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        log.info("Request PATH = {},METHOD = {}, QueryParams = {}", exchange.getRequest().getPath(), exchange.getRequest().getMethod(),exchange.getRequest().getQueryParams());
        return chain.filter(exchange);
    }
}