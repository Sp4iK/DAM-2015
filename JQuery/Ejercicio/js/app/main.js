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
    var id;
    var tipos = {dog: 'perro', cat: 'gato'};
    var colores = {"red": 'rojo', "green": 'verde', "blue": 'azul', "yellow": 'amarillo', "purple": 'púrpura', "orange": 'naranja', "black": 'negro', "pink": 'rosa'};

    // Necesitaremos una funcion que cree objetos DOM de clase img y todos los atributos necesarios.
    // Necesitaremos una funcion que agrege el array de img al DOM (no se realizaran cambios de DOM dentro de ningun bucle).

    // Necesitaremos una o varias funcion(es) que controle(n) el paso del tiempo.

    // Necesitaremos añadir los eventos necesarios en el momento adecuado.
    $("#carga").on("click", function () {
        prepara();
    });

    var clicado = function() {
        var tipo = tipos[$(this).data("tipo")];
        var color = colores[$(this).data("color")];

        $(this).addClass("hidden");
        $("#eliminados").append($('<p/>', {html: Date() + ': ' + $(this).data("nombre") + ', el '+tipo+' '+color}));
    };

    var control = function() {
        var a = $("#contenedor img");

        for (var i=0;i<a.length;i++) {
            if ($(a[i]).data("tiempo") < tiempo) {
                $(a[i]).addClass($(a[i]).data("color"));
                $(a[i]).on("mouseup", clicado);
            }
        }

        tiempo++;
    };

    var callback = function cb(json) {
        for (var animal in json) {
            animales.push($('<img/>', {
                src: 'img/'+json[animal].animal+'.png',
                width: 100/(json.length/filas)+'%',
                'data-tiempo': json[animal].timer,
                'data-color': json[animal].color,
                'data-tipo': json[animal].animal,
                'data-nombre': json[animal].nombre
            }));
        }

        $("#contenedor").append(animales);

        id = setInterval(control, 1000);
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
