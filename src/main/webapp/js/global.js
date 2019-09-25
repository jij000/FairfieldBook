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

//start from here
$(function(){
    weather.init();
    posts.init();
});