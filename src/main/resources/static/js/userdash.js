var ws;

function connect() {
    ws = new WebSocket('ws://localhost:8080/comms/survey-speak');
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

function sendData(message, id) {
    var data = JSON.stringify({
        'message' : message,
        'id': id
    })
    ws.send(data);
}

// function helloWorld(message) {
//     $("#helloworldmessage").append(" " + message + "");
// }