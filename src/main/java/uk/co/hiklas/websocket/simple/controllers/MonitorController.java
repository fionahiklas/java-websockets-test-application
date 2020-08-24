package uk.co.hiklas.websocket.simple.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uk.co.hiklas.websocket.simple.responses.WebsocketMonitorResponse;

@Slf4j
@RestController
public class MonitorController {

    @RequestMapping("/monitor")
    public WebsocketMonitorResponse monitor() {
        log.debug("/monitor called");
        return new WebsocketMonitorResponse(0);
    }
}
