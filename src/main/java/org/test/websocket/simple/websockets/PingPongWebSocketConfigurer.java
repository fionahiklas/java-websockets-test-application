package org.test.websocket.simple.websockets;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Slf4j
@RequiredArgsConstructor
public class PingPongWebSocketConfigurer implements WebSocketConfigurer {

    private PingPongWebSocketHandler handler;

    private PingPongWebSocketInterceptor interceptor;

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry webSocketHandlerRegistry) {
        log.debug("Configuring websockets");

        webSocketHandlerRegistry
                .addHandler(handler, "pingpong")
                .addInterceptors(interceptor);
    }
}
