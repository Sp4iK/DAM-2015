package com.dam.bucles;

import java.util.Scanner;

public class EJ3_04 {

	/**
	 * Definir un array bidimensional para representar una agenda semanal,
	 * donde se contemplen los 7 diÌ�as de la semana y las 24 horas de cada diÌ�a.
	 * Utilizar bucles for anidados para inicializar la agenda a: â€œNo tengo planes.â€�.
	 * AnÌƒadir â€œplanesâ€� a la agenda y mostrar el resultado por pantalla.
	 *
	 * @param args
	 */

	public static void main(String[] args) {
		String[][] agenda = new String[7][24];
		Scanner entrada = new Scanner(System.in);
		char opcion;
		int dia, hora;
		String plan = null;

		for (int d=0;d<7;d++) {
			for (int h=0;h<24;h++) {
				agenda[d][h] = "No tengo planes.";
			}
		}

		System.out.print("Deseas consultar (c) o editar (e) la agenda? ");
		opcion = (entrada.next()).charAt(0);

		System.out.print("Selecciona un día (l,m,x,j,v): ");
		dia = (int)(entrada.next()).charAt(0);

		System.out.print("Selecciona una hora: ");
		hora = entrada.nextInt();

		if (opcion == 'c') {
			System.out.print("El plan para el día "+dia+", a las "+hora+" es: "+agenda[dia][hora]);
		} else if (opcion == 'e') {
			System.out.print("Dime un nuevo plan: ");
			plan = entrada.next();
			agenda[dia][hora] = plan;
		} else {
			System.out.println("No has introducido una opción válida.");
		}

	}

}
