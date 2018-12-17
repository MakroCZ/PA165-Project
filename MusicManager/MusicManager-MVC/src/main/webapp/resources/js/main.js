/**
 * This class represents application scripts.
 * @author Yehor Safonov; 487596
 */

$("#menu-toggle").click(function(e) {
    e.preventDefault();
    $("#wrapper").toggleClass("toggled");
});

$(document).ready(function() {
     $("#search-button").click(function(e) {
        e.preventDefault();
        var data = $("#search-name").val();
        var href = $(this).attr('href');
        window.location = href+data;
    });
});