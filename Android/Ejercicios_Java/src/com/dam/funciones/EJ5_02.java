package com.dam.funciones;

import java.util.Scanner;

public class EJ5_02 {

	/**
	 * Crear una funcioÌ�n que tome como paraÌ�metro un nuÌ�mero e indique si es
	 * primo o no.
	 *
	 * @param args
	 */
	public static boolean calcular(double x) {
		if (x%2 != 0) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Indica si un nÃºmero es primo
	 *
	 * @param n
	 * @return
	 */

	public static void main(String[] args) {
		Scanner entrada = new Scanner(System.in);
		boolean resultado;
		double valor;

		System.out.print("Dime un número: ");
		valor = entrada.nextDouble();

		resultado = calcular(valor);

		System.out.println("El número "+valor+" es primo? "+resultado);
	}

}
