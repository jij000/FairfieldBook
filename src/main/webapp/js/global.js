window.alert = function () { };
var defaultCSS = document.getElementById('bootstrap-css');
function changeCSS(css) {
    if (css) $('head > link').filter(':first').replaceWith('<link rel="stylesheet" href="' + css + '" type="text/css" />');
    else $('head > link').filter(':first').replaceWith(defaultCSS);
}
$(document).ready(function () {
    var iframe_height = parseInt($('html').height());
    //window.parent.postMessage(iframe_height, 'https://bootsnipp.com');//should be http://localhost:32768/ like
});

function addAd(title,subtitle,content,link) {
    $("#adpanel").append(`<div class="card gedf-card">
                            <div class="card-body">
                                <h5 class="card-title">${title}</h5>
                                <h6 class="card-subtitle mb-2 text-muted">${subtitle}</h6>
                                <p class="card-text">${content}</p>
                                <a href="#" class="card-link">${link}</a>
                            </div>
                        </div>`);
}

let globalUserId = null;

function visitOthers(id){
    window.open ("MainControllerServlet?id="+id);
}

function readCookie(name) {
    var nameEQ = name + "=";
    var ca = document.cookie.split(';');
    for(var i=0;i < ca.length;i++) {
        var c = ca[i];
        while (c.charAt(0) === ' ') {
            c = c.substring(1,c.length);
        }
        if (c.indexOf(nameEQ) === 0) {
            return c.substring(nameEQ.length,c.length);
        }
    }
    return null;
}

function isSelf(){

    return globalUserId==null||readCookie("userId")==globalUserId;
}

function getUrlParam(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
    var r = window.location.search.substr(1).match(reg);
    if (r != null) return unescape(r[2]); return null;
}

function followOther(id){
    $.post("FollowController",
        {"id":id}).done(function(data){
            console.log(data);
            loadProfile();
            posts.getAllPosts();
        })
}

//start from here
$(function(){
    globalUserId = getUrlParam("id");
    if(!isSelf()){
        $("#editorPanel").hide();
    }
    weather.init();
    posts.init();
    isSelf();
});