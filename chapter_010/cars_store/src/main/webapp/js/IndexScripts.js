$(document).ready(function () {
    var loggedUserForm = $("#loggedUser");
    if (loggedUserForm.length !== 0) {
        $("li.nav-item:eq(0)").hide();
        $("li.nav-item:eq(1)").hide();
    } else {
        $("li.nav-item:eq(2)").hide();
        $("li.nav-item:eq(3)").hide();
    }

    return getAdverts();

});

function getAdverts() {
    $.getJSON("./useradverts", function (json) {
        var table = $("table");
        var newItems = [];
        $.each(json, function (index, value) {
            var sold = value.advert.sold ? "SOLD" : "ON SALE";
            var imgs = "<img src='img/No-image-available.jpg' class='img-thumbnail'>";
            if (value.photos[0].length > 0) {
                imgs = "";
                $.each(value.photos[0], function (index, photo) {
                    imgs = imgs + "<img src=" + photo + " alt=photo#" + index + " class='img-thumbnail'/></br>";
                });
            }
            newItems.push(
                "<tr>"
                + "<td>" + value.advert.id + "</td>"
                + "<td><b>Brand:</b> " + value.car.brand + ";<br>"
                + "<b>VIN:</b> " + value.car.vin + ";<br>"
                + "<b>" + value.parts[0][3].type + "</b>" + ": " + value.parts[0][3].desc + ";<br>"
                + "<b>" + value.parts[0][2].type + "</b>" + ": " + value.parts[0][2].desc + ";<br>"
                + "<b>" + value.parts[0][1].type + "</b>" + ": " + value.parts[0][1].desc + ";<br>"
                + "<b>" + value.parts[0][0].type + "</b>" + ": " + value.parts[0][0].desc + ";<br>"
                + "</td>"
                + "<td>" + value.advert.desc + "</td>"
                + "<td>" + value.advert.price + "</td>"
                + "<td>" + sold + "</td>"
                + "<td>" + new Date(value.advert.created) + "</td>"
                + "<td>" + imgs + "</td>"
                +"</tr>"
            );

        });
        table.append(newItems.join(""))
    });

    return false;
}