//package com.demo.boot.api.config;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.cache.annotation.CachingConfigurerSupport;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import redis.clients.jedis.JedisPool;
//import redis.clients.jedis.JedisPoolConfig;
//
//@Configuration
//@Slf4j
//public class RedisConfig extends CachingConfigurerSupport {
//
//    private static String host;
//
//    private static int port;
//
//    private static String password;
//
//    @Value("${spring.redis.port}")
//    public void setPort(int port) {
//        RedisConfig.port = port;
//    }
//
//    @Value("${spring.redis.host}")
//    public void setHost(String host) {
//        RedisConfig.host = host;
//    }
//
//    @Value("${spring.redis.access-key-secret}")
//    public void setPassword(String password) {
//        RedisConfig.password = password;
//    }
//
//    private static volatile JedisPool jedisPool = null;
//
//    @Bean
//    public static JedisPool redisPoolFactory() {
//        if (jedisPool == null) {
//            synchronized (RedisConfig.class) {
//                if (jedisPool == null) {
//                    JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
//                    jedisPool = new JedisPool(jedisPoolConfig, host, port, 3000, password);
//                    log.info("JedisPool注入成功！");
//                    log.info("redis地址：" + host + ":" + port);
//                }
//            }
//        }
//        return jedisPool;
//    }
//}
