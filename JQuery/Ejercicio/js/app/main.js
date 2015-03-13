//objeto global.
var APP = APP || {};

//esperar a que el DOM este cargado.
$(document).ready(function(){
    "use strict";

    // Iniciaremos las variables privadas que sean necesarias.
    var filas = 0;
    var columnas = 0;
    var tiempo = 0;
    var animales = [];

    // Necesitaremos una funcion que cree objetos DOM de clase img y todos los atributos necesarios.
    // Necesitaremos una funcion que agrege el array de img al DOM (no se realizaran cambios de DOM dentro de ningun bucle).

    // Necesitaremos una o varias funcion(es) que controle(n) el paso del tiempo.

    // Necesitaremos añadir los eventos necesarios en el momento adecuado.
    $("#carga").on("click", function () {
        console.log("has hecho click!");
        prepara();
    });

    var control = function(){
        var a = $("#contenedor img");

        for (var i=0;i<a.length;i++) {
            if ($(a[i]).data("tiempo") < tiempo) {
                $(a[i]).addClass($(a[i]).data("color"));
            }

            tiempo++;
        }
    };

    var callback = function cb(json) {
        console.log("funcion callback");

        for (var animal in json) {
            animales.push($('<img/>', {
//                'class': json[animal].color,
                src: 'img/'+json[animal].animal+'.png',
                width: 100/(json.length/filas)+'%',
                'data-tiempo': json[animal].timer,
                'data-color': json[animal].color
            }));
        }

        $("#contenedor").append(animales);

        var id = setInterval(control, 1000);
    };

    // Necesitaremos las funciones de callback para los eventos.
    function prepara() {
        filas = $("#filas").val();
        columnas = $("#columnas").val();
        var numero = filas * columnas;

        $("#botones").addClass("hidden");
        $("#eliminados").removeClass("hidden");
        $("#contenedor").removeClass("hidden");

        APP.net.respuesta(numero, callback);
    }

    // Necesitaremos una funcion encargada de llamar al modulo que se define en el fichero net.js con los parametros adecuados para realizar la llamada y capturar la respuesta AJAX.

    // Necesitaremos una funcion que controle el final del juego.

    // Necesitaremos una funcion que controle la lista de eliminados.

    // Cualquier otra funcion que sea necesaria.



});
