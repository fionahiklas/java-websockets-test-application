package uk.co.hiklas.websocket.simple.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uk.co.hiklas.websocket.simple.responses.WebsocketMonitorResponse;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestController
public class MonitorController {

    @RequestMapping("/monitor")
    public WebsocketMonitorResponse monitor(HttpServletRequest request) {
        log.debug("/monitor called, request: ", request);
        return new WebsocketMonitorResponse(0);
    }
}
