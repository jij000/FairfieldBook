$(function () {
    $('button#btn').click(function () {
        let btn = $(this);
        let tdId = $(this).parent().parent().children('td')[0];
        let id = tdId.innerText;
        let tdisDisable = $(this).parent().parent().children('td')[3];
        let isDisable = tdisDisable.innerText;
        isDisable = isDisable == 'active' ? false : true;
        $.post("AdminUsers", {
            id: id,
            isDisable: isDisable,
        }).done(
            function (data) {
                if (!isDisable) {
                    btn.text("enable");
                    btn.attr("class","btn-info");
                    tdisDisable.innerText = "inactive";
                } else {
                    btn.text("disable");
                    btn.attr("class","btn-danger");
                    tdisDisable.innerText = "active";
                }
            }
        ).fail(function (xhr, status, exception) {
            $('#adpanel').append(status);
        });
    });
});