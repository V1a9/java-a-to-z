$(".form-control").submit(function () {
    $.post("../ustorage/new", function (data) {

    }).done(function (data) {
        var errors = JSON.parse(data.responseText);
        var inputs = document.querySelectorAll("input");
        var i;
        if (errors !== null) {
            for (i = 0; i < inputs.length; i++) {
                if (i !== 1 || i !== 3 || i !== 5 || i !== 6) {
                    if (errors[i] === '') {
                        inputs[i].addClass("inputErrors");
                    }
                }
            }
        }
        return false;
    })
});