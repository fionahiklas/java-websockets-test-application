
function WebSocketsClient() {
    console.log("Creating WebSocket Client");
}

let extend = WebSocketsClient.prototype;

extend.openWebSocket = function(websocketURL) {
    console.log("Creating websocket for URL: ", websocketURL);
    this.socket = new WebSocket(websocketURL);

    this.socket.onopen = function(event) {
        console.log("Connection opened");
    };

    this.socket.onclose = function(event) {
        console.log("Connection closes");
    }

    this.socket.onerror = function(event) {
        console.log("Websocket error: ", event.message);
    }

    this.socket.onmessage = function(event) {
        console.log("Received message: ", event.data);
    }
}

extend.closeWebSocket = function() {
    console.log("Closing websocket");
    this.socket.close();
}