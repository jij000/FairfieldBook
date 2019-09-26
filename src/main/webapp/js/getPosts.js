let posts = (function () {
    function makePost(data) {
        let followStr = data[8]?"":`<button class="btn btn-primary myposts" type="button" onclick="followOther(${data[1]})" >Follow</button>`;
        let ret = `<div class="card gedf-card">
        <div class="card-header">
            <div class="d-flex justify-content-between align-items-center">
                <div class="d-flex justify-content-between align-items-center">
                    <div class="mr-2">
                        <img class="rounded-circle" width="45" src="${data[3]}" alt="" onclick="visitOthers(${data[1]})">
                    </div>
                    <div class="ml-2">
                        <div class="h5 m-0">@${data[2]}</div>
                        <div class="h7 text-muted">${data[2]}</div>
                    </div>
                </div>
                <div>
                    <div class="dropdown">
                        ${followStr}
                    </div>
                </div>
            </div>
        </div>
        <div class="card-body">
            <p class="card-text">
                ${data[4]}
                <img src="${data[5]}" width="100%">
            </p>
        </div>
    </div>`;
        return ret;
    }


    function addFromFront(data){
        data[2] = decodeURI(data[2]);
        data[4] = decodeURI(data[4]);
        $("#editorPanel").after(makePost(data));
    }

    function addFromEnd(data){
        data[2] = decodeURI(data[2]);
        data[4] = decodeURI(data[4]);
        $("#postsContainer").append(makePost(data));
    }

    function removeAll(){
        $(".myposts").remove();
    }


// [52, 13, "Jipeng", "https://cdn4.iconfinder.com/data/icons/social-media-icons-the-circle-set/48/facebook_circle-512.png", "???", "", "N", "N", 13] (9)

    function request() {
        removeAll();
        $.get("PostController",{id: globalUserId}).done(function (data) {
            console.log(data);
            data.reverse().forEach(element => {
                addFromEnd(element);
            });
        }).fail(function (data) {
            
        })
    }

    return {
        "init": function () {
            request();
        },
        "getAllPosts": request,
        "addFromFront" : addFromFront,
        "addFromEnd" : addFromEnd,
        "removeAll" : removeAll
    }
})();