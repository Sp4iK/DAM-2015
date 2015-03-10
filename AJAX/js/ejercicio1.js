var READY_STATE_UNINITIALIZED=0;
var READY_STATE_LOADING=1;
var READY_STATE_LOADED=2;
var READY_STATE_INTERACTIVE=3;
var READY_STATE_COMPLETE=4;

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
    var parrafoEstado = document.createElement("p");
    var parrafoCodigo = document.createElement("p");
    var textoEstado = document.createTextNode("");
    var textoCodigo = document.createTextNode("");
    parrafoEstado.appendChild(textoEstado);
    parrafoCodigo.appendChild(textoCodigo);
    document.getElementById("estados").appendChild(parrafoEstado);
    document.getElementById("codigo").appendChild(parrafoCodigo);

    switch (peticion_http.readyState) {
        case READY_STATE_UNINITIALIZED:
            textoEstado.nodeValue = "READY_STATE_UNINITIALIZED";
            break;
        case READY_STATE_LOADING:
            textoEstado.nodeValue = "READY_STATE_LOADING";
            break;
        case READY_STATE_LOADED:
            textoEstado.nodeValue = "READY_STATE_LOADED";
            break;
        case READY_STATE_INTERACTIVE:
            textoEstado.nodeValue = "READY_STATE_INTERACTIVE";
            break;
        case READY_STATE_COMPLETE:
            textoEstado.nodeValue = "READY_STATE_COMPLETE";
            textoCodigo.nodeValue = peticion_http.status + ": " + peticion_http.statusText;

            if(peticion_http.status == 200) {
                var parrafoContenido = document.createElement("p");
                var textoContenido = document.createTextNode("blabla");
                document.getElementById("contenidos").appendChild(parrafoContenido.appendChild(textoContenido));
                document.getElementById("cabeceras").appendChild(document.createElement("p").appendChild(document.createTextNode(""));
            }

            break;
    }
}

function descargaContenido() {
    var contenido = document.getElementById("recurso").value;

    cargaContenido(contenido, "GET", muestraContenido);
}

window.onload = muestraUrl;
document.getElementById("enviar").onclick = descargaContenido;
