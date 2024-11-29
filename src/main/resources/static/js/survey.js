var ws;
var currid;

function connect(id) {
    currid = "#" + id + "#"
    var baseURL = window.location.origin
    ws = new WebSocket(baseURL + '/comms/survey-speak');
    ws.onmessage = function(data) {
        if (data.data.includes("CLOSE") && data.data.includes(currid)) {
            console.log(data.data)
            location.reload();
        }
    }
}

function disconnect() {
    if (ws != null) {
        ws.close();
    }
}