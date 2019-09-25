$.get("Advertisement").done(
    function(data) {
        let x;
        for (x in data) {
            if($('#adpanel').innerText != "") {
                $('#adpanel').append('</br>')
            }
            $('#adpanel').append(x);
        }
    }
).fail(function(xhr, status, exception){
    $('#adpanel').append(status);
});