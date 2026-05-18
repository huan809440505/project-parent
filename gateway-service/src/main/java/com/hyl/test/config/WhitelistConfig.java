package com.hyl.test.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * 白名单配置
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "whitelist.path")
public class WhitelistConfig {

    private List<String> include;

}
