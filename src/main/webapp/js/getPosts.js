let posts = (function () {
    function makePost(data) {
        let ret = `<div class="card gedf-card">
        <div class="card-header">
            <div class="d-flex justify-content-between align-items-center">
                <div class="d-flex justify-content-between align-items-center">
                    <div class="mr-2">
                        <img class="rounded-circle" width="45" src="${data.author.profilePhotoUrl}" alt="" onclick="visitOthers(${data.author.name},${data.author.id})">
                    </div>
                    <div class="ml-2">
                        <div class="h5 m-0">@${data.author.name}</div>
                        <div class="h7 text-muted">${data.author.name}</div>
                    </div>
                </div>
                <div>
                    <div class="dropdown">
                        <button class="btn btn-primary" type="button">Follow</button>
                    </div>
                </div>
            </div>
        </div>
        <div class="card-body">
            <p class="card-text">
                ${data.content}
                <img src="${data.picUrl}" width="100%">
            </p>
        </div>
    </div>`;
        return ret;
    }


    function addFromFront(data){
        $("#editorPanel").after(makePost(data));
    }

    function addFromEnd(data){
        $("#postsContainer").append(makePost(data));
    }


    function request() {
        $.ajax("PostController").done(function (data) {
            data.forEach(element => {
                addFromEnd(element);
            });
        }).fail(function (data) {
            
        })
    }

    return {
        "init": function () {
            request();
        },
        "addFromFront" : addFromFront,
        "addFromEnd" : addFromEnd
    }
})();