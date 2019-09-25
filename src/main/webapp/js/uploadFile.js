$(function () {
    $("#imgSubmit").click(function () {
        uploadImg(function () {
            console.log(data);
        });

    })

    $("#customFile").change(function () {
        uploadImg(function (data) {
            console.log(data.image);
            $(".custom-file-label").html("Upload Succeed");
            $("#imageName").val(data.image);
            setTimeout(function () {
                $(".custom-file-label").html(data.image);
            }, 1500);
        });
    });
});



function uploadImg(resultFunc) {
    var form = $("#uploadForm")[0];
    var data = new FormData(form);
    $.ajax("upload", {
        type : "POST",
        processData : false,
        contentType : false,
        enctype : "multipart/form-data",
        data : data
    }).done(resultFunc).fail(function (xhr, status, exception) {
        console.log(xhr, status, exception);
    });
}

/*
* <form id="uploadForm" name="uploadForm" method="POST" enctype="multipart/form-data" >
    <input type="file" id="myfile" name="myfile" accept="image/png, image/jpg, image/jpeg, image/gif" />
</form>
<input id="imgSubmit" type="submit" value="Submit" />
* */