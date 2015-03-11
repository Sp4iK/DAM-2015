window.onload = function() {

    var READY_STATE_COMPLETE = 4;
    var peticion_http = null;

    document.getElementById("comprobar").onclick = valida;

    function valida() {
        peticion_http = inicializa_xhr();

        if (peticion_http) {
            peticion_http.onreadystatechange = procesaRespuesta;
            peticion_http.open("POST", "php/compruebaDisponibilidadJSON.php", true);
            peticion_http.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
            var query_string = "login=" + document.getElementById("login").value + "&nocache=" + Math.random();
            peticion_http.send(query_string);
        }
    }

    function inicializa_xhr() {
        if (window.XMLHttpRequest) {
            return new XMLHttpRequest();
        } else if (window.ActiveXObject) {
            return new ActiveXObject("Microsoft.XMLHTTP");
        }
    }

    function procesaRespuesta() {
        if (peticion_http.readyState == READY_STATE_COMPLETE) {
            if (peticion_http.status == 200) {
                var respuesta = parsearRespuesta(peticion_http.responseText);

                document.getElementById("disponibilidad").innerHTML = respuesta;
            }
        }
    }

    function parsearRespuesta (respuesta) {
        var objeto_json = JSON.parse(respuesta);
        console.log(objeto_json);

        if (objeto_json.disponible == "si") {
            respuesta = "<p>El nombre de usuario introducido " + objeto_json.disponible + " está disponible.</p>";
        } else {
            respuesta = "<p>El nombre de usuario introducido " + objeto_json.disponible + " está disponible.</p>";
            respuesta += "<p>Puede elegir entre las siguientes alternativas:</p>";
            respuesta += "<ul>";

            for (var i=0; i < objeto_json.alternativas.length; i++) {
                respuesta += "<li>" + objeto_json.alternativas[i] + "</li>";
            }

            respuesta += "</ul>";
        }

        return respuesta;
    }
};
