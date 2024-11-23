var ws;
var currid;

function connect(id) {
    currid = "#" + id + "#"
    ws = new WebSocket('ws://localhost:8080/comms/survey-speak');
    ws.onmessage = function(data) {
        if (data.data.includes("CLOSE") && data.data.includes(currid)) {
            location.reload();
        }
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