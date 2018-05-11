$(document).ready(function () {
   $.getJSON("../testexercise/entities", {entity:"role"}, function (resp) {
       var roles = $("#selRole");
       $.each(resp, function (index, element) {
           $("<option>" + element + "</option>").appendTo(roles);
       })
   })
});