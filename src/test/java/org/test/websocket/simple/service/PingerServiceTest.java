package org.test.websocket.simple.service;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = PingerService.class)
@TestPropertySource
public class PingerServiceTest {

    @Autowired
    private PingerService pingerService;

    @Test
    public void testValueOfPingerDelay() throws Exception {
        assertThat(pingerService.getPingerDelay(), equalTo(1));
    }


    @Test
    public void testRegisterSessionWithPinger() throws Exception {

    }
}
