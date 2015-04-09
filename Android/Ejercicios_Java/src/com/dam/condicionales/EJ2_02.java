package com.dam.condicionales;

import java.util.Scanner;

public class EJ2_02 {

	/**
	 * Construir un programa que calcule el iÌ�ndice de masa corporal de una persona (IMC = peso [kg] / altura2[m])
	 * e indique el estado en el que se encuentra esa persona en funcioÌ�n del valor de IMC.
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

		if (imc < 16) {
			System.out.println("Padeces delgadez extrema");
		} else if (imc > 16 && imc <= 16.99) {
			System.out.println("Padeces delgadez moderada");
		} else if (imc > 17 && imc <= 18.49) {
			System.out.println("Padeces delgadez aceptable");
		} else if (imc > 18.50 && imc <= 24.99) {
			System.out.println("Tu peso es normal");
		} else if (imc > 25 && imc <= 29.99) {
			System.out.println("Padeces sobrepeso");
		} else if (imc > 30 && imc <= 34.99) {
			System.out.println("Padeces obesidad de tipo 1");
		} else if (imc > 35 && imc <= 40) {
			System.out.println("Padeces obesidad de tipo 2");
		} else if (imc > 40) {
			System.out.println("Padeces obesidad de tipo 3");
		} else {
			System.out.println("Tu valor IMC es desorbitado, haztelo mirar");
		}
	}

}
