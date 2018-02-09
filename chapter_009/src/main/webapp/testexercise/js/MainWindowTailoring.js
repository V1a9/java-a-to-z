function showData(dataType) {
    var dataRows = $("#data tr");
    var users;
    if (dataType === 'user') {
        $.ajax({
            url: "../testexercise/show",
            data: {
                dataType: "user"
            },
            type: "GET",
            dataType: "json"
        })
            .done(function (json) {
                users = json;
            })
        ;
        $.ajax({
            url: "../testexercise/show",
            data: {
                dataType: "role"
            },
            type: "GET",
            dataType: "json"
        })
            .done(function (json) {
                var usersData = $("#data");
                var roles = json;
                var tr = $("<tr>" +
                    "<th>User Id</th>" +
                    "<th>Name</th>" +
                    "<th>Login</th>" +
                    "<th>Password</th>" +
                    "<th>Role</th>" +
                    "</tr>"
                );

                usersData.append(tr);

                var userFields = [];
                for (var i = 0; i < users.length; i++) {
                    var userRole;
                    for (var j = 0; j < roles.length; j++) {
                        if (users[i].role === roles[j].id) {
                            userRole = roles[j].role;
                        }
                    }
                    userFields.push(
                        "<tr>"
                        + "<td>" + users[i].id + "</td>"
                        + "<td>" + users[i].name + "</td>"
                        + "<td>" + users[i].login + "</td>"
                        + "<td>" + users[i].password + "</td>"
                        + "<td>" + userRole + "</td>" +
                        "</tr>"
                    );
                }
                dataRows.remove();
                usersData.append(userFields.join(""));

                $("tr:gt(0)").click(function () {
                    var tr = arguments[0].currentTarget.rowIndex;
                    var trs = $("#data tr:gt(0)");
                    $.each(trs, function (index, element) {
                        if (index + 1 !== tr) {
                            element.remove();
                        }
                    });

                    if ($("tr:eq(0)")[0].cells.length === 5) {

                        var headers = [];
                        headers.push(
                            "<th>Address</th>" +
                            "<th>Music</th>"
                        );
                        $("tr:eq(0)").append(headers.join(""));
                        var cells = [];
                        cells.push(
                            "<td></td>" +
                            "<td></td>"
                        );
                        $("tr:eq(1)").append(cells.join(""));

                    }

                    var userId = arguments[0].currentTarget.cells[0].innerHTML;
                    $.ajax({
                        url:"../testexercise/userall",
                        data:{
                            id:userId
                        },
                        dataType:"json",
                        type:"GET"
                    })
                        .done(function (json) {
                            $("tr:eq(1) td:eq(5)").html(json[1].address);
                            var userMusic = '';
                            for (var i = 3; i < json.length; i++) {
                                userMusic = userMusic + json[i].music + ' ';
                            }
                            $("tr:eq(1) td:eq(6)").html(userMusic);
                        })
                    ;
                })
            })
        ;
    } else if (dataType === 'role') {
        $.ajax({
            url: "../testexercise/show",
            data: {
                dataType: "role"
            },
            type: "GET",
            dataType: "json"
        })
            .done(function (json) {
                var roles = json;
                var rolesData = $("#data");
                var tr = $("<tr>" +
                    "<th>Role id</th>" +
                    "<th>Role</th>" +
                    "</tr>"
                );

                rolesData.append(tr);

                var roleFields = [];
                for (var i = 0; i < roles.length; i++) {
                    roleFields.push(
                        "<tr>"
                        + "<td>" + roles[i].id + "</td>"
                        + "<td>" + roles[i].role + "</td>"
                        + "</tr>"
                    );
                }
                dataRows.remove();
                rolesData.append(roleFields.join(""));
            });
    } else if (dataType === 'address') {
        $.ajax({
            url: "../testexercise/show",
            data: {
                dataType: "address"
            },
            type: "GET",
            dataType: "json"
        })
            .done(function (json) {
                var addresses = json;
                var addressesData = $("#data");
                var tr = $("<tr>" +
                    "<th>Address id</th>" +
                    "<th>Address</th>" +
                    "</tr>"
                );

                addressesData.append(tr);

                var addressFields = [];
                for (var i = 0; i < addresses.length; i++) {
                    addressFields.push(
                        "<tr>"
                        + "<td>" + addresses[i].id + "</td>"
                        + "<td>" + addresses[i].address + "</td>"
                        + "</tr>"
                    );
                }
                dataRows.remove();
                addressesData.append(addressFields.join(""));
            });
    } else if (dataType === 'music') {
        $.ajax({
            url: "../testexercise/show",
            data: {
                dataType: "music"
            },
            type: "GET",
            dataType: "json"
        })
            .done(function (json) {
                var musics = json;
                var musicsData = $("#data");
                var tr = $("<tr>" +
                    "<th>Music id</th>" +
                    "<th>Genre</th>" +
                    "</tr>"
                );

                musicsData.append(tr);

                var musicFields = [];
                for (var i = 0; i < musics.length; i++) {
                    musicFields.push(
                        "<tr>"
                        + "<td>" + musics[i].id + "</td>"
                        + "<td>" + musics[i].music + "</td>"
                        + "</tr>"
                    );
                }
                dataRows.remove();
                musicsData.append(musicFields.join(""));
            });
    }

    return false;
}