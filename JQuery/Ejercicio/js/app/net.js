//objeto global
var APP = APP || {};

//esperar a que el DOM este cargado
$(document).ready(function () {
    "use strict";

    //Funcion anonima autoejecutable para realizar las peticiones AJAX, que añadiremos en el objeto global
    //y expondra un metodo para poder realizar las llamadas al servidor.
    APP.net = (function () {
        var url = "php/getcards.php";

        var respuesta = function(cnt,callback) {
            (cnt !== null || cnt !== 0) ? cnt : 1;

            $.ajax({
                // la URL para la petición
                url: url,

                // la información a enviar
                // (también es posible utilizar una cadena de datos)
                data: {
                    numero: cnt
                },

                // especifica si será una petición POST o GET
                type: 'POST',

                // el tipo de información que se espera de respuesta
                dataType: 'json',

                // código a ejecutar si la petición es satisfactoria;
                // la respuesta es pasada como argumento a la función
                success: function (json) {
                    callback(json);
                },

                // código a ejecutar si la petición falla;
                // son pasados como argumentos a la función
                // el objeto jqXHR (extensión de XMLHttpRequest), un texto con el estatus
                // de la petición y un texto con la descripción del error que haya dado el servidor
                error: function (jqXHR, status, error) {
                    console.log('Disculpe, existió un problema');
                },

                // código a ejecutar sin importar si la petición falló o no
                complete: function (jqXHR, status) {
                    console.log('Petición realizada');
                }
            });
        }

        return {respuesta: respuesta};
    })();
});

//El servidor espera un parametro 'numero' con el número de elementos que tendra el JSON de respuesta.

