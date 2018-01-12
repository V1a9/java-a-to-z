$(document).ready(function () {
    $("#country").change(function () {
        $.ajax("../ustorage/getcities", {
            method: 'POST',
            data: {country: $('#country').valueOf()[0].value},
            complete: function (data) {
                var cities = JSON.parse(data.responseText);
                var citiesList = document.getElementById("city");
                var optionsInForm = citiesList.getElementsByTagName("option");
                var option;
                if (optionsInForm.length > 1) {
                    do {
                        citiesList.removeChild(optionsInForm.item(1));
                    } while (optionsInForm.length !== 1);
                }
                for (var j = 0; j < cities.length; j++) {
                    option = document.createElement("option");
                    var text = document.createTextNode(cities[j]);
                    option.setAttribute("value", cities[j]);
                    option.appendChild(text);
                    citiesList.appendChild(option);
                }
            }
        })
    })
});