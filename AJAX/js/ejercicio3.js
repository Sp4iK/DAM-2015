var READY_STATE_COMPLETE=4;
var peticion_http = null;

function inicializa_xhr() {
    console.log("inicializa_xhr");
    if(window.XMLHttpRequest) {
        return new XMLHttpRequest();
    }
    else if(window.ActiveXObject) {
        return new ActiveXObject("Microsoft.XMLHTTP");
    }
}

//function crea_query_string() {
//    var fecha = document.getElementById("fecha_nacimiento");
//    var cp = document.getElementById("codigo_postal");
//    var telefono = document.getElementById("telefono");
//
//    return "fecha_nacimiento=" + encodeURIComponent(fecha.value) +
//        "&codigo_postal=" + encodeURIComponent(cp.value) +
//        "&telefono=" + encodeURIComponent(telefono.value) +
//        "&nocache=" + Math.random();
//}

function valida() {
    console.log("valida");
    peticion_http = inicializa_xhr();

    if (peticion_http) {
        peticion_http.onreadystatechange = procesaRespuesta;
        peticion_http.open("POST", "http://localhost/compruebaDisponibilidad.php", true);
        peticion_http.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
        var query_string = document.getElementById("login").value + "&nocache=" + Math.random();
        peticion_http.send(query_string);
    }
}

function procesaRespuesta() {
    console.log("procesaRespuesta");
    if (peticion_http.readyState == READY_STATE_COMPLETE) {
        if (peticion_http.status == 200) {
            document.getElementById("disponibilidad").textContent = peticion_http.responseText;
        }
    }
}

document.getElementById("comprobar").onclick = valida;
