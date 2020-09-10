package uk.co.hiklas.websocket.simple.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import uk.co.hiklas.websocket.simple.responses.LoginResponse;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;

@Slf4j
@RestController
public class LoginController {

    @PostMapping("/login")
    public LoginResponse login(HttpServletRequest request, HttpServletResponse response) {
        log.debug("/login called, request: ", request);
        Cookie elmo = new Cookie("X-FromLogin", "Wibble"+System.currentTimeMillis());
        response.addCookie(elmo);
        return new LoginResponse(request.getHeader("MyNameIs"), Arrays.toString(request.getCookies()) );
    }

}
