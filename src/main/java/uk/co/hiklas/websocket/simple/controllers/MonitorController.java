package uk.co.hiklas.websocket.simple.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uk.co.hiklas.websocket.simple.responses.WebsocketMonitorResponse;

import jakarta.servlet.http.HttpServletRequest;

@Slf4j
@RestController
public class MonitorController {

    @GetMapping("/monitor")
    public ResponseEntity<WebsocketMonitorResponse> monitor(HttpServletRequest request) {
        log.debug("/monitor called, request: ", request);

        return ResponseEntity
                .ok()
                .body(new WebsocketMonitorResponse(0));
    }
}
