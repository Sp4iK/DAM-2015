package com.dam.basicos;

public class EJ1_02 {

	/**
	 * A partir de una variable num1 con valor inicial 12 y una variable num2 con valor inicial 4,
	 * crear nuevas variables que almacenen el resultado de realizar la suma,
	 * resta, multiplicacioÌ�n, divisioÌ�n y resto de num1 y num2.
	 * Mostrar el valor de las nuevas variables por pantalla.
	 *
	 * @param args
	 */

	public static void main(String[] args) {

		int num1 = 12;
		int num2 = 4;
		int suma = num1 + num2;
		int resta = num1 - num2;
		int multiplicacion = num1 * num2;
		int division = num1 / num2;
		int resto = num1 % num2;

		System.out.println("Suma: " + suma);
		System.out.println("Resta: " + resta);
		System.out.println("Multiplicación: " + multiplicacion);
		System.out.println("División: " + division);
		System.out.println("Resto: " + resto);
	}

}
