function Persona (nombre, edad, genero) {
    this.nombre = nombre || "";
    this.edad = edad || "";
    this.genero = genero || "";
    this.obtDetalles = function() {
       alert(this.nombre+", de "+this.edad+" años y "+this.genero);
    };
}

function Estudiante (nombre, edad, genero, curso, grupo) {
    this.base = Persona;
    this.base(nombre, edad, genero);
    this.curso = curso || "";
    this.grupo = grupo || "";
    this.registrar = function(){};
}
Estudiante.prototype = new Persona;

function Profesor (nombre, edad, genero, asignatura, nivel) {
    this.base = Persona;
    this.base(nombre, edad, genero);
    this.asignatura = asignatura || "";
    this.nivel = nivel || "";
    this.asignar = function(){};
}
Profesor.prototype = new Persona;

var profe = new Profesor("Profe", 30, "hombre", "HTML", "");

var estudiante = new Estudiante("Estudiante", 25, "mujer", "primero", "A");

profe.obtDetalles();
estudiante.obtDetalles();
