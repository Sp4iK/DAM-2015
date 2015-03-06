var enlaces = document.getElementsByTagName("a");
var penultimo_enlace = enlaces[enlaces.length-2];
var num_enlaces = 0;
var num_enlaces_parrafo = document.getElementsByTagName("p")[2].getElementsByTagName("a").length;

for (var i=0;i<enlaces.length;i++) {
    if (enlaces[i].getAttribute("href") == "http://prueba") {
        num_enlaces++;
    }
}

console.log(enlaces.length+" enlaces.");

console.log("Penúltimo enlace: "+penultimo_enlace);

console.log(num_enlaces+" enlaces a 'http://prueba'");

console.log(num_enlaces_parrafo+" enlaces en el último párrafo");
