package org.test.websocket.simple.websockets;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistration;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.times;


@RunWith(SpringRunner.class)
public class PingPongWebSocketConfigurerTest {

    @MockBean
    private PingPongWebSocketInterceptor interceptorBean;

    @MockBean
    private PingPongWebSocketHandler handlerBean;

    @Mock
    private WebSocketHandlerRegistry webSocketHandlerRegistry;

    @Mock
    private WebSocketHandlerRegistration webSocketHandlerRegistration;

    @InjectMocks
    private PingPongWebSocketConfigurer pingPongWebSocketConfigurer;

    @Test
    public void testRegistrationCalls() throws Exception {
        when(webSocketHandlerRegistration.addInterceptors(any()))
                .thenReturn(webSocketHandlerRegistration);

        when(webSocketHandlerRegistry.addHandler(any(), any()))
                .thenReturn(webSocketHandlerRegistration);

        pingPongWebSocketConfigurer.registerWebSocketHandlers(webSocketHandlerRegistry);

        verify(webSocketHandlerRegistry, times(1)).addHandler(any(), any());
        verify(webSocketHandlerRegistration, times(1)).addInterceptors(any());
    }

}
