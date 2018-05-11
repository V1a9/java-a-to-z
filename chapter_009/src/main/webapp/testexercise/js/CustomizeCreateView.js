$(document).ready(function () {
    var entity = $("title").get(0).textContent.split(" ")[1];
    $('.container').filter(function (index, element) {
        return element.children[1].id !== entity;
    }).hide();
});