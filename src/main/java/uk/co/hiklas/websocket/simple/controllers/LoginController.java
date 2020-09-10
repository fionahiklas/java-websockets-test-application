package uk.co.hiklas.websocket.simple.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import uk.co.hiklas.websocket.simple.responses.LoginResponse;


@Slf4j
@RestController
public class LoginController {

    public static final String MYNAMEIS_HEADER = "X-MyNameIs";
    public static final String FROMLOGIN_COOKIE = "X-FromLogin";

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestHeader(name = MYNAMEIS_HEADER) String myNameIs,
                                               @CookieValue(name = FROMLOGIN_COOKIE, required = false) String existingLoginCookie) {
        log.debug("/login called, my name is: {} existing cookie: {}", myNameIs, existingLoginCookie );

        ResponseCookie loginCookie = ResponseCookie
                .from(FROMLOGIN_COOKIE, "Wibble"+System.currentTimeMillis())
                .build();

        HttpHeaders headers = new HttpHeaders();
        headers.add("Set-Cookie", loginCookie.toString());

        log.debug("Added new cookie: {}", loginCookie.toString());

        return ResponseEntity
                .ok()
                .headers(headers)
                .body(new LoginResponse(myNameIs, existingLoginCookie));
    }

}
