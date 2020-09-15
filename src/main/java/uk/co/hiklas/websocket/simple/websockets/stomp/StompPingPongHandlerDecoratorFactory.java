package uk.co.hiklas.websocket.simple.websockets.stomp;


import io.micrometer.core.instrument.DistributionSummary;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.handler.WebSocketHandlerDecoratorFactory;
import uk.co.hiklas.websocket.simple.service.PingerService;

@Slf4j
@Component
public class StompPingPongHandlerDecoratorFactory implements WebSocketHandlerDecoratorFactory {

    @Autowired
    private PingerService pingerService;

    @Autowired
    @Qualifier("pingTime")
    private DistributionSummary pingTimeMetricRecorder;

    @Override
    public WebSocketHandler decorate( WebSocketHandler handler) {
        log.debug("Decorate handler: {}", handler.getClass().toString());
        return new StompPingPongHandlerDecorator(handler, pingerService, pingTimeMetricRecorder);
    }
}
