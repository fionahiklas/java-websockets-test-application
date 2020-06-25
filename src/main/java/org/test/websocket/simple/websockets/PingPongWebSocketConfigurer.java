package org.test.websocket.simple.websockets;

import lombok.RequiredArgsConstructor;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;


@RequiredArgsConstructor
public class PingPongWebSocketConfigurer implements WebSocketConfigurer {

    private PingPongWebSocketHandler handler;

    private PingPongWebSocketInterceptor interceptor;

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry webSocketHandlerRegistry) {
        webSocketHandlerRegistry
                .addHandler(handler, "pingpong")
                .addInterceptors(interceptor);
    }
}
