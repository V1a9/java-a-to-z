$(document).ready(function () {
    return getAdvert();
});

function getAdvert() {
    $.getJSON("advert", {
        id : $('table tr:eq(1) td:eq(0)').html()
    }).done(function(json) {
        var table = $("table tr:eq(1)");
        var newItems = [];
        var sold = json.advert.sold ? "SOLD" : "ON SALE";
        var imgs = "<img src='img/No-image-available.jpg' class='img-thumbnail'>";
        if (json.photos[0].length > 0) {
            imgs ="";
            $.each(json.photos[0], function (index, photo) {
                imgs = imgs + "<img src=" + photo + " alt=photo#" + index + " class='img-thumbnail'/></br>";
            });
        }
        newItems.push(
            "<td><b>Brand:</b>" + json.car.brand + ";<br>"
            + "<b>VIN:</b> " + json.car.vin + ";<br>"
            + "<b>" + json.parts[0][3].type + "</b>" + ": " + json.parts[0][3].desc + ";<br>"
            + "<b>" + json.parts[0][2].type + "</b>" + ": " + json.parts[0][2].desc + ";<br>"
            + "<b>" + json.parts[0][1].type + "</b>" + ": " + json.parts[0][1].desc + ";<br>"
            + "<b>" + json.parts[0][0].type + "</b>" + ": " + json.parts[0][0].desc + ";<br>"
            + "</td>"
            + "<td>" + json.advert.desc + "</td>"
            + "<td>" + json.advert.price + "</td>"
            + "<td>" + sold + "</td>"
            + "<td>" + new Date(json.advert.created) + "</td>"
            + "<td>" + imgs + "</td>"
        );

        table.append(newItems.join(""));

    }).fail(function () {
        console.log("Error!");
    });

    return false;
}

function redirectMain() {
    window.location.href = 'mainview';
}