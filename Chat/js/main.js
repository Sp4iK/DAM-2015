$(function () {
    "use strict";

    // Obtener los elementos del DOM
    var content = $("#content");

    // Mi color asignado por el servidor
    var myColor = false;
    // Mi nick
    var myName = false;

    // Comprobar la disponibilidad de Web Socket en el navegador
//    if () {
//        return false;
//    }

    window.WebSocket = window.WebSocket || window.MozWebSocket;

    // Abrir la conexion con ws://10.70.1.111:1337
//    var socket = new WebSocket('ws://10.70.1.111:1337');
    var socket = new WebSocket('ws://localhost:1337');

    // 1. Al abrir la conexión, solicitar el nick.
    socket.onopen = function() {
        $("#status").text("Nickname?");
        $("#input").removeAttr("disabled");
    };

    // 2. Controlar posibles errores del servidor.
    // 3. Escuchar los mensajes del servidor, y mostrarlos en el elemento "content"
    socket.onmessage = function(event) {
        var data = JSON.parse(event.data);
        console.log(data);

        if (data.type == "color") {
            console.log("Color asignado: " + data.data);
            myColor = data.data;
        }

        if (data.type == "history") {
            data = data.data;

            for (var i=0; i<data.length; i++) {
                addMessage(data[i].author, data[i].text, data[i].color, new Date(data[i].time));
            }
        }

        if (data.type == "message") {
            data = data.data;

            for (var i=0; i<data.length; i++) {
                console.log(data[i].author, data[i].text, data[i].color, data[i].time);
                addMessage(data[i].author, data[i].text, data[i].color, data[i].time);
            }
        }
    };

    // 4. La estructura del objeto enviado por el servidor es la siguiente:
    //      {
    //          // Contiene el tipo de mensaje recibido
    //          type : @string in ['color', 'history', 'message'],
    //          // Contiene los datos según el tipo de mensaje recibido
    //          data: @Object {author, text, color, time}
    //      }
    // 5. Enviar un mensaje al pulsar enter. El mensaje enviado es únicamente la cadena de caracteres.

    /**
     * Añadir el mensaje a la ventana de chat
     */
    function addMessage(author, message, color, dt) {
        console.log("blabla");
        content.prepend('<p><span style="color:' + color + '">' + author + '</span> @ '
             + (dt.getHours() < 10 ? '0' + dt.getHours() : dt.getHours()) + ':'
             + (dt.getMinutes() < 10 ? '0' + dt.getMinutes() : dt.getMinutes())
             + ': ' + message + '</p>');
    }
});
