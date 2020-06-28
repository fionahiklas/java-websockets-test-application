package org.test.websocket.simple.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class PingerService {

    /**
     * Delay between pings
     */
    @Value("${pinger.time.delay}")
    private Integer pingerDelay = 120;


    protected Integer getPingerDelay() {
        return this.pingerDelay;
    }
}
