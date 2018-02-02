$(document).ready(function () {
    var userRole = $("title").get(0).textContent;
    if (userRole.search('USER') !== -1 || userRole.search('MODERATOR') !== -1) {
        $(".btn-group").eq(1).hide();
    }
    return false;
});