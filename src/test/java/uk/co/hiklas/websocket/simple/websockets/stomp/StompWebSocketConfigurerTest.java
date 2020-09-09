package uk.co.hiklas.websocket.simple.websockets.stomp;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.server.HandshakeInterceptor;

import java.util.List;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.equalTo;


@RunWith(SpringRunner.class)
public class StompWebSocketConfigurerTest {

    @Mock
    private MessageBrokerRegistry mockMessageBrokerRegistry;

    @Mock
    private StompEndpointRegistry mockStompEndpointRegistry;

    @Mock
    private StompHandshakeInterceptor mockStompHandshakeInterceptor;

    @Captor
    private ArgumentCaptor<String> relayNamesArgumentCaptor;

    @Captor
    private ArgumentCaptor<String> endpointArgumentCaptor;


    @InjectMocks
    private StompWebSocketConfigurer stompWebSocketConfigurer;


    @Test
    public void testConfigureMessageBroker() throws Exception {
        stompWebSocketConfigurer.configureMessageBroker(mockMessageBrokerRegistry);

        verify(mockMessageBrokerRegistry, times(1))
                .enableStompBrokerRelay(relayNamesArgumentCaptor.capture());

        // There seems to be some weirdness with argument captors and
        // varargs.  Using String as the captor type and then getting all
        // values back as a list seems to work
        List<String> relayNames = relayNamesArgumentCaptor.getAllValues();

        assertThat(relayNames.size(), greaterThan(0));
        assertThat(relayNames, hasItem("/topic/yall"));
        assertThat(relayNames, hasItem("/user/mytasks"));
    }

    @Test
    public void testRegisterStompEndpoints() throws Exception {
        stompWebSocketConfigurer.registerStompEndpoints(mockStompEndpointRegistry);

        verify(mockStompEndpointRegistry, times(1))
                .addEndpoint(endpointArgumentCaptor.capture());

        String endpoint = endpointArgumentCaptor.getValue();
        assertThat(endpoint, equalTo("/ankh"));
    }

}
