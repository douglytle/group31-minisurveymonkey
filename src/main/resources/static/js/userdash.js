var ws;

function connect() {
    var baseURL = window.location.origin
    ws = new WebSocket(baseURL + '/comms/survey-speak');
    ws.onmessage = function(data) {
        console.log(data.data)
    }
}

function disconnect() {
    if (ws != null) {
        ws.close();
    }
}

function closeSurvey(id) {
    var data = JSON.stringify({
        'type': "CLOSE_SURVEY",
        'id': id
    })
    ws.send(data);
    $("#surveystatus" + id).text("Survey Status: Closed")
}

