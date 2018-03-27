$(document).ready(function () {
    $('table').on('change', 'select', function (event) {
        var id = event.target.offsetParent.parentElement.cells[0].innerHTML;
        return changeStatus(id);
    });
    return getAdverts(false);
});

function getAdverts(showActive) {
    var address;
    if (showActive === true) {
        address = 'useradverts?showActive=true';
        $("h2:eq(1)").html('All active advertisements:');
    } else {
        address = 'useradverts?showActive=false';
        $("h2:eq(1)").html('Your advertisements:');
    }
    $.getJSON(address, function (json) {
        var table = $("table");
        var newItems = [];
        $.each(json, function (index, value) {
            var imgs = "<img src='img/No-image-available.jpg' class='img-thumbnail'>";
            if (value.photos[0].length > 0) {
                imgs = "";
                $.each(value.photos[0], function (index, photo) {
                    imgs = imgs + "<img src=" + photo + " alt=photo#" + index + " class='img-thumbnail'/></br>";
                });
            }

            var status;
            if (showActive === true) {
                status = value.advert.sold ? "SOLD" : "ON SALE";
            } else {
                if (value.advert.sold) {
                    status =
                        "<form>"
                        + "<select name='status'>"
                        +   "<option value='SOLD'>SOLD</option>"
                        +   "<option value='ON SALE'>ON SALE</option>"
                        + "</select>"
                        +"</form>";
                } else {
                    status =
                        "<form>"
                        + "<select name='status'>"
                        +   "<option value='ON SALE'>ON SALE</option>"
                        +   "<option value='SOLD'>SOLD</option>"
                        + "</select>"
                        +"</form>";
                }
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
                + "<td>" + status + "</td>"
                + "<td>" + new Date(value.advert.created) + "</td>"
                + "<td>" + imgs + "</td>"
                +"</tr>"
            );

        });
        $('table tr:gt(0)').remove();
        table.append(newItems.join(""));
    });
    return true;
}

function changeStatus(id) {
    console.log(id);
    $.post('change', {
        advertId : id
    }).done(function () {
        console.log("Success")
    }).fail(function () {
        console.log("Error")
    });
    return false;
}

function getWithPhotos() {
    return true;
}