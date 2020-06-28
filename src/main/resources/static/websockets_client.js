
console.log("Creating constructor");
function WebSocketsClient() {
    console.log("Creating WebSocket Client");
}

console.log("Getting prototype");
let extend = WebSocketsClient.prototype;

console.log("Extending prototype");

extend.openWebSocket = function(websocketURL) {
    console.log("Creating websocket for URL: ", websocketURL);
    this.socket = new WebSocket(websocketURL);

    this.socket.onopen = function(event) {
        console.log("Connection opened");
        if (this.listeners && this.listeners.open) {
            console.log("Calling open listener");
            this.listeners.open();
        }
    };

    this.socket.onclose = function(event) {
        console.log("Connection closes");
        if (this.listeners && this.listeners.close) {
            console.log("Calling close listener");
            this.listeners.close();
        }
    }

    this.socket.onerror = function(event) {
        console.log("Websocket error: ", event.message);
        if (this.listeners && this.listeners.error) {
            console.log("Calling error listener");
            this.listeners.error(event.message);
        }
    }

    this.socket.onmessage = function(event) {
        console.log("Received message: ", event.data);
        if (this.listeners && this.listeners.message) {
            console.log("Calling message listener");
            this.listeners.message(event.data);
        }
    }
}

extend.closeWebSocket = function() {
    console.log("Closing websocket");
    this.socket.close();
    this.socket = null;
}

extend.sendMessage = function(message) {
    console.log("Trying to send a message");
    if (this.socket && this.socket.readyState == this.socket.OPEN) {
        this.socket.send(message);
    } else {
        console.log("ERROR: Socket doesn't exist or isn't ready");
    }
}

/**
 * TODO: This needs to be a bit more robust, it just takes the object and stores it
 * Expects the following object
 * {
 *     open: function() {},
 *     close: function(),{}
 *     error: function(errorMessage) {},
 *     message: function(message) ()
 * }
 * @param listeners
 */
extend.registerListeners = function(listeners) {
    this.listeners = listeners;
}