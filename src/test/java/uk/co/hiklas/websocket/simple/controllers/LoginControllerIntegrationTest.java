package uk.co.hiklas.websocket.simple.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.cookie;

@SpringBootTest
@AutoConfigureMockMvc
public class LoginControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testLoginReturnsHeaderAndCookie() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/login").header(LoginController.MYNAMEIS_HEADER, "Mr Nutt"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(content().string(containsString("Mr Nutt")))
                .andExpect(cookie().exists(LoginController.FROMLOGIN_COOKIE));
    }

    @Test
    public void testLoginReturnsHandlesEmptyHeaderAndCookie() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/login"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(cookie().exists(LoginController.FROMLOGIN_COOKIE))
                .andExpect(cookie().value(LoginController.FROMLOGIN_COOKIE, containsString("Wibble")));
    }

    @Test
    public void testLoginDoesNotReturnsUsernameCookieForEmptyHeader() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/login"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(cookie().doesNotExist(LoginController.USERNAME_COOKIE));
    }

    @Test
    public void testLoginReturnsUsernameCookieWhenHeaderPresent() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/login").header(LoginController.MYNAMEIS_HEADER, "Glenda Sugarbean"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(cookie().exists(LoginController.USERNAME_COOKIE))
                .andExpect(cookie().value(LoginController.USERNAME_COOKIE, "GlendaSugarbean"));
    }


}
