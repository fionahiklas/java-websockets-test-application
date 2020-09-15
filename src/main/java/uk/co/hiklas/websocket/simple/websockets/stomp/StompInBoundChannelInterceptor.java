package uk.co.hiklas.websocket.simple.websockets.stomp;


import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class StompInBoundChannelInterceptor implements ChannelInterceptor {

    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel) {
        log.debug("StompMessageInterceptor - preSend, message: {}, {}", message, message.getPayload());
        return message;
    }

    @Override
    public Message<?> postReceive(Message<?> message, MessageChannel channel) {
        log.debug("StompMessageInterceptor - postReceive, message: {}, {}", message, message.getPayload());
        return message;
    }
}
