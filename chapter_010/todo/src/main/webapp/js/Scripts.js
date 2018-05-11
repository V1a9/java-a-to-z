$(document).ready(function () {

    var table = $("<table/>");
    var tr;

    tr = $("<tr/>");
    $("<th/>").html("ID").appendTo(tr);
    $("<th/>").html("Description").appendTo(tr);
    $("<th/>").html("Create Date").appendTo(tr);
    $("<th/>").html("Done").appendTo(tr);

    tr.appendTo(table);
    table.insertAfter($("#all"));

    $.getJSON("../todo/tasks", function (json) {
        $.each(json, function (index, element) {
            tr = $("<tr/>");
            $("<td/>").html(element.id).appendTo(tr);
            $("<td/>").html(element.desc).appendTo(tr);
            $("<td/>").html(element.created).appendTo(tr);
            var input = $("<input/>", {type: "checkbox"});
            $("<td/>").html(input).appendTo(tr);
            if (element.done !== false) {
                input[0].checked = true;
            }
            tr.appendTo(table);
        })
    });

    $("table").on("change", "tr:gt(0) td:last-child", function (event) {
        var id = this.parentNode.cells[0].innerHTML;
        $.post("../todo/change", {id:id, done:this.children[0].checked}, function () {
            return false;
        });
    });

    $("input[type='checkbox']:eq(1)").on("change", function () {
        if (this.checked !== true) {
            $("tr:gt(0)").each(function (index, element) {
                if (element.childNodes[3].childNodes[0].checked === true) {
                    $(element).hide();
                }
            });
        } else {
            $("tr:gt(0)").each(function (index, element) {
                $(element).show();
            });
        }
    });

});

function addItem () {
    $.post("../todo/tasks", {desc:$("input[name = desc]")[0].value, done:$("input[name = done]")[0].checked}, function () {
        location.reload();
    });
    return false;
}
