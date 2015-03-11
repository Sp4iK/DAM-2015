window.onload = function() {
    console.log("cargado");

    var READY_STATE_COMPLETE = 4;
    var peticion_http = null;

    document.getElementById("comprobar").onclick = valida;

    function valida() {
        console.log("valida");

        peticion_http = inicializa_xhr();

        if (peticion_http) {
            peticion_http.onreadystatechange = procesaRespuesta;
            peticion_http.open("POST", "php/compruebaDisponibilidad.php", true);
            peticion_http.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
            var query_string = document.getElementById("login").value + "&nocache=" + Math.random();
            peticion_http.send(query_string);
        }
    }

    function inicializa_xhr() {
        console.log("inicializa_xhr");

        if (window.XMLHttpRequest) {
            return new XMLHttpRequest();
        } else if (window.ActiveXObject) {
            return new ActiveXObject("Microsoft.XMLHTTP");
        }
    }

    function procesaRespuesta() {
        console.log("procesaRespuesta: ReadyState="+peticion_http.readyState+" | Status="+peticion_http.status);

        if (peticion_http.readyState == READY_STATE_COMPLETE) {
            if (peticion_http.status == 200) {
                document.getElementById("disponibilidad").textContent = "El nombre de usuario introducido " + peticion_http.responseText + " está disponible.";
            }
        }
    }
};
