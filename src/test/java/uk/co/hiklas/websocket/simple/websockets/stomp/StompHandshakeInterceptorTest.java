package uk.co.hiklas.websocket.simple.websockets.stomp;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.socket.WebSocketHandler;
import uk.co.hiklas.websocket.simple.websockets.pingpong.PingPongWebSocketInterceptor;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;


@RunWith(MockitoJUnitRunner.class)
public class StompHandshakeInterceptorTest {

    @Mock
    private ServerHttpRequest mockServerHttpRequest;

    @Mock
    private ServerHttpResponse mockServerHttpResponse;

    @Mock
    private WebSocketHandler mockWebSocketHandler;


    @InjectMocks
    private StompHandshakeInterceptor interceptor;

    @Test
    public void testBeforeHandshakeReturnsTrue() throws Exception {
        Map<String, Object> attributes = new HashMap<>();

        boolean result = interceptor.beforeHandshake(
                mockServerHttpRequest,
                mockServerHttpResponse,
                mockWebSocketHandler,
                attributes);

        assertThat(result, equalTo(true));
    }
}
