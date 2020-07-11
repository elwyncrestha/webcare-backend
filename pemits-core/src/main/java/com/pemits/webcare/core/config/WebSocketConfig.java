package com.pemits.webcare.core.config;

import static com.pemits.webcare.core.constant.AppConstant.SOCKET_PUBLISHER;
import static com.pemits.webcare.core.constant.AppConstant.SOCKET_SUBSCRIBER;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

/**
 * @author Elvin Shrestha on 7/11/2020
 */
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry
            .addEndpoint("webcare-websocket")
            .setAllowedOrigins("*")
            .withSockJS();
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.enableSimpleBroker(SOCKET_SUBSCRIBER);
        registry.setApplicationDestinationPrefixes(SOCKET_PUBLISHER);
    }
}
