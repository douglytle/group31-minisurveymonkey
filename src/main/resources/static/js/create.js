$(document).ready(function () {
    connect()
})

function connect() {
    var baseURL = window.location.origin
    ws = new WebSocket(baseURL + '/comms/survey-speak');
    ws.onmessage = function(data) {
    }
}

function sendCreate(){
    var data = JSON.stringify({
        'type': "SURVEY_CREATED",
    })
    ws.send(data);
}
