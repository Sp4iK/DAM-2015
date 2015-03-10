var fecha_nacimiento = "Nací el 05/04/1982 en Donostia.";
var email = "e@mail.com";
var nombre_apellido = "Markel Flores";
var etiqueta = "<p>Lorem ipsum dolor sit amet, <script>consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, <script>quis nostrud</script> exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse <script>cillum</script> dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.</script></p>";

//var regexp1 = /\d{2}\/\d{2}\/\d{2,4}/;
var regexp1 = /(0[1-9]|[1-2][0-9]|30|31)\/(0[1-9]|1[0-2])\/(\d{4})/;
//var regexp2 = /([0-9a-zA-Z.-]+)(@)([a-zA-Z]+)([.])([a-zA-Z]{2,3})/;
var regexp2 = /^(\w+)((\.|-|_)(\w+))*@(\w+)(\.\w{2,})+$/;
var regexp3 = /([a-zA-Z]+)\s([a-zA-Z]+)/;
//var regexp4 = /<script\w?>.*?<\/script>/g;
var regexp4 = /<script\w*?>.*?(<script\w*?>.*?<\/script>\w*?)*<\/script>\w*?/g
var regexp5 = /[<>&"]/g;

console.log("Texto: "+fecha_nacimiento+"\n"+"Fecha de nacimiento: "+fecha_nacimiento.match(regexp1));

console.log("Email: "+email+"\n"+"Email correcto? "+regexp2.test(email));

var resultado = nombre_apellido.match(regexp3);
console.log("Original: "+nombre_apellido+"\n"+"Resultado: "+resultado[2]+", "+resultado[1]);

console.log("Original: "+etiqueta+"\n"+"Resultado: "+etiqueta.replace(regexp4,""));
