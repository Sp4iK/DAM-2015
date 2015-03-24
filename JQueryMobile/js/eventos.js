$(document).ready(function() {
    "use strict";

    $( document ).on( "mobileinit", function(event) {
        $(".pagestate").append($('<p/>', {html: "JQuery Mobile ha sido cargado"}));
        console.log("JQuery Mobile ha sido cargado");
    });

    $( window ).on( "hashchange", function() {
        console.log("Evento 'hashchange': "+location.hash);
    });

    $( window ).on( "orientationchange", function( event ) {
        $( ".orientation" ).text( "El dispositivo está en " + event.orientation );
    });

    $( window ).on( "scrollstart", function( event ) {
        $( ".scroll" ).text( "Has empezado a hacer scroll");
    });

    $( window ).on( "scrollstop", function( event ) {
        $( ".scroll" ).text( "Has parado el scroll");
    });

    $( window ).on( "swipe", function( event ) {
        $( ".swipe" ).text( "Has hecho 'swipe'");
    });

    $( window ).on( "swipeleft", function( event ) {
        $( ".swipe" ).text( "Has hecho 'swipe' hacia la izquierda");
    });

    $( window ).on( "swiperight", function( event ) {
        $( ".swipe" ).text( "Has hecho 'swipe' hacia la derecha");
    });

    $( window ).on( "tap", function( event ) {
        $( ".tap" ).text( "Has hecho 'tap'");
    });

    $( window ).on( "taphold", function( event ) {
        $( ".tap" ).text( "Has hecho 'taphold'");
    });

    $( window ).off( "tapend", function( event ) {
        $( ".tap" ).text( "Has dejado de hacer 'tap'");
    });

    $( window ).on( "vclick", function( event ) {
        $( ".vclick" ).text( "Has hecho clic");
    });

    $( document ).on( "vmousedown", function( event ) {
        $( ".vclick" ).text( "Estas haciendo clic");
    });

//    $( document ).on( "vmousemove", function( event ) {
//        $( ".vclick" ).text( "Has movido el ratón");
//    });

    $( ".vclick" ).on( "vmouseout", $(".vclick"), function( event ) {
        $( ".vclick" ).text( "Ya no estas moviendote por encima de este texto");
    });

    $( ".vclick" ).on( "vmouseover", $(".vclick"), function() {
        $( ".vclick" ).text( "Estas moviendote por encima de este texto" );
    });

    $( document ).on( "vmouseup", function( event ) {
        $( ".vclick" ).text( "Has hecho 'vmouseup'" );
    });

    $(document).on("pagecreate", function(event) {
        $(".pagestate").append($('<p/>', {html: "La página " + event.target.id + " ha sido creada"}));
    });

    $(document).on("pageinit", function(event) {
        $(".pagestate").append($('<p/>', {html: "La página " + event.target.id + " ha sido inicializada"}));
    });

    $(document).on("pageload", function(event) {
        $(".pagestate").append($('<p/>', {html: "La página " + event.target.id + " ha sido cargada"}));
    });

    $(document).on("pagehide", function(event) {
        $(".pagestate").append($('<p/>', {html: "La página " + event.target.id + " ha sido ocultada"}));
    });
});
