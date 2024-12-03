function connect(id) {
    currid = "#" + id + "#"
    var baseURL = window.location.origin
    ws = new WebSocket(baseURL + '/comms/survey-speak');
    ws.onmessage = function(data) {
        if (data.data.includes("SUBMITTED") && data.data.includes(currid)) {
            updateOutput()
        }
    }
}

function updateOutput() {

    tablinks = document.getElementsByClassName("htablinks");
    current_tab = ""
    for (i = 0; i < tablinks.length; i++) {
        console.log(tablinks[i].className)
        if (tablinks[i].className.includes("active")) {
            current_tab = tablinks[i].id
            tablinks[i].className = tablinks[i].className.replace(" active", "");
            console.log(current_tab)
            break;
        }
    }

    let url = window.location.pathname
    console.log(url)
    $.post(url + "/update_live").done(function (fragment) {
        $("#survey_questions").replaceWith(fragment);
    });


    setTimeout(function () {
        document.getElementById(current_tab).click();
    }, 300);
}

function closeSurvey(id) {
    var data = JSON.stringify({
        'type': "CLOSE_SURVEY",
        'id': id
    })
    ws.send(data);
    $("#surveystatus" + id).text("Survey Status: Closed")
}