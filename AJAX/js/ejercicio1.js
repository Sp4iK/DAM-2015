var estados = [
    "READY_STATE_UNINITIALIZED",
    "READY_STATE_LOADING",
    "READY_STATE_LOADED",
    "READY_STATE_INTERACTIVE",
    "READY_STATE_COMPLETE"
];

var peticion_http;

function cargaContenido(url, metodo, funcion) {
    peticion_http = inicializa_xhr();

    if(peticion_http) {
        peticion_http.onreadystatechange = funcion;
        peticion_http.open(metodo, url, true);
        peticion_http.send(null);
    }
}

function inicializa_xhr() {
    if(window.XMLHttpRequest) {
        return new XMLHttpRequest();
    }
    else if(window.ActiveXObject) {
        return new ActiveXObject("Microsoft.XMLHTTP");
    }
}

function muestraUrl() {
    document.getElementById("recurso").value = document.URL;
}

function muestraContenido() {
    console.log(peticion_http.readyState);
    document.getElementById("estados").textContent += estados[peticion_http.readyState] + "\n";

    if (peticion_http.readyState == 4) {
        if (peticion_http.status == 200) {
            document.getElementById("contenidos").textContent = peticion_http.responseText;
            document.getElementById("cabeceras").textContent = peticion_http.getAllResponseHeaders;
            document.getElementById("codigo").textContent = peticion_http.status + ": " + peticion_http.statusText;
        }
    }
}

function descargaContenido() {
    var contenido = document.getElementById("recurso").value;

    cargaContenido(contenido, "GET", muestraContenido);
}

window.onload = muestraUrl;
document.getElementById("enviar").onclick = descargaContenido;
