package uk.co.hiklas.websocket.simple.responses;

public class WebsocketMonitorResponse {
    private Integer connectionCount;

    public WebsocketMonitorResponse(Integer connectionCount) {
        this.connectionCount = connectionCount;
    }

    public Integer getConnectionCount() {
        return this.connectionCount;
    }
}
