package uk.co.hiklas.websocket.simple.websockets.stomp;


import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

public class StompWebSocketConfigurer implements WebSocketMessageBrokerConfigurer {

    @Override
    public void registerStompEndpoints(StompEndpointRegistry stompEndpointRegistry) {

    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry messageBrokerRegistry) {

    }
}
