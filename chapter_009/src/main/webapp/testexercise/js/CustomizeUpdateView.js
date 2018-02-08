function getEntity(entity) {
    var idFormInput = $(".form-horizontal input");
    var val = idFormInput.val();
    if (entity === "user") {
        var user;
        $.ajax({
            url:"../testexercise/get",
            type: "GET",
            data:{
                entity:"user",
                id: val
            },
            dataType:"json"
        })
            .done(function (resp) {
                user = resp;
                $("div.container:eq(0)").hide();
                $("div.container:eq(1)").removeAttr("hidden");
                var data = $("#data");
                var tr = $("<tr>" +
                    "<th>Id</th>" +
                    "<th>Name</th>" +
                    "<th>Login</th>" +
                    "<th>Password</th>" +
                    "<th>Role</th>" +
                    "</tr>"
                );
                data.append(tr);
                var fields = [];
                fields.push(
                    "<tr>"
                    + "<td>" + user.id + "</td>"
                    + "<td>" + user.name + "</td>"
                    + "<td>" + user.login + "</td>"
                    + "<td>" + user.password + "</td>"
                    + "<td>" + user.role + "</td>"
                    +"</tr>"
                );

                data.append(fields.join(""));
                substitute();
                $("<input type=\"text\" name=\"id\" hidden>").val(user.id).insertAfter($("div.container:eq(1) div.form-group:eq(3)"));

            })
        ;
    } else if (entity === "role") {
        var role;
        $.ajax({
            url:"../testexercise/get",
            type: "GET",
            data:{
                entity:"role",
                id: val
            },
            dataType:"json"
        })
            .done(function (resp) {
                role = resp;
                $("div.container:eq(0)").hide();
                $("div.container:eq(2)").removeAttr("hidden");
                var data = $("#data");
                var tr = $("<tr>" +
                    "<th>Id</th>" +
                    "<th>Role</th>" +
                    "</tr>"
                );
                data.append(tr);
                var fields = [];
                fields.push(
                    "<tr>"
                    + "<td>" + role.id + "</td>"
                    + "<td>" + role.name + "</td>"
                    +"</tr>"
                );

                data.append(fields.join(""));
                $("<input type=\"text\" name=\"id\" hidden>").val(role.id).insertAfter($("div.container:eq(2) div.form-group:eq(0)"));
            })
        ;
    }  else if (entity === "address") {
        var address;
        $.ajax({
            url:"../testexercise/get",
            type: "GET",
            data:{
                entity:"address",
                id: val
            },
            dataType:"json"
        })
            .done(function (resp) {
                address = resp;
                $("div.container:eq(0)").hide();
                $("div.container:eq(4)").removeAttr("hidden");
                var data = $("#data");
                var tr = $("<tr>" +
                    "<th>Id</th>" +
                    "<th>Address</th>" +
                    "</tr>"
                );
                data.append(tr);
                var fields = [];
                fields.push(
                    "<tr>"
                    + "<td>" + address.id + "</td>"
                    + "<td>" + address.name + "</td>"
                    +"</tr>"
                );

                data.append(fields.join(""));
                $("<input type=\"text\" name=\"id\" hidden>").val(address.id).insertAfter($("div.container:eq(4) div.form-group:eq(0)"));

            })
        ;
    }  else if (entity === "music") {
        var music;
        $.ajax({
            url:"../testexercise/get",
            type: "GET",
            data:{
                entity:"music",
                id: val
            },
            dataType:"json"
        })
            .done(function (resp) {
                music = resp;
                $("div.container:eq(0)").hide();
                $("div.container:eq(3)").removeAttr("hidden");
                var data = $("#data");
                var tr = $("<tr>" +
                    "<th>Id</th>" +
                    "<th>Music</th>" +
                    "</tr>"
                );
                data.append(tr);
                var fields = [];
                fields.push(
                    "<tr>"
                    + "<td>" + music.id + "</td>"
                    + "<td>" + music.name + "</td>"
                    +"</tr>"
                );

                data.append(fields.join(""));
                $("<input type=\"text\" name=\"id\" hidden>").val(music.id).insertAfter($("div.container:eq(3) div.form-group:eq(0)"));
            })
        ;
    }
    return false;
}
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