package com.hyl.rock.config;

import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig {

    private static final Logger log = LoggerFactory.getLogger(RedisConfig.class);

    @PostConstruct
    public void postConstruct() {
        log.debug("[Herodotus] |- Module [Cache Redis] Configure.");
    }

    /**
     * 重新配置一个 redisTemplate
     * @param redisConnectionFactory
     * @return
     */
    @Bean(name = "redisTemplate")
    @ConditionalOnMissingBean
    public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<Object, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        redisTemplate.setKeySerializer(keySerializer());
        redisTemplate.setHashKeySerializer(keySerializer());
        redisTemplate.setValueSerializer(valueSerializer());
        redisTemplate.setHashValueSerializer(valueSerializer());
        redisTemplate.setDefaultSerializer(valueSerializer());
        redisTemplate.afterPropertiesSet();

        log.trace("[Herodotus] |- Bean [Redis Template] Configure.");

        return redisTemplate;
    }

    private RedisSerializer<String> keySerializer() {
        RedisSerializer<String> redisSerializer = new StringRedisSerializer();
        log.trace("[Herodotus] |- Bean [redisSerializer Redis keySerializer] Configure.");
        return redisSerializer;
    }

    private RedisSerializer<Object> valueSerializer() {
        RedisSerializer<Object> redisSerializer = new Jackson2JsonRedisSerializer<>(Object.class);
        log.trace("[Herodotus] |- Bean [Jackson2Json Redis valueSerializer] Configure.");
        return redisSerializer;
    }
}
