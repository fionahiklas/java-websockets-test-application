package org.test.websocket.simple.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketSession;

import java.util.concurrent.RunnableScheduledFuture;

@Component
public class PingerService {

    /**
     * The pinger adds an attribute with this key to the WebSocketSession
     * so that this can be cancelled at a later point in time.
     *
     * TODO: This might be something to watch in case of memory leaks,
     * TODO: in theory cancelled tasks should get garbage collected
     */
    public static final String PINGER_FUTURE="PingerFuture";

    /**
     * Delay between pings
     */
    @Value("${pinger.time.delay}")
    private Integer pingerDelay = 120;

    /**
     * This is provided mainly for testing purposes to surface the configured
     * delay between pings to a web socket
     *
     * @return number of seconds between ping attempts
     */
    protected Integer getPingerDelay() {
        return this.pingerDelay;
    }

    /**
     * Register a session with the pinger service such that a ping request will be
     * sent on a regular basis until the
     * @param webSocketSession
     * @returns RunnableScheduledFuture for the given task that will execute the ping
     * @throws IllegalStateException if this session already has a pinger attached
     */
    public RunnableScheduledFuture<Void> registerWebSocketSessionWithPinger(WebSocketSession webSocketSession)
        throws IllegalStateException {

        return null;
    }

    /**
     * Unregister a websocket session from the pinger service.  This will likely be
     * done when the the connection is closed.
     *
     * @param webSocketSession
     * @throws IllegalStateException if there is no pinger for this webSocketSession or
     * if it has already been unregistered.
     */
    public void unregisterWebSocketSessionWithPinger(WebSocketSession webSocketSession)
        throws IllegalStateException {

    }
}
