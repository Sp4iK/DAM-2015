package com.dam.basicos;

public class EJ1_03 {

	/**
	 * Construir un programa que, dado el radio de una esfera, calcule y devuelva por pantalla
	 * el valor de la superficie y el volumen de la esfera correspondiente.
	 * Para obtener el valor de PI, utilizar la variable estaÌ�tica Math.PI.
	 * @param args
	 */

	public static void main(String[] args) {
		int radio = 5;

		double superficie = 4 * Math.PI * (radio*radio);
		double volumen = (4/3) * Math.PI * Math.pow(radio,3);

		System.out.println("El radio de la esfera es: " + radio);
		System.out.println("La superficie de la esfera: " + superficie);
		System.out.println("El Volumen de la esfera: " + volumen);
	}

}
