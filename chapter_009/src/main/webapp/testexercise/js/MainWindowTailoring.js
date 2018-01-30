function showData(dataType) {
    var users;
    var roles;
    var dataRows = $("#data tr");
    if (dataType.toLowerCase() === 'users') {
        $.ajax({
            url: "../testexercise/show",
            data: {
                dataType: "users"
            },
            type: "GET",
            dataType: "json"
        })
            .done(function (json) {
                users = json;
            });

        $.ajax({
            url: "../testexercise/show",
            data: {
                dataType: "roles"
            },
            type: "GET",
            dataType: "json"
        })
            .done(function (json) {
                var usersData = $("#data");
                roles = json;
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
                        + "<td>" + userRole + "</td>"
                        +"</tr>"
                    );
                }
                dataRows.remove();
                usersData.append(userFields.join(""));
            });
    } else if (dataType.toLowerCase() === 'roles') {
        $.ajax({
            url: "../testexercise/show",
            data: {
                dataType: "roles"
            },
            type: "GET",
            dataType: "json"
        })
            .done(function (json) {
                roles = json;
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
    } else if (dataType.toLowerCase() === 'addresses') {
        $.ajax({
            url: "../testexercise/show",
            data: {
                dataType: "addresses"
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
    } else if (dataType.toLowerCase() === 'musics') {
        $.ajax({
            url: "../testexercise/show",
            data: {
                dataType: "musics"
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