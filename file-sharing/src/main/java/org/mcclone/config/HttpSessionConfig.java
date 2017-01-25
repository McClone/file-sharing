package org.mcclone.config;

import org.mcclone.security.session.AuthenticationSuccessEventListener;
import org.mcclone.security.session.RedisUserSessionRepositoryService;
import org.mcclone.security.session.SessionDestroyedEventListener;
import org.springframework.context.annotation.Bean;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * Created by Administrator on 2017/1/24.
 */
@EnableRedisHttpSession
public class HttpSessionConfig {

    @Bean
    public RedisUserSessionRepositoryService redisUserSessionRepositoryService() {
        return new RedisUserSessionRepositoryService();
    }

    @Bean
    public AuthenticationSuccessEventListener authenticationSuccessEventListener() {
        return new AuthenticationSuccessEventListener();
    }

    @Bean
    public SessionDestroyedEventListener sessionDestroyedEventListener() {
        return new SessionDestroyedEventListener();
    }

}
