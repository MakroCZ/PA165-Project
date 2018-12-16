/**
 * This class represents application scripts.
 * @author Yehor Safonov; 487596
 */

$("#menu-toggle").click(function(e) {
    e.preventDefault();
    $("#wrapper").toggleClass("toggled");
});