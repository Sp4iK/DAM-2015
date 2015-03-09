function valida_nombre() {
    var valor = document.getElementById("registro_nombre").value;

    if( valor == null || valor.length == 0 || /^\s+$/.test(valor) ) {
//        document.getElementById("registro_nombre").value = "¡Falta este campo!";
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

    if( valor == null || valor.length == 0 || valor.length > 50 || /^\s+$/.test(valor) ) {
        console.log("campo vacio!");
        return false;
    }
}

document.getElementById("registro_nombre").onblur = valida_nombre;
document.getElementById("registro_email").onblur = valida_email;
document.getElementById("registro_comentarios").onblur = valida_texto;
