package com.dam.condicionales;

import java.util.Scanner;

public class EJ2_01 {

	/**
	 * Definir dos variables num1 y num2 e implementar un programa que asigne un valor a cada una,
	 * y obtenga el mayor de los dos, mostrando el resultado por pantalla.
	 *
	 * @param args
	 */

	public static void main(String[] args) {
		double num1, num2, mayor;

		Scanner entrada = new Scanner(System.in);

		System.out.print("Introduce el primer valor: ");
		num1 = entrada.nextDouble();

		System.out.print("Introduce el segundo valor: ");
		num2 = entrada.nextDouble();

		if (num1 > num2) {
			System.out.println("El mayor valor es: " + num1);
		} else {
			System.out.println("El mayor valor es: " + num2);
		}
	}

}
