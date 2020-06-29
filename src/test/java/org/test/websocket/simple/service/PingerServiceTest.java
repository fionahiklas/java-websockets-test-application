package org.test.websocket.simple.service;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.socket.WebSocketSession;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ScheduledFuture;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.collection.IsMapContaining.hasKey;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.any;

/**
 * NOTE: Don't use generics for the ScheduledFuture - since we aren't
 * actually returning any value in them it just causes problems.  The
 * compiler isn't able to figure out what the type should be so if you
 * use &lt;?&gt; (which is correct) it just gets confused.
 * Generics in Java aren't brilliant :/
 */
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

    @MockBean(name="PingerThreadPool")
    private ThreadPoolTaskScheduler mockThreadPool;

    @Autowired
    private PingerService pingerService;

    @Test
    public void testValueOfPingerDelay() throws Exception {
        assertThat(pingerService.getPingerDelay(), equalTo(1));
    }


    @Test
    public void testRegisterSessionWithPinger() throws Exception {
        Map<String, Object> attributes = new HashMap<>();

        when(sessionOne.getAttributes()).thenReturn(attributes);
        when(mockThreadPool.scheduleAtFixedRate(any(Runnable.class), eq(1000L))).thenReturn(futureOne);

        ScheduledFuture<?> result = pingerService.registerWebSocketSessionWithPinger(sessionOne);
        assertThat(result, notNullValue());
        assertThat(attributes, hasKey(PingerService.PINGER_FUTURE));
        assertThat(attributes.get(PingerService.PINGER_FUTURE), equalTo(futureOne));
    }
}
