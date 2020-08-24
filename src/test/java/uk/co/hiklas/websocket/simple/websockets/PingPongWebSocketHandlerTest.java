package uk.co.hiklas.websocket.simple.websockets;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.PongMessage;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

@Slf4j
@RunWith(SpringRunner.class)
public class PingPongWebSocketHandlerTest {

    @Mock
    private WebSocketSession mockWebSocketSession;


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
    }

    @Test
    public void testHandleTransportError() throws Exception {
        webSocketHandler.handleTransportError(mockWebSocketSession, new Exception("Test"));
    }

    @Test
    public void testAfterConnectionClosed() throws Exception {
        webSocketHandler.afterConnectionClosed(mockWebSocketSession, CloseStatus.NORMAL);
    }
    
}
