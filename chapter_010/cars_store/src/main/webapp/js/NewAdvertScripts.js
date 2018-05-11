$(document).ready(function () {
   $.getJSON("parts", function (json) {
        for (var i = 0; i < json.length; i++) {
            var object = json[i];
            if (object.type === 'BRAND') {
                $("<option/>", {
                    html: object.desc,
                    value: object.desc
                }).appendTo($("#brand"))
            } else if (object.type === 'BODY') {
                $("<option/>", {
                    html: object.desc,
                    value: object.desc
                }).appendTo($("#body"));
            } else if (object.type === 'ENGINE') {
                $("<option/>", {
                    html: object.desc,
                    value: object.desc
                }).appendTo($("#engine"));
            } else if (object.type === 'TRANSMISSION') {
                $("<option/>", {
                    html: object.desc,
                    value: object.desc
                }).appendTo($("#transmission"));
            } else if (object.type === 'SUSPENSION') {
                $("<option/>", {
                    html: object.desc,
                    value: object.desc
                }).appendTo($("#suspension"));
            }
        }
    });
});