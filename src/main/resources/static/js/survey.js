var ws;
var currid;

function connect(id) {
    currid = "#" + id + "#"
    var baseURL = window.location.origin
    ws = new WebSocket(baseURL + '/comms/survey-speak');
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