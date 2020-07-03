package org.test.websocket.simple.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.PingMessage;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.concurrent.ScheduledFuture;

@Slf4j
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
     * The thread pool used for executing tasks
     */
    @Autowired
    @Qualifier("PingerThreadPool")
    private ThreadPoolTaskScheduler taskScheduler;

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
     * @returns ScheduledFuture for the given task that will execute the ping
     * @throws IllegalStateException if this session already has a pinger attached
     */
    public ScheduledFuture<?> registerWebSocketSessionWithPinger(WebSocketSession webSocketSession)
        throws IllegalStateException {

        log.debug("Creating runner for websocket session: {}", webSocketSession);

        // This should be a closure, so the webSocketSession parameter persists in the inner class
        Runnable runner = new Runnable() {
            @Override
            public void run() {
                log.debug("Calling ping for session: {}", webSocketSession.toString());
                WebSocketMessage<ByteBuffer> pingMessage = new PingMessage();
                try {
                    webSocketSession.sendMessage(pingMessage);
                } catch (IOException ie) {
                    log.warn("Error pinging websocket for session: {}", webSocketSession);
                }
            }
        };

        log.debug("Scheduling the runner for websocket session: {}", webSocketSession);
        // TODO: There are potential race conditions here - when this task is scheduled it
        // TODO: will attempt to execute immediately for a start.  The web socket may have
        // TODO: disconnected before then.  Maybe the runner needs to handle that by checking
        // TODO: if the session object has the future attribute set and if not, it does
        // TODO: nothing yet?
        // TODO: Another issue is that the act of unregistering the runner may fail because again
        // TODO: the socket closed too soon and the code doesn't get a chance to add the
        // TODO: future which allows the task to be cancelled.
        // TODO: Again maybe we could add something else into the session to indicate this
        // TODO: condition and get the runner to stop attempting pings
        ScheduledFuture<?> future = taskScheduler.scheduleAtFixedRate(runner, scheduleRate());
        log.debug("Runner is scheduled now");
        webSocketSession.getAttributes().put(PINGER_FUTURE, future);
        return future;
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

        log.debug("Unregistering web socket session: {}", webSocketSession);
        ScheduledFuture<?> future = (ScheduledFuture)webSocketSession.getAttributes().get(PINGER_FUTURE);

        if (future == null) {
            log.error("Trying to unregister a session with no pinger");
            throw new IllegalStateException("No pinger present");
        }
    }

    private long scheduleRate() {
        return pingerDelay * 1000L;
    }


}
