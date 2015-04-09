package com.dam.funciones;

import java.util.Scanner;

public class EJ5_04 {

	/**
	 * Crear una funcioÌ�n que convierte de grados Fahrenheit a Celsius.
	 * @param args
	 */


	/**
	 * Convierte de Celsius a Fahrenheit
	 *
	 * @param celsius
	 * @return Fahrenheit
	 */
	public static double convierteF (double celsius) {
		double fahrenheit;

		fahrenheit = (celsius*1.8)+32;

		return fahrenheit;
	}


	/**
	 * Convierte de Fahrenheit a Celsius
	 *
	 * @param fahrenheit
	 * @return Celsius
	 */
	public static double convierteC (double fahrenheit) {
		double celsius;

		celsius = (fahrenheit-32)/1.8;

		return celsius;
	}

	public static void main(String[] args) {
		Scanner entrada = new Scanner(System.in);
		double temp, tempC, tempF;
		char tipo;

		System.out.print("Dame un valor de temperatura: ");
		temp = entrada.nextDouble();

		System.out.print("Es Celsius (c) o Fahrenheit (f)? ");
		tipo  = (entrada.next()).charAt(0);

		if (tipo == 'c') {
			tempF = convierteF(temp);
			System.out.print(temp+"º Celsius equivalen a "+tempF+"º Fahrenheit.");
		} else if (tipo == 'f') {
			tempC = convierteC(temp);
			System.out.print(temp+"º Fahrenheit equivalen a "+tempC+"º Celsius.");
		}
	}

}
