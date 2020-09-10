package uk.co.hiklas.websocket.simple.responses;


public class LoginResponse {

    private String name;
    private String cookies;

    public LoginResponse(String name, String cookies) {
        this.name = name;
        this.cookies = cookies;
    }

    public String getCookies() {
        return cookies;
    }

    public String getName() {
        return name;
    }
}
