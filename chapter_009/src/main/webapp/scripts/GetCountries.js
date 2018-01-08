$(document).ready(function () {
    $.ajax("/getcountries", {
        method: 'GET',
        complete: function (data) {
            var countries = JSON.parse(data.responseText);
            var countriesList = document.getElementById("country");
            for (var i = 0; i < countries.length; i++) {
                var option = document.createElement("option");
                option.setAttribute("value", countries[i]);
                var textOption = document.createTextNode(countries[i]);
                countriesList.appendChild(option);
                option.appendChild(textOption);
            }
        }
    })
});