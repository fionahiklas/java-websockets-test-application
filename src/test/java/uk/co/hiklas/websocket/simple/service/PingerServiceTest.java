package uk.co.hiklas.websocket.simple.service;


import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.socket.PingMessage;
import org.springframework.web.socket.WebSocketSession;

import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ScheduledFuture;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.collection.IsMapContaining.hasKey;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.any;

/**
 * NOTE: Don't use generics for the ScheduledFuture - since we aren't
 * actually returning any value in them it just causes problems.  The
 * compiler isn't able to figure out what the type should be so if you
 * use &lt;?&gt; (which is correct) it just gets confused.
 * Generics in Java aren't brilliant :/
 */
@Slf4j
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = PingerService.class)
@TestPropertySource
public class PingerServiceTest {

    @Mock
    private WebSocketSession sessionOne;

    @Mock
    private WebSocketSession sessionTwo;

    @Mock
    private ScheduledFuture futureOne;

    @Mock
    private ScheduledFuture futureTwo;

    @Captor
    private ArgumentCaptor<Runnable> runnableArgumentCaptor;

    @Captor
    private ArgumentCaptor<PingMessage> pingMessageArgumentCaptor;

    @MockBean(name="PingerThreadPool")
    private ThreadPoolTaskScheduler mockThreadPool;

    // Spring will pick up the value for the time delay used by the pinger
    // service from the PingerServiceTest.properties file under
    // test/resources
    @Autowired
    private PingerService pingerService;

    @Test
    public void testValueOfPingerDelay() throws Exception {
        assertThat(pingerService.getPingerDelay(), equalTo(1));
    }

    @Test
    public void testRegisterSessionWithPinger_newSession() throws Exception {
        Map<String, Object> attributes = new HashMap<>();

        when(sessionOne.getAttributes()).thenReturn(attributes);
        when(mockThreadPool.scheduleAtFixedRate(any(Runnable.class), eq(1000L))).thenReturn(futureOne);

        ScheduledFuture<?> result = pingerService.registerWebSocketSessionWithPinger(sessionOne);
        assertThat(result, notNullValue());
        assertThat(attributes, hasKey(PingerService.PINGER_FUTURE));
        assertThat(attributes.get(PingerService.PINGER_FUTURE), equalTo(futureOne));
    }

    @Test
    public void testRegisterSessionWithPinger_runnerCallsWebSocketPing() throws Exception {
        // Grab the current time so that we can check that the timestamp the
        // ping message uses is within acceptable limits.  We've no way of
        // knowing the exact time the code uses
        Long startTimestamp = System.currentTimeMillis();

        // Empty session attributes to return
        Map<String, Object> attributes = new HashMap<>();

        when(sessionOne.getAttributes()).thenReturn(attributes);
        when(mockThreadPool.scheduleAtFixedRate(any(Runnable.class), eq(1000L))).thenReturn(futureOne);

        ScheduledFuture<?> result = pingerService.registerWebSocketSessionWithPinger(sessionOne);

        assertThat(result, notNullValue());
        verify(mockThreadPool).scheduleAtFixedRate(runnableArgumentCaptor.capture(), eq(1000L));

        Runnable scheduledRunnable = runnableArgumentCaptor.getValue();
        assertThat(scheduledRunnable, notNullValue());

        // Actually kick off the pinger instance which should run once
        scheduledRunnable.run();

        verify(sessionOne).sendMessage(pingMessageArgumentCaptor.capture());
        assertThat(pingMessageArgumentCaptor.getValue(), notNullValue());

        // Check the payload value
        ByteBuffer pingPayload = pingMessageArgumentCaptor.getValue().getPayload();
        assertThat(pingPayload, notNullValue());
        assertThat(pingPayload.position(), equalTo(0));

        Long payloadTimestamp = pingPayload.getLong();

        Long nowTimestamp = System.currentTimeMillis();
        log.debug("Timestamps, start: {}, payload {}, now {}", startTimestamp, payloadTimestamp, nowTimestamp);

        // The timestamp should be within a fairly small range of values
        assertThat(payloadTimestamp, lessThanOrEqualTo(nowTimestamp));
        assertThat(payloadTimestamp, greaterThanOrEqualTo(startTimestamp));
    }

    @Test(expected=IllegalStateException.class)
    public void testUnregisterSessionWithPinger_noSessionPresent() throws Exception {
        Map<String, Object> attributes = new HashMap<>();

        when(sessionOne.getAttributes()).thenReturn(attributes);

        // Should throw an exception as there is no session present
        pingerService.unregisterWebSocketSessionWithPinger(sessionOne);
    }
}
