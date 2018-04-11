$(document).ready(function () {
    var loggedUserForm = $("#loggedUser");
    if (loggedUserForm.length !== 0) {
        $("li.nav-item:eq(0)").hide();
        $("li.nav-item:eq(1)").hide();
    } else {
        $("li.nav-item:eq(2)").hide();
        $("li.nav-item:eq(3)").hide();
    }

    $.getJSON("./brands.do", function (json) {
        console.log(json);
        var form = $('#filter');
        var newItems = [];
        $.each(json, function (index, value) {
            newItems.push("<input  style=\"margin-left: 20px\" type=\"checkbox\" name=\"" + value.description + "\">  " + value.description + "</br>");
        });
        newItems.push("<input type=\"submit\" value=\"Apply\" style=\"margin-left: 20px\"></br>");
        form.append(newItems.join(""));
    });

    return getAdverts();

});

function getAdverts() {

    $.getJSON("./useradverts.do", function (json) {
        console.log(json);
        var table = $("table");
        var newItems = [];
        $.each(json, function (index, value) {
            var sold = value.sold ? "SOLD" : "ON SALE";
            var imgs = "<img src='img/No-image-available.jpg' class='img-thumbnail'>";
            if (value.photos.length > 0) {
                imgs = "";
                $.each(value.photos, function (index, photo) {
                    imgs = imgs + "<img src=" + photo.fileName + " alt=photo#" + index + " class='img-thumbnail'/></br>";
                });
            }
            newItems.push(
                "<tr>"
                + "<td>" + value.id + "</td>"
                + "<td><b>Brand:</b> " + value.car.brand + ";<br>"
                + "<b>VIN:</b> " + value.car.vin + ";<br>"
                + "<b>" + value.car.parts[3].type + "</b>" + ": " + value.car.parts[3].description + ";<br>"
                + "<b>" + value.car.parts[2].type + "</b>" + ": " + value.car.parts[2].description + ";<br>"
                + "<b>" + value.car.parts[1].type + "</b>" + ": " + value.car.parts[1].description + ";<br>"
                + "<b>" + value.car.parts[0].type + "</b>" + ": " + value.car.parts[0].description + ";<br>"
                + "</td>"
                + "<td>" + value.description + "</td>"
                + "<td>" + value.price + "</td>"
                + "<td>" + sold + "</td>"
                + "<td>" + new Date(value.created) + "</td>"
                + "<td>" + imgs + "</td>"
                +"</tr>"
            );

        });
        $('table tr:gt(0)').remove();
        table.append(newItems.join(""))
    });

    return false;
}

function getFilteredAdverts() {
    var data = $('#filter').serializeArray();
    console.log(data);
    if (data.length === 0) {
        getAdverts();
    } else {

        $.getJSON("./filter.do", data, function (json) {
            var table = $("table");
            var newItems = [];
            $.each(json, function (index, value) {
                var sold = value.sold ? "SOLD" : "ON SALE";
                var imgs = "<img src='img/No-image-available.jpg' class='img-thumbnail'>";
                if (value.photos.length > 0) {
                    imgs = "";
                    $.each(value.photos, function (index, photo) {
                        imgs = imgs + "<img src=" + photo + " alt=photo#" + index + " class='img-thumbnail'/></br>";
                    });
                }
                newItems.push(
                    "<tr>"
                    + "<td>" + value.id + "</td>"
                    + "<td><b>Brand:</b> " + value.car.brand + ";<br>"
                    + "<b>VIN:</b> " + value.car.vin + ";<br>"
                    + "<b>" + value.car.parts[3].type + "</b>" + ": " + value.car.parts[3].description + ";<br>"
                    + "<b>" + value.car.parts[2].type + "</b>" + ": " + value.car.parts[2].description + ";<br>"
                    + "<b>" + value.car.parts[1].type + "</b>" + ": " + value.car.parts[1].description + ";<br>"
                    + "<b>" + value.car.parts[0].type + "</b>" + ": " + value.car.parts[0].description + ";<br>"
                    + "</td>"
                    + "<td>" + value.description + "</td>"
                    + "<td>" + value.price + "</td>"
                    + "<td>" + sold + "</td>"
                    + "<td>" + new Date(value.created) + "</td>"
                    + "<td>" + imgs + "</td>"
                    +"</tr>"
                );

            });
            $('table tr:gt(0)').remove();
            table.append(newItems.join(""));
        });
    }
    return false;
}