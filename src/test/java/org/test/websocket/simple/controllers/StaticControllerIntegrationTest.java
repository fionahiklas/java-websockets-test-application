package org.test.websocket.simple.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
public class StaticControllerIntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testMonitorReturnsCount() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/index.html"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("text/html"))
                .andExpect(content().string(containsString("Connection Count")));
    }

    @Test
    public void testCanGetJavaScriptCode() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/websockets_client.js"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/javascript"));
    }

}
