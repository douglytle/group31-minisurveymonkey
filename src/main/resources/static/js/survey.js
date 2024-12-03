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
}

function send_update(id)
{
    var data = JSON.stringify({
        'type': "SURVEY_SUBMISSION",
        'id': id
    })
    ws.send(data);
}

function disconnect() {
    if (ws != null) {
        ws.close();
    }
}