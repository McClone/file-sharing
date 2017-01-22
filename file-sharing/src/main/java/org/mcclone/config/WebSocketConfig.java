package org.mcclone.config;

import org.mcclone.message.web.MyHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.*;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;

/**
 * Created by mcclone on 17-1-20.
 */
@Configuration
@EnableWebSocket
@EnableWebSocketMessageBroker
public class WebSocketConfig extends AbstractWebSocketMessageBrokerConfigurer implements WebSocketConfigurer {

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(myHandler(), "/myHandler")
                .addInterceptors(new HttpSessionHandshakeInterceptor())
                .setAllowedOrigins("/**");
    }

    @Bean
    public WebSocketHandler myHandler() {
        return new MyHandler();
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/hello-websocket").withSockJS();
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        config.setApplicationDestinationPrefixes("/app");
        config.enableSimpleBroker("/topic", "/queue");
    }
}
