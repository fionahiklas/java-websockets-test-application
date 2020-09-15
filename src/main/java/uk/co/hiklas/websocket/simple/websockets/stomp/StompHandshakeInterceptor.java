package uk.co.hiklas.websocket.simple.websockets.stomp;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.handler.WebSocketHandlerDecorator;
import org.springframework.web.socket.server.HandshakeInterceptor;

import java.util.Map;

@Slf4j
@Component
public class StompHandshakeInterceptor implements HandshakeInterceptor {

    @Override
    public boolean beforeHandshake(ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse, WebSocketHandler webSocketHandler, Map<String, Object> map) throws Exception {
        log.debug("STOMP Before handshake call, headers: {}", serverHttpRequest.getHeaders());
        log.debug("STOMP Before handshake call, handler class: {}", webSocketHandler.getClass().toString());

        if (webSocketHandler instanceof WebSocketHandlerDecorator) {
            var realWebSocketHandler = ((WebSocketHandlerDecorator) webSocketHandler).getLastHandler();
            log.debug("STOMP Before handshake call, real handler class: {}", realWebSocketHandler);
        } else {
            log.debug("STOMP Before handshake call, not a decorator");
        }
        return true;
    }

    @Override
    public void afterHandshake(ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse, WebSocketHandler webSocketHandler, Exception e) {
        log.debug("STOMP After handshake call");
    }
}
