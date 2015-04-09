//package com.dam.funciones;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Vector;

public class EJ5_03 {

	/**
	 * Crear una funcioÌ�n que tome como paraÌ�metro un nuÌ�mero y devuelve un array
	 * con el nuÌ�mero de divisores primos que tiene. RecomendacioÌ�n: crear una nueva
	 * funcioÌ�n o utilizar la anterior que indique si un nuÌ�mero es primo o no.
	 *
	 * @param args
	 */


	/**
	 * Devuelve un array con nÃºmeros divisores primos de n
	 * @param n
	 * @return
	 */
	public static Vector divisores (int n) {
		Vector divisores = new Vector();

		for (int i=1;i<=n;i++) {
			boolean primo;

			primo = calcular(i);

			if (n%i == 0 && primo == true) {
				divisores.add(i);
			}
		}

		return divisores;
	}

	/**
	 * Indica si un nÃºmero es primo
	 *
	 * @param n
	 * @return
	 */
	public static boolean calcular(double n) {
		if (n%2 != 0) {
			return true;
		} else {
			return false;
		}
	}

	public static void main(String[] args) {
		Scanner entrada = new Scanner(System.in);
		Vector resultado;
		int valor;

		System.out.print("Dime un número: ");
		valor = entrada.nextInt();

		resultado = divisores(valor);

		System.out.println("Existen "+resultado.size()+" divisores primos de "+valor+" y son: ");

		for (int i=0;i<resultado.size();i++) {
			System.out.println(resultado.get(i));
		}
	}

}
