$(function () {
    $('button#btn').click(function () {
        let id = $(this).parent().parent().children('td')[0].innerText;
        let isDisable = $(this).parent().parent().children('td')[3].innerText;
        isDisable = isDisable == 'active' ? true : false;
        $.post("/AdminPosts", {
            id: id,
            isDisable: isDisable,
        }).done(
            function (data) {
                alert("inactive success");
            }
        ).fail(function (xhr, status, exception) {
            $('#adpanel').append(status);
        });
    });
});