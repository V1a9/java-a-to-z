$(document).ready(function () {
   $.getJSON("parts.do", function (json) {
        for (var i = 0; i < json.length; i++) {
            var object = json[i];
            if (object.type === 'BRAND') {
                $("<option/>", {
                    html: object.description,
                    value: object.description
                }).appendTo($("#brand"))
            } else if (object.type === 'BODY') {
                $("<option/>", {
                    html: object.description,
                    value: object.description
                }).appendTo($("#body"));
            } else if (object.type === 'ENGINE') {
                $("<option/>", {
                    html: object.description,
                    value: object.description
                }).appendTo($("#engine"));
            } else if (object.type === 'TRANSMISSION') {
                $("<option/>", {
                    html: object.description,
                    value: object.description
                }).appendTo($("#transmission"));
            } else if (object.type === 'SUSPENSION') {
                $("<option/>", {
                    html: object.description,
                    value: object.description
                }).appendTo($("#suspension"));
            }
        }
    });
});