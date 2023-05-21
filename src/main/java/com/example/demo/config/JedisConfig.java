package com.example.demo.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Jedis 连接配置
 *
 * @author Baiyun
 * @version V1.0
 * @since 2021-10-18 11:18
 */
@Data
@Component
@ConfigurationProperties(prefix = "spring.data.redis")
public class JedisConfig {
    private String host;
    private int port;
    private String password;
    private int database;
    private int timeout;
    private int maxActive;
    private int maxIdle;
    private int minIdle;
    private int maxWait;
    private int maxTotal;
}
