package uk.co.hiklas.websocket.simple.websockets.stomp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketTransportRegistration;

@Configuration
@EnableWebSocketMessageBroker
public class StompWebSocketConfigurer implements WebSocketMessageBrokerConfigurer {

    @Autowired
    private StompHandshakeInterceptor stompHandshakeInterceptor;

    @Autowired
    private StompInBoundChannelInterceptor stompInBoundChannelInterceptor;

    @Autowired
    private StompPingPongHandlerDecoratorFactory stompPingPongHandlerDecoratorFactory;

    @Override
    public void configureMessageBroker(MessageBrokerRegistry messageBrokerRegistry) {
        messageBrokerRegistry
                .enableStompBrokerRelay("/topic/discworld", "/user/mytasks");

    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry stompEndpointRegistry) {
        stompEndpointRegistry
                .addEndpoint("/ankh")
                .addInterceptors(stompHandshakeInterceptor);
    }

    @Override
    public void configureClientInboundChannel(ChannelRegistration registration) {
        registration
                .interceptors(stompInBoundChannelInterceptor)
                .taskExecutor()
                .corePoolSize(2);
    }

    @Override
    public void configureWebSocketTransport(WebSocketTransportRegistration transportRegistry) {
        transportRegistry
                .addDecoratorFactory(stompPingPongHandlerDecoratorFactory);
    }
}
