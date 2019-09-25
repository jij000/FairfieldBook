$(function(){
    $("#shareBtn").click(function () {
        let message = $("#message").val();
        let imgName = $("#imageName").val();
        if (message.length == 0 && imgName.length == 0) {
            // error
        } else {
            // request
            $.post("PostController", {
                picUrl: imgName,
                content: message,
            }).done(
                function (data) {
                    $("#message").val("");
                    $("#imageName").val("");
                    $(".custom-file-label").html("Choose image");
                }
            ).fail(function (xhr, status, exception) {
                $('#adpanel').append(status);
            });
        }
    });
});
