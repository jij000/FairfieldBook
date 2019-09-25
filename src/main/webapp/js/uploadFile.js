$(function () {
    $("#imgSubmit").click(function () {
        var form = $("#uploadForm")[0];
        var data = new FormData(form);
        $.ajax("upload", {
            type : "POST",
            processData : false,
            contentType : false,
            enctype : "multipart/form-data",
            data : data
        }).done(function (data) {
            alert(data);
        }).fail(function (xhr, status, exception) {
            alert(status, exception);
        });
    })
});

/*
* <form id="uploadForm" name="uploadForm" method="POST" enctype="multipart/form-data" >
    <input type="file" id="myfile" name="myfile" accept="image/png, image/jpg, image/jpeg, image/gif" />
</form>
<input id="imgSubmit" type="submit" value="Submit" />
* */