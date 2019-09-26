$(document).ready(function() {
    console.log(globalUserId);
    loadProfile();
    //$("#profileimage").attr("src", proUrl);
    $("#bindtwitter").click(function(){
        window.location.assign("twitter2oauth");
    });
    // $("#logout").click(function(){
    //     window.location.href("LogoutController");
    // });

    $("#proimgSubmit").click(function () {
        var form = $("#uploadproimage")[0];
        var data = new FormData(form);
        $.ajax("upload", {
            "type": "POST",
            "processData": false,
            "contentType": false,
            "enctype": "multipart/form-data",
            "data": data,
            "success":debug111
        });
    });

    function debug111(data) {
        console.log(data.image);
        $("#proimageupload").attr("src",data.image);
    }

    function debugerr(data) {
        console.log("errr");
    }

    $("#submitbtn").click(function(){
        $.ajax("UpdateProfileController", {
            "type" : "POST",
            "data" : {
                "pass":$("#poppass").val(),
                "picUrl":$("#proimageupload")[0].src
            }
        }).done(function (data) {
            //alert(data);
            loadProfile();
        }).fail(function (xhr, status, exception) {
            //alert(status, exception);
        });
    });

});


function loadProfile(){
    console.log(globalUserId);
    $.ajax("ProfileController",
        {
            "data":{"name":"","id": globalUserId}
        }
    ).done(displayPro);
}
function displayPro(data){
    data=JSON.parse(data);
    var proUrl =data.profilePhotoUrl;
    if( data.profilePhotoUrl==null){
        proUrl="https://cdn4.iconfinder.com/data/icons/social-media-icons-the-circle-set/48/facebook_circle-512.png";
    }
    $("#profilename").html(data.name);
    $("#profileimage").attr("src", proUrl);
    $("#poppass").attr("value",data.password);
    $("#poprepass").attr("value",data.password);
    $("#popname").html(data.name);
    $("#popprofileimage").attr("src", proUrl);
    $("#popimage").attr("src", proUrl);
    $("#Followers").html(data.followerNum);
    $("#Following").html(data.followingNum);
    if(data.token){
        $('#bindtwitter').attr("disabled", true);
        $('#bindtwitter').html("Twitter Binded");
    }

    if(!data.isActive){
        $("#shareBtn").attr("disabled", true);
        $('#shareBtn').html("Forbidden");
        $("#shareBtn").attr("class", "btn btn-danger");
    }
        
}
