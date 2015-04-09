package com.dam.funciones;

import java.util.Scanner;

public class EJ5_01 {

	/**
	 * Crear una funcioÌ�n que tome dos paraÌ�metros numeÌ�ricos y calcule el maÌ�ximo de los dos nuÌ�meros.
	 * Crear una nueva funcioÌ�n con el mismo nombre, que tome tres paraÌ�metros,
	 * y calcule el maÌ�ximo de los tres nuÌ�meros. Esta segunda funcioÌ�n debe hacer uso de la primera.
	 *
	 * @param args
	 */


	/**
	 * Calcula el mÃ¡ximo de dos nÃºmeros
	 * @param x
	 * @param y
	 * @return
	 */

	public static double calcular (double x, double y) {
		return Math.max(x, y);
	}


	/**
	 * Calcula el mÃ¡ximo de tres nÃºmeros
	 * @param x
	 * @param y
	 * @param z
	 * @return
	 */

	public static double calcular (double x, double y, double z) {
		return Math.max(Math.max(x, y),z);
	}

	public static void main(String[] args) {
		//Scanner entrada = new Scanner(System.in);
		double res1, res2;

		res1 = calcular(1,2);
		res2 = calcular(3,2,1);

		System.out.println("El valor máximo de la primera función es: " + res1);
		System.out.println("El valor máximo de la segunda función es: " + res2);
	}

}
