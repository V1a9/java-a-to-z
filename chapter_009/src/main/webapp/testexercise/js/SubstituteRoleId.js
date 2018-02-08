function substitute() {
    var roles;
    $.ajax({
        url: "../testexercise/show",
        data: {
            dataType: "role"
        },
        type: "GET",
        dataType: "json"
    })
        .done(function (json) {
            roles = json;
            $("#data tr td").each(function (index, cell) {
                if ((index + 1) % 5 === 0) {
                    for (var i = 0; i < roles.length; i++) {
                        if (roles[i].id === cell.innerHTML) {
                            cell.innerHTML = roles[i].role;
                        }
                    }
                }
            });
        });
    return false;
}