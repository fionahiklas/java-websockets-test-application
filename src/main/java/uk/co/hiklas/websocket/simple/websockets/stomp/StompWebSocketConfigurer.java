package uk.co.hiklas.websocket.simple.websockets.stomp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class StompWebSocketConfigurer implements WebSocketMessageBrokerConfigurer {

    @Autowired
    private StompHandshakeInterceptor stompHandshakeInterceptor;

    @Override
    public void configureMessageBroker(MessageBrokerRegistry messageBrokerRegistry) {
        messageBrokerRegistry
            .enableStompBrokerRelay("/topic/yall", "/user/mytasks");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry stompEndpointRegistry) {
        stompEndpointRegistry.addEndpoint("/ankh")
                .addInterceptors(stompHandshakeInterceptor);
    }
}
