$(document).ready(function () {
    return getAdvert();
});

function getAdvert() {
    $.getJSON("getadvert.do", {
        id : $('table tr:eq(1) td:eq(0)').html()
    }).done(function(json) {
        var table = $("table tr:eq(1)");
        var newItems = [];
        var sold = json.sold ? "SOLD" : "ON SALE";
        var imgs = "<img src='img/No-image-available.jpg' class='img-thumbnail'>";
        if (json.photos.length > 0) {
            imgs ="";
            $.each(json.photos, function (index, photo) {
                imgs = imgs + "<img src=" + photo.fileName + " alt=photo#" + index + " class='img-thumbnail'/></br>";
            });
        }
        newItems.push(
            "<td><b>Brand:</b>" + json.car.brand + ";<br>"
            + "<b>VIN:</b> " + json.car.vin + ";<br>"
            + "<b>" + json.car.parts[3].type + "</b>" + ": " + json.car.parts[3].description + ";<br>"
            + "<b>" + json.car.parts[2].type + "</b>" + ": " + json.car.parts[2].description + ";<br>"
            + "<b>" + json.car.parts[1].type + "</b>" + ": " + json.car.parts[1].description + ";<br>"
            + "<b>" + json.car.parts[0].type + "</b>" + ": " + json.car.parts[0].description + ";<br>"
            + "</td>"
            + "<td>" + json.description + "</td>"
            + "<td>" + json.price + "</td>"
            + "<td>" + sold + "</td>"
            + "<td>" + new Date(json.created) + "</td>"
            + "<td>" + imgs + "</td>"
        );

        table.append(newItems.join(""));

    }).fail(function () {
        console.log("Error!");
    });

    return false;
}

function redirectMain() {
    window.location.href = 'mainview.do';
}