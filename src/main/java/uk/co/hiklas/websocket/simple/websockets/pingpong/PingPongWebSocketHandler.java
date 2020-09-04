package uk.co.hiklas.websocket.simple.websockets.pingpong;

import io.micrometer.core.instrument.DistributionSummary;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.PongMessage;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import uk.co.hiklas.websocket.simple.service.PingerService;

@Slf4j
@Component
public class PingPongWebSocketHandler extends TextWebSocketHandler {

    @Autowired
    private PingerService pingerService;

    @Autowired
    @Qualifier("pingTime")
    private DistributionSummary pingTimeMetricRecorder;

    @Override
    protected void handlePongMessage(WebSocketSession session, PongMessage message) throws Exception {
        long currentTimestamp = System.currentTimeMillis();
        long messageTimestamp = message.getPayload().getLong();
        long pingTime = currentTimestamp - messageTimestamp;
        log.debug("Handle Pong Message: {}, ping time: {}", messageTimestamp, pingTime);

        pingTimeMetricRecorder.record(pingTime);
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        log.debug("Handle Text Message: {}", message.getPayload());
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        log.debug("Handle afterConnectionEstablished, registering pinger for session: {}", session);
        pingerService.registerWebSocketSessionWithPinger(session);
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        log.debug("Handle transport error, exception: {}", exception);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        log.debug("Handle afterConnectionClosed, status: {}, unregistering for session: {}", status, session);
        pingerService.unregisterWebSocketSessionWithPinger(session);
    }
}
