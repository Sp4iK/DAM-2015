package com.dam.condicionales;

import java.util.Scanner;

public class EJ2_03 {

	/**
	 * Construir un programa que simule el funcionamiento de una calculadora que puede realizar
	 * las cuatro operaciones aritmeÌ�ticas baÌ�sicas (suma, resta, producto y divisioÌ�n)
	 * con valores numeÌ�ricos enteros. El usuario debe especificar la operacioÌ�n con el primer caraÌ�cter
	 * del primer paraÌ�metro de la liÌ�nea de comandos: S o s para la suma, R o r para la resta, P, p, M o m para el producto y D o d para la divisioÌ�n.
	 * Los valores de los operandos se deben indicar en el segundo y tercer paraÌ�metros.
	 *
	 * @param args
	 */


	public static void main(String[] args) {
		char op;
		char o;
		int val1, val2, res = 0;

		Scanner entrada = new Scanner(System.in);

		System.out.print("¿Qué operación vas a realizar? (Suma: S o s | Resta: R o r | Producto: P, p, M o m | División: D o d): ");
		op = (entrada.next()).charAt(0);

		System.out.print("Introduce el primer valor: ");
		val1 = entrada.nextInt();

		System.out.print("Introduce el segundo valor: ");
		val2 = entrada.nextInt();

		switch (op) {
		case 'S':
		case 's':
			res = val1 + val2;
			break;
		case 'R':
		case 'r':
			res = val1 - val2;
			break;
		case 'P':
		case 'p':
		case 'M':
		case 'm':
			res = val1 * val2;
			break;
		case 'D':
		case 'd':
			res = val1 / val2;
			break;
		default:
			break;
		}

		System.out.println("El resultado es " + res);
	}

}
