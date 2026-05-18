package com.hyl.test.util;

import com.hyl.test.config.TokenConfig;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.Date;
import java.util.Map;

@Component
public class JwtUtil {

    private static final Logger log = LoggerFactory.getLogger(JwtUtil.class);
    private static TokenConfig tokenConfig;

    @Autowired
    public void setTokenConfig(TokenConfig tokenConfig) {
        JwtUtil.tokenConfig = tokenConfig;
    }


    //创建一个jwt密钥 加密和解密都需要用这个玩意
    private static SecretKey getKey(){
        return Jwts.SIG.HS256.key()
                .random(new SecureRandom(tokenConfig.getSecret().getBytes(StandardCharsets.UTF_8)))
                .build();
    }

    private static Long getDefaultExpire(){
        return 1000 * tokenConfig.getExpireTime();
    }

    /**
     * 使用默认过期时间（7天），生成一个JWT
     *
     * @param username 用户名
     * @param claims   JWT中的数据
     * @return
     */
    public static String createToken(String username, Map<String, Object> claims) {
        return createToken(username, claims, getDefaultExpire());
    }

    /**
     * 生成token
     *
     * @param username 用户名
     * @param claims   请求体数据
     * @param expire   过期时间 单位：毫秒
     * @return token
     */
    public static String createToken(String username, Map<String, Object> claims, Long expire) {
        JwtBuilder builder = Jwts.builder();
        Date now = new Date();
        // 生成token
        builder.id("rQRk$yN:7%*Bw}A_A-]M~4#;yGa:a_F{") //id 这个可以不填，但是建议填
                .issuer("sys") //签发者
                .claims(claims) //数据
                .subject(username) //主题
                .issuedAt(now) //签发时间
                .expiration(new Date(now.getTime() + expire)) //过期时间
                .signWith(getKey()); //签名方式
        builder.header()
                .add("type", "JWT")
                .add("alg", "HS256");
        return builder.compact();
    }

    /**
     * 解析token
     *
     * @param token jwt token
     * @return Claims
     */
    public static Claims claims(String token) {
        try {
            Jws<Claims> jws = Jwts.parser()
                    .verifyWith(getKey())
                    .build()
                    .parseSignedClaims(token);
            return jws.getPayload();
        } catch (Exception e) {
            if (e instanceof ExpiredJwtException) {
                //现在不需要使用 claims.getExpiration().before(new Date());
                // 判断JWT是否过期了 如果过期会抛出ExpiredJwtException异常
                log.error("token已过期");
            }
            if (e instanceof JwtException) {
                log.error("token已失效" + e);
            }
            log.error("jwt解析失败" + e);
            throw new RuntimeException(e);
        }
    }

    /**
     * 获取jwt头部信息
     *
     * @param token
     * @return
     */
    public static JwsHeader header(String token) {
        try {
            Jws<Claims> jws = Jwts.parser()
                    .verifyWith(getKey())
                    .build()
                    .parseSignedClaims(token);
            return jws.getHeader();
        } catch (Exception e) {
            if (e instanceof ExpiredJwtException) {
                //现在不需要使用 claims.getExpiration().before(new Date());
                // 判断JWT是否过期了 如果过期会抛出ExpiredJwtException异常
                log.error("token已过期");
            }
            if (e instanceof JwtException) {
                log.error("token已失效" + e);
            }
            log.error("jwt解析失败" + e);
            throw new RuntimeException(e);
        }
    }
}
