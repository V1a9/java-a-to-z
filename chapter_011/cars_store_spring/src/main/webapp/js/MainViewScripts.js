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
        address = 'useradverts.do?showActive=true';
        $("h2:eq(1)").html('All active advertisements:');
    } else {
        address = 'useradverts.do?showActive=false';
        $("h2:eq(1)").html('Your advertisements:');
    }
    $.getJSON(address, function (json) {
        var table = $("table");
        var newItems = [];
        $.each(json, function (index, value) {
            var imgs = "<img src='img/No-image-available.jpg' class='img-thumbnail'>";
            if (value.photos.length > 0) {
                imgs = "";
                $.each(value.photos, function (index, photo) {
                    imgs = imgs + "<img src=" + photo.fileName + " alt=photo#" + index + " class='img-thumbnail'/></br>";
                });
            }

            var status;
            if (showActive === true) {
                status = value.sold ? "SOLD" : "ON SALE";
            } else {
                if (value.sold) {
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
                + "<td>" + status + "</td>"
                + "<td>" + new Date(value.created) + "</td>"
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
    $.post('change.do', {
        advertId : id
    }).done(function () {
        console.log("Success")
    }).fail(function () {
        console.log("Error")
    });
    return false;
}