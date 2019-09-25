$(document).ready(function(){
    $("#name").keyup(function(){
        $("#namecheck").html("");
    });
    $("#password").click(checkUserNameExist);
    $("#re_pass").keyup(checkPass);
    $("#agree-term").change(checkAgree);

    $("#register-form").submit(submitCheck);

    function checkUserNameExist(){
        checkUserName();
        if($("#name").val()!="" ){
            $.ajax("CheckUserNameController",
                {
                    "data":{"name":$("#name").val()}
                }).done(function(data){
                if(data=="exist"){
                    $("#namecheck").html("This username already exists").css("color","red").css("position","static");
                }
            });
        }
    }
    function checkPass(){
        var pass = $("#password").val();
        var repass =  $("#re_pass").val();

        if( pass=="" || repass=="" || pass==repass ){
            $("#repasscheck").html("");
            return true;
        }
        else{
            $("#repasscheck").html("the password must be same").css("color","red").css("position","static");
            return false;
        }
    }
    function checkUserName(){
        if(checkCharactor($("#name").val())){
            $("#namecheck").html("");
            return true;
        }else{
            $("#namecheck").html("name must be character or number and length can't less than 5").css("color","red").css("position","static");
            return false;
        }
    }
    function checkAgree() {
        if($("#agree-term").is(':checked')){
            $("#agreecheck").html("");
        }
    }
    function submitCheck(event){
        event.preventDefault();
        var submitFlag=true;
        if($("#name").val()=="") {
            $("#namecheck").html("username can't be null").css("color","red").css("position","static");
            submitFlag=false;
        }else if(!checkUserName()){
            submitFlag=false;
        }

        if($("pass").val()=="" || $("re_pass").val()=="" ){
            $("#repasscheck").html("password can't be null").css("color","red").css("position","static");
            submitFlag=false;
        }else if(!/^\w{5,10}$/.test($("pass").val())){
            $("#repasscheck").html("password length should be 5-10").css("color","red").css("position","static");
            submitFlag=false;
        }else if(!checkPass()){
            submitFlag=false;
        }
        if(!$("#agree-term").is(':checked')){
            $("#agreecheck").html("you have to agree to the statments");
            submitFlag=false;
        }
        if(submitFlag===true){
            this.submit();
        }
    }

    function checkCharactor(str){
        if(/^([a-zA-Z0-9]{5,})$/.test(str)){
            return true;
        } else{
            return false;
        }
    }

});