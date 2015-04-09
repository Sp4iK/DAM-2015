package com.dam.basicos;

import java.util.Scanner;

public class EJ1_04 {

	/**
	 * Construir un programa que dado el peso (en kilogramos) y la altura de una persona (en metros)
	 * calcule y muestre por pantalla su Indice de Masa Corporal (IMS) o iÌ�ndice de Quetelet.
	 * Este iÌ�ndice pretende determinar el intervalo de peso maÌ�s saludable que puede tener una persona.
	 *
	 * @param args
	 */

	public static void main(String[] args) {
		double peso, altura, imc;

		Scanner entrada = new Scanner(System.in);

		// Introducir la altura
		System.out.print("Introduce tu altura en metros: ");
		altura = entrada.nextDouble();

		// Introducir el peso
		System.out.print("Introduce tu peso en kg.: ");
		peso = entrada.nextDouble();

		imc = peso / Math.pow(altura,2);

		System.out.println("Tu índice de masa corporal es: " + imc);
	}

}
