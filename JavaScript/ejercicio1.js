var letras = ['T', 'R', 'W', 'A', 'G', 'M', 'Y', 'F', 'P', 'D', 'X', 'B', 'N', 'J', 'Z', 'S', 'Q', 'V', 'H', 'L', 'C', 'K', 'E', 'T'];
var numero = 44162532;
var letra = "W";

if (numero < 0 || numero > 99999999) {
    alert("El número proporcionado no es válido");
} else {
    letra = letra.toUpperCase();
    var resultado = numero % 23;
    var letraCorrecta = letras[resultado];

    if (letraCorrecta === letra) {
        alert("El número de DNI y letra indicados son correctos");
    } else {
        alert("La letra indicada no es válida");
    }
}
