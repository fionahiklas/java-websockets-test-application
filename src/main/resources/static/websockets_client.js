
console.log("Creating constructor");
function WebSocketsClient() {
    console.log("Creating WebSocket Client");
}

console.log("Getting prototype");
let extend = WebSocketsClient.prototype;

console.log("Extending prototype");

extend.openWebSocket = function(websocketURL) {
    // When the socket handler functions ate called "this" refers to the
    // WebSocket object, we need to refer to the WebSocketsClient object
    // so we set that up as "self"
    const self = this;

    console.log("Creating websocket for URL: ", websocketURL);
    this.socket = new WebSocket(websocketURL);

    this.socket.onopen = function(event) {
        console.log("Connection opened");
        if (self.listeners && self.listeners.open) {
            console.log("Calling open listener");
            self.listeners.open();
        }
    };

    this.socket.onclose = function(event) {
        console.log("Connection closes");
        if (self.listeners && self.listeners.close) {
            console.log("Calling close listener");
            self.listeners.close();
        }
    }

    this.socket.onerror = function(event) {
        console.log("Websocket error: ", event.message);
        if (self.listeners && self.listeners.error) {
            console.log("Calling error listener");
            self.listeners.error(event.message);
        }
    }

    this.socket.onmessage = function(event) {
        console.log("Received message: ", event.data);
        if (self.listeners && self.listeners.message) {
            console.log("Calling message listener");
            self.listeners.message(event.data);
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