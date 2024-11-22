var ws;

function connect() {
    ws = new WebSocket('ws://localhost:8080/comms/survey-speak');
    console.log("connected")
    ws.onmessage = function(data) {
        console.log(data.data);
    }
    //setConnected(true);
}

function disconnect() {
    if (ws != null) {
        ws.close();
    }
    //setConnected(false);
    console.log("Websocket is in disconnected state");
}

// function helloWorld(message) {
//     $("#helloworldmessage").append(" " + message + "");
// }