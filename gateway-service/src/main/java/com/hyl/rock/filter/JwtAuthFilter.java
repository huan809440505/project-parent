package com.hyl.rock.filter;

import com.hyl.rock.config.TokenConfig;
import com.hyl.rock.config.WhitelistConfig;
import com.hyl.rock.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import jakarta.annotation.Resource;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class JwtAuthFilter implements GlobalFilter, Ordered {

    @Resource
    private TokenConfig tokenConfig;
    @Resource
    private WhitelistConfig whitelistConfig;


    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        // 获取请求对象和响应对象
        ServerHttpRequest request = exchange.getRequest();
        ServerHttpResponse response = exchange.getResponse();
        String requestPath = request.getPath().value(); // 获取请求路径（如/api/user/login）
        // 判断请求是否在“无需认证的路径”中，若是则直接放行
        if (isIgnorePath(requestPath)) {
            return chain.filter(exchange);
        }
        // 提取请求头中的JWT token
        String token = request.getHeaders().getFirst(tokenConfig.getHeaderName());
        if (token == null || !token.isEmpty()) {
            // 无token或格式错误，返回401（未授权）
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
            return response.setComplete();
        }

        // 验证并解析token中的用户信息，放入请求头传递给下游微服务
        Claims claims = JwtUtils.parseToken(token);
        // 构建新的请求对象（Gateway请求头不可直接修改，需通过mutate()创建新对象）
        ServerHttpRequest newRequest = request.mutate()
                .header("X-User-Id", claims.get("userId").toString()) // 传递用户ID
                .header("X-User-Name", claims.get("username").toString()) // 传递用户名
                .header("X-User-Role", claims.get("role").toString()) // 传递用户角色
                .build();
        // 替换请求对象，继续转发到下游服务
        return chain.filter(exchange.mutate().request(newRequest).build());
    }

    /**
     * 判断请求路径是否在白名单的列表中
     */
    private boolean isIgnorePath(String requestPath) {
        return whitelistConfig.getInclude().contains(requestPath);
    }

    /**
     * 设置过滤器优先级（值越小，优先级越高，确保认证过滤器先执行）
     */
    @Override
    public int getOrder() {
        return -100;
    }
}
