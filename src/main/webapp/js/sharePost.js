$(function(){
    $(document).on("input propertychange","#message",function(){
        let textLen = this.value.length;
        initTextarea(textLen);
    });

    $("#shareBtn").click(function () {
        initBtn("#shareBtn")
        let message = $("#message").val();
        let imgName = $("#imageName").val();
        if ((message.length == 0 || message.length > 280) && imgName.length == 0) {
            // error
            if (message.length > 280) {
                showAlert("#shareBtn", "Tip", "Your content beyond 280 characters.");
            } else {
                showAlert("#shareBtn", "Tip", "Please input content or upload image.");
            }
        } else {
            // encode
            message = encodeURI(message);
            // request
            $.post("PostController", {
                picUrl: imgName,
                content: message,
            }).done(
                function (data) {
                    $("#message").val("");
                    $("#imageName").val("");
                    $(".custom-file-label").html("Choose image");
                    initTextarea(0);
                    console.log(data);
                    posts.addFromFront(data);
                }
            ).fail(function (xhr, status, exception) {
                $('#adpanel').append(status);
            });
        }
    });
});

function showAlert(btnId, title, content) {
    $(btnId).attr("data-toggle", "modal");
    $(btnId).attr("data-target", "#exampleModal");
    $("#exampleModalLabel").html(title);
    $(".modal-body").html(content);
    $(btnId).click();
}

function initBtn(btnId) {
    $(btnId).removeAttr("data-toggle");
    $(btnId).removeAttr("data-target");
}

function initTextarea(textLen) {
    let showTip = (textLen == 0) ? "Limited: " : "Remaining: ";
    $("#tipLabel").html(showTip + (280-textLen));
    if (textLen >= 280) {
        $("#tipLabel").css("color", "#c82333");
    } else {
        $("#tipLabel").css("color", "#888888");
    }
}
