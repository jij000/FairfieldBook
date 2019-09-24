$(document).ready(function(){
    alert("123");
    loadProfile();
    function loadProfile(){
        $.ajax("ProfileController",
            {
                "data":{"name":"666666"}
            }
        ).done(displayPro);
    }
    function displayPro(data){
        data=JSON.parse(data);
        $("#profile_name").html(data.name);
    }

});