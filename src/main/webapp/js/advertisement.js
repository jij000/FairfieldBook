$.get("Advertisement").done(
    function (data) {

        for (let x = 0; x < data.length; x++) {
            if ($('#adpanel').innerText != "" && $('#adpanel').innerText != undefined) {
                $('#adpanel').append('</br>')
            }
            $('#adpanel').append(data[x].content);
        }
    }
).fail(function (xhr, status, exception) {
    $('#adpanel').append(status);
});