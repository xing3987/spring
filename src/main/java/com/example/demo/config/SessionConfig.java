package com.example.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@Configuration
@EnableRedisHttpSession(maxInactiveIntervalInSeconds = 60*60*24)  //设置session共享的时间，并把session存入redis中
public class SessionConfig {
}
