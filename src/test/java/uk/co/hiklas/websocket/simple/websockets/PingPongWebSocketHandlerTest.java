package uk.co.hiklas.websocket.simple.websockets;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.PongMessage;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import uk.co.hiklas.websocket.simple.service.PingerService;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;


@Slf4j
@RunWith(SpringRunner.class)
public class PingPongWebSocketHandlerTest {

    @Mock
    private WebSocketSession mockWebSocketSession;

    @Mock
    private PingerService mockPingerService;

    @Captor
    private ArgumentCaptor<WebSocketSession> webSocketSessionArgumentCaptor;


    @InjectMocks
    private PingPongWebSocketHandler webSocketHandler;


    @Test
    public void testHandlePongMessage() throws Exception {
        PongMessage testPongMessage = new PongMessage();
        webSocketHandler.handlePongMessage(mockWebSocketSession, testPongMessage);
    }

    @Test
    public void testHandleTextMessage() throws Exception {
        TextMessage testTextmessage = new TextMessage("Test".getBytes());
        webSocketHandler.handleTextMessage(mockWebSocketSession, testTextmessage);
    }

    @Test
    public void testAfterConnectionEstablished() throws Exception {
        webSocketHandler.afterConnectionEstablished(mockWebSocketSession);
        verify(mockPingerService, times(1))
                .registerWebSocketSessionWithPinger(webSocketSessionArgumentCaptor.capture());
        assertThat(webSocketSessionArgumentCaptor.getValue(), equalTo(mockWebSocketSession));
    }

    @Test
    public void testHandleTransportError() throws Exception {
        webSocketHandler.handleTransportError(mockWebSocketSession, new Exception("Test"));
    }

    @Test
    public void testAfterConnectionClosed() throws Exception {
        webSocketHandler.afterConnectionClosed(mockWebSocketSession, CloseStatus.NORMAL);
        verify(mockPingerService, times(1))
                .unregisterWebSocketSessionWithPinger(webSocketSessionArgumentCaptor.capture());
        assertThat(webSocketSessionArgumentCaptor.getValue(), equalTo(mockWebSocketSession));
    }
    
}
