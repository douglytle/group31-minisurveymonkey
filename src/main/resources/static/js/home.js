$(document).ready(function() {
   updateOutput();
   connect();
});

function updateOutput() {
    $.post("update_home_list").done(function (fragment) {
        $("#surveylist").replaceWith(fragment);
    });
}

var ws;

function connect() {
    var baseURL = window.location.origin
    ws = new WebSocket(baseURL + '/comms/survey-speak');
    ws.onmessage = function(data) {
        if (data.data.includes("CLOSE")) {
            console.log(data.data)
            updateOutput();
        }
    }
}

function disconnect() {
    if (ws != null) {
        ws.close();
    }
    console.log("Websocket is in disconnected state");
}