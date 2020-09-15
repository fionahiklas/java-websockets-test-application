package uk.co.hiklas.websocket.simple.websockets.stomp;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.handler.WebSocketHandlerDecorator;

@Slf4j
public class StompPingPongHandlerDecorator extends WebSocketHandlerDecorator {

    public StompPingPongHandlerDecorator(WebSocketHandler delegate) {
        super(delegate);
        log.debug("StompPingPongHandlerDecorator constructor called, delegate type: {}", delegate.getClass().toString());
    }
}
