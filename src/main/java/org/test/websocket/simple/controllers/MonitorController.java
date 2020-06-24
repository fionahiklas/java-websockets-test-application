package org.test.websocket.simple.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.test.websocket.simple.responses.WebsocketMonitorResponse;


@RestController
public class MonitorController {

    @RequestMapping("/monitor")
    public WebsocketMonitorResponse monitor() {
        return new WebsocketMonitorResponse(0);
    }
}
