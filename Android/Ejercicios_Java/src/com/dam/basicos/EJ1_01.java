package com.dam.basicos;

public class EJ1_01 {

	/**
	 * Crear dos variables de cualquiera de los tipos baÌ�sicos, dar un valor a la primera
	 * y a continuacioÌ�n asigne la segunda a la primera. Imprimir por pantalla las dos variables.
	 * Cambiar el valor de la segunda variable y volver a imprimir las variables por pantalla.
	 * Â¿QueÌ� es lo que ocurre?
	 *
	 * @param args
	 */

	public static void main(String[] args) {

		int a = 0;
		int b = a;

		System.out.println("A es: " + a);
		System.out.println("B es: " + b);

		b = 1;

		System.out.println("Ahora A es: " + a);
		System.out.println("Ahora B es: " + b);
	}

}
