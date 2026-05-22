package com.hyl.rock.gateway.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
public class TokenConfig {

    /**
     * token 密钥
     */
    @Value("${token.secret}")
    private String secret;

    /**
     * token 在请求头的名称
     */
    @Value("${token.headerName}")
    private String headerName;

    /**
     * token 过期时间（秒）
     */
    @Value("${token.expireTime}")
    private Long expireTime;
}
