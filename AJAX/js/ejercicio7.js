window.onload = function() {
    var READY_STATE_COMPLETE = 4;
    var peticion_http = null;

    function valida(url, query, funcion) {
        peticion_http = inicializa_xhr();

        if (peticion_http) {
            peticion_http.onreadystatechange = funcion;
            peticion_http.open("POST", url, true);
            peticion_http.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
            var query_string = query + "&nocache=" + Math.random();
            peticion_http.send(query_string);
            console.log(query_string);
        }
    }

    function inicializa_xhr() {
        if (window.XMLHttpRequest) {
            return new XMLHttpRequest();
        } else if (window.ActiveXObject) {
            return new ActiveXObject("Microsoft.XMLHTTP");
        }
    }

    function procesaProvincias() {
        if (peticion_http.readyState == READY_STATE_COMPLETE) {
            if (peticion_http.status == 200) {
                var respuesta = parsearRespuesta(peticion_http.responseText);

                document.getElementById("provincia").innerHTML = respuesta;
            }
        }
    }

    function procesaMunicipios() {
        if (peticion_http.readyState == READY_STATE_COMPLETE) {
            if (peticion_http.status == 200) {
                var respuesta = parsearRespuesta(peticion_http.responseText);

                document.getElementById("municipio").innerHTML = respuesta;
            }
        }
    }

    function parsearRespuesta(respuesta) {
        var objeto_json = JSON.parse(respuesta);
        objeto_json.sort;
        console.log(objeto_json);
        var respuestaParseada;

        for (var codigo in objeto_json) {
            respuestaParseada += "<option value=" + codigo + ">" + objeto_json[codigo] + "</option>";
            console.log(respuestaParseada);
        }

        return respuestaParseada;
    }

    document.getElementById("provincia").onchange = municipios;

    function provincias() {
        valida("php/cargaProvinciasJSON.php", null, procesaProvincias);
    }

    function municipios() {
        var codigoProvincia = document.getElementById("provincia").selectedIndex;
        console.log(codigoProvincia);
        valida("php/cargaMunicipiosJSON.php", "provincia="+codigoProvincia, procesaMunicipios);
    }

    provincias();
};
