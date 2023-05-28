package autoconfigure;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import config.RedisConfig;
import jakarta.annotation.Resource;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;

/**
 * Redis 配置类
 *
 * @author Baiyun
 * @version V1.0
 * @since 2021-04-21 17:32
 */
@Configuration
@EnableCaching
@DependsOn({"redisConfig"})
public class RedisConfiguration extends CachingConfigurerSupport {
    @Resource
    private RedisConfig redisConfig;

    @Bean
    @SuppressWarnings("all")
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory factory) {
        RedisTemplate<String, Object> template = new RedisTemplate<String, Object>();
        template.setConnectionFactory(factory);
        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(om);
        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
        // key采用String的序列化方式
        template.setKeySerializer(stringRedisSerializer);
        // hash的key也采用String的序列化方式
        template.setHashKeySerializer(stringRedisSerializer);
        // value序列化方式采用jackson
        template.setValueSerializer(jackson2JsonRedisSerializer);
        // hash的value序列化方式采用jackson
        template.setHashValueSerializer(jackson2JsonRedisSerializer);
        template.afterPropertiesSet();
        return template;
    }

    /**
     * 连接池配置信息
     */
    /*
    @Bean
    public JedisPoolConfig jedisPoolConfig() {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        // 当池内没有可用连接时，最大等待时间
        jedisPoolConfig.setMaxWaitMillis(jedisConfig.getMaxWait());
        // 最大空闲连接数
        jedisPoolConfig.setMinIdle(jedisConfig.getMaxIdle());
        // 最小空闲连接数
        jedisPoolConfig.setMinIdle(jedisConfig.getMinIdle());
        // 其他属性...
        jedisPoolConfig.setTestOnBorrow(false);
        return jedisPoolConfig;
    }*/

    /**
     * Jedis 连接
     *
     * @param
     * @return
     */
    @Bean
    public JedisConnectionFactory jedisConnectionFactory() {
        JedisClientConfiguration jedisClientConfiguration = JedisClientConfiguration.builder().usePooling()
                .and().readTimeout(Duration.ofMillis(redisConfig.getTimeout())).build();
        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration();
        redisStandaloneConfiguration.setHostName(redisConfig.getHost());
        redisStandaloneConfiguration.setPort(redisConfig.getPort());
        redisStandaloneConfiguration.setDatabase(redisConfig.getDatabase());
        redisStandaloneConfiguration.setPassword(RedisPassword.of(redisConfig.getPassword()));
        return new JedisConnectionFactory(redisStandaloneConfiguration, jedisClientConfiguration);
    }

    @Bean
    public CacheManager cacheManager(JedisConnectionFactory jedisConnectionFactory){
        return RedisCacheManager.create(jedisConnectionFactory);
    }
}
