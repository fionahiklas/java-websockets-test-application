package uk.co.hiklas.websocket.simple.websockets.pingpong;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Slf4j
@Configuration
@EnableWebSocket
public class PingPongWebSocketConfigurer implements WebSocketConfigurer {

    @Autowired
    private PingPongWebSocketHandler handler;

    @Autowired
    private PingPongWebSocketInterceptor interceptor;

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry webSocketHandlerRegistry) {
        log.debug("Configuring websockets");

        webSocketHandlerRegistry
                .addHandler(handler, "/pingpong")
                .addInterceptors(interceptor);
    }
}
