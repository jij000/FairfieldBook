$(function () {
    $('button#btn').click(function () {
        let btn = $(this);
        let tdId = $(this).parent().parent().children('td')[0];
        let id = tdId.innerText;
        let tdisDisable = $(this).parent().parent().children('td')[3];
        let isDisable = tdisDisable.innerText;
        isDisable = isDisable == 'active' ? true : false;
        $.post("AdminAds", {
            id: id,
            isDisable: isDisable,
        }).done(
            function (data) {
                if (isDisable) {
                    btn.text("enable");
                    btn.attr("class", "btn btn-info");
                    tdisDisable.innerText = "inactive";
                } else {
                    btn.text("disable");
                    btn.attr("class", "btn btn-danger");
                    tdisDisable.innerText = "active";
                }
            }
        ).fail(function (xhr, status, exception) {
            $('#adpanel').append(status);
        });
    });
    $('#addAdBtn').click(function () {
        let mode = "add";
        let name = $('#adName').val();
        let content = $('#adContent').val();
        let isDisable = $('#adIsDisable').val();
        isDisable = isDisable == 'active' ? true : false;
        $.post("AdminAds", {
            mode: mode,
            name: name,
            content: content,
            isDisable: isDisable
        }).done(function (data) {
            window.location.reload();
        }).fail(function (xhr, status, exception) {
            $('#adpanel').append(status);
        });
    });
});