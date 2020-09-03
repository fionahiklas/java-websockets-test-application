package uk.co.hiklas.websocket.simple.websockets;

import io.micrometer.core.instrument.DistributionSummary;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.PongMessage;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import uk.co.hiklas.websocket.simple.service.PingerService;

import java.nio.ByteBuffer;

import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.lessThanOrEqualTo;
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

    @Mock
    @Qualifier("pingTime")
    private DistributionSummary mockDistributionSummary;

    @Captor
    private ArgumentCaptor<WebSocketSession> webSocketSessionArgumentCaptor;

    @Captor
    private ArgumentCaptor<Double> recordArgumentCaptor;

    @InjectMocks
    private PingPongWebSocketHandler webSocketHandler;


    @Test
    public void testHandlePongMessage() throws Exception {
        Long currentTimestamp = System.currentTimeMillis();
        PongMessage testPongMessage = createPongMessage(currentTimestamp);

        webSocketHandler.handlePongMessage(mockWebSocketSession, testPongMessage);
        verify(mockDistributionSummary, times(1)).record(recordArgumentCaptor.capture());

        Double recordValue = recordArgumentCaptor.getValue();
        assertThat(recordValue, greaterThanOrEqualTo(Double.valueOf(0)));
        assertThat(recordValue, lessThanOrEqualTo(Double.valueOf(100000)));
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

    private PongMessage createPongMessage(Long payload) {
        ByteBuffer payloadBuffer = ByteBuffer.allocate(Long.BYTES);
        payloadBuffer.putLong(payload);
        payloadBuffer.flip();
        return new PongMessage(payloadBuffer);
    }
}
