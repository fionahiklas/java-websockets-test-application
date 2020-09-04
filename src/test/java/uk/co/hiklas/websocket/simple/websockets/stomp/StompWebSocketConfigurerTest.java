package uk.co.hiklas.websocket.simple.websockets.stomp;

import org.junit.Test;
import org.mockito.Mock;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

public class StompWebSocketConfigurerTest {
    @Mock
    private MessageBrokerRegistry mockMessageBrokerRegistry;

    @Mock
    private StompEndpointRegistry mockStompEndpointRegistry;


    @Test
    public void testConfigureMessageBroker() throws Exception {

    }

    @Test
    public void testRegisterStompEndpoints() throws Exception {

    }

}
