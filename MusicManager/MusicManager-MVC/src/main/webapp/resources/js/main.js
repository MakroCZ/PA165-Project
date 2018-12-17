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
//       alert("Search button action");
       var data = $("#search-name").val();
//       alert(data);
       var href = $(this).attr('href');
//       alert(href);
       var name = "http://"+window.location.host+href+data;
       window.location = href+data;
//       window.location.reload();
//       alert(name);
//       window.open(name);

//       window.open(name);
//       window.close();
   });
});