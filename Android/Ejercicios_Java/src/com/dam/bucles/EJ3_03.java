package com.dam.bucles;

public class EJ3_03 {

	/**
	 * Construir un programa en el que, utilizando un bucle for, se escriba por pantalla una tabla de
	 * conversioÌ�n de grados Fahrenheit a Celsius, para los valores de 0 hasta 300 grados, en intervalos de 20.
	 *
	 * @param args
	 */

	public static void main(String[] args) {

		System.out.println("| ºF | ºC |");

		for (int i=0;i<301;i+=20) {
			double c = (i-32)/1.8;
			c = Math.round(c);

			System.out.println("| "+i+" | "+c+" |");
		}

	}

}
