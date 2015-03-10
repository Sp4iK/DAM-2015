function valida_nombre() {
    var valor = document.getElementById("registro_nombre").value;

    if( valor == null || valor.length == 0 || /^\s+$/.test(valor) ) {
        console.log("campo vacio!");
        return false;
    }
}

function valida_email() {
    var valor = document.getElementById("registro_email").value;

//    if( valor == null || valor.length == 0 || !(/\w+([-+.']\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)/.test(valor)) ) {
    if( valor == null || valor.length == 0 || !(/([0-9a-zA-Z.-]+)(@)([a-zA-Z]+)([.])([a-zA-Z]{2,3})/.test(valor)) ) {
        console.log("email no válido o vacio!");
        return false;
    }
}

function valida_texto() {
    var valor = document.getElementById("registro_comentarios").value;

    if (valor == null || valor.length == 0 || /^\s+$/.test(valor)) {
        console.log("campo vacio!");
        return false;
    }
}

function valida_texto2() {
    var valor = document.getElementById("registro_comentarios").value;

    if( valor.length > 50 ) {
        console.log("campo lleno!");
        return false;
    }
}

function valida_pass() {
    var valor = document.getElementById("registro_password").value;

    if (valor.length < 6) {
        console.log("El password debe tener 6 carácteres como mínimo!");
        return false;
    }
}

document.getElementById("registro_nombre").onblur = valida_nombre;
//document.getElementById("registro_apellidos").onblur = valida_nombre;
document.getElementById("registro_email").onblur = valida_email;
document.getElementById("registro_password").onblur = valida_pass;
document.getElementById("registro_comentarios").onblur = valida_texto;
document.getElementById("registro_comentarios").onkeypress = valida_texto2;
