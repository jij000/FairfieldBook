let posts = (function () {

    function request() {
        $.ajax("PostController").done(function (data) {
            console.log(data);
        }).fail(function (data) {
            console.log(data)
        })
    }

    return{
        "init": function () {
            request();
        }
    }
})();