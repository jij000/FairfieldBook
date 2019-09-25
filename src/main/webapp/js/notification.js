$(document).ready(function(){
    //$("#btn1").click(notifiy);
    setInterval(notifiy,100000)
    var checkflag=false;
    function notifiy(){
        if(checkflag==false){
            $.ajax("NotificationController",{"dataType":"text"}
            ).done(showMessage).fail(error);
        }
    }
    function showMessage(data){
        // console.log("the data is "+data);
        if(data!=""){
            var html="";
            html +="<div class='alert alert-info alert-dismissible'>";
            html +="<a href='#' class='close' data-dismiss='alert' aria-label='close'>&times;</a>";
            html +="<strong id='noteinfo'>there are "+data+" new posts</strong> ";
            html +="</div>";
            $("#notification").html(html);
            $("#notification").click(function(){checkflag=false;$("#notification").html("");});
        }
        else{
            $("#notification").html("");
        }

    }
    function error(xhr,status,exception){
        console.log(xhr,status,exception);
    }
});