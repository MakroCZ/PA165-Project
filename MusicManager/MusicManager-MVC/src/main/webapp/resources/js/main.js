/**
 * This class represents a main.js scripts.
 * @author Yehor Safonov; 487596
 */

$("#menu-toggle").click(function(e) {
    e.preventDefault();
    $("#wrapper").toggleClass("toggled");
});