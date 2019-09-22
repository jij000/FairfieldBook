$(function () {
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

    addAd();
    addAd()

});