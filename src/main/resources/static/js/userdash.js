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
        'type': "CLOSE_SURVEY",
        'id': id
    })
    ws.send(data);
    $("#surveystatus" + id).text("Survey Status: Closed")
}

// function helloWorld(message) {
//     $("#helloworldmessage").append(" " + message + "");
// }