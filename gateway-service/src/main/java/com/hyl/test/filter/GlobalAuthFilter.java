package com.hyl.test.filter;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import reactor.core.publisher.Mono;

@Configurable
public class GlobalAuthFilter {

    /**
     * 自定义全局认证过滤器（Order 越小，执行优先级越高）
     */
    @Bean
    @Order(-100) // 优先级高于默认过滤器（默认 0）
    public GlobalFilter authFilter() {
        return (exchange, chain) -> {
            // 1. 获取请求头中的 Token
            String token = exchange.getRequest().getHeaders().getFirst("X-Token");

            // 2. 校验 Token（实际场景：对接 Redis/鉴权服务校验）
            if (token == null || token.isEmpty()) {
                // 3. Token 无效：返回 401 未授权
                exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                return exchange.getResponse().setComplete();
            }

            // 4. Token 有效：继续转发请求到下游服务
            return chain.filter(exchange);
        };
    }

    /**
     * 自定义限流键解析器（IP 限流）
     */
    @Bean
    public KeyResolver ipKeyResolver() {
        return exchange -> Mono.just(
                // 获取客户端 IP 地址作为限流键
                exchange.getRequest().getRemoteAddress().getAddress().getHostAddress()
        );
    }



}
