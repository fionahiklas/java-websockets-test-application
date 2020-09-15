package uk.co.hiklas.websocket.simple.websockets.stomp;

import io.micrometer.core.instrument.DistributionSummary;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.PongMessage;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.WebSocketHandlerDecorator;
import uk.co.hiklas.websocket.simple.service.PingerService;

@Slf4j
public class StompPingPongHandlerDecorator extends WebSocketHandlerDecorator {

    private PingerService pingerService;

    private DistributionSummary pingTimeMetricRecorder;

    public StompPingPongHandlerDecorator(WebSocketHandler delegate, PingerService pingerService, DistributionSummary pingTimeMetricRecorder) {
        super(delegate);
        this.pingerService = pingerService;
        this.pingTimeMetricRecorder = pingTimeMetricRecorder;

        log.debug("StompPingPongHandlerDecorator constructor called, delegate type: {}", delegate.getClass().toString());
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        log.debug("afterConnectionEstablished, registering pinger for session: {}", session);
        pingerService.registerWebSocketSessionWithPinger(session);
        log.debug("registered pinger, now passing the call onto the delegate");
        log.debug("afterConnectionEstablished called, calling super and delegate: {}", this.getDelegate().getClass());
        super.afterConnectionEstablished(session);
    }

    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
        log.debug("handleMessage called, message type: {}", message.getClass().toString());

        if (message instanceof PongMessage) {
            log.debug("Got a Pong message, we're handling it");
            long currentTimestamp = System.currentTimeMillis();
            long messageTimestamp = ((PongMessage)message).getPayload().getLong();
            long pingTime = currentTimestamp - messageTimestamp;
            log.debug("Handle Pong Message: {}, ping time: {}", messageTimestamp, pingTime);

            pingTimeMetricRecorder.record(pingTime);
        }

        log.debug("Passing the message onto the delegate in case they are interested");
        super.handleMessage(session, message);
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        log.debug("handleTransportError called");
        super.handleTransportError(session, exception);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
        log.debug("Handle afterConnectionClosed, status: {}, unregistering for session: {}", closeStatus, session);
        pingerService.unregisterWebSocketSessionWithPinger(session);
        log.debug("unregistered pinger, passing the call onto the delegate");
        super.afterConnectionClosed(session, closeStatus);
    }
}
