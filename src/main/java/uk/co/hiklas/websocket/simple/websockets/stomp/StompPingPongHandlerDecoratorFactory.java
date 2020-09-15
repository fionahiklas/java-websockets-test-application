package uk.co.hiklas.websocket.simple.websockets.stomp;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.handler.WebSocketHandlerDecoratorFactory;

@Slf4j
@Component
public class StompPingPongHandlerDecoratorFactory implements WebSocketHandlerDecoratorFactory {

    @Override
    public WebSocketHandler decorate( WebSocketHandler handler) {
        log.debug("Decorate handler: {}", handler.getClass().toString());
        return new StompPingPongHandlerDecorator(handler);
    }
}
