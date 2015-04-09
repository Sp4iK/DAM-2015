package com.dam.arrays;

import java.util.Arrays;
import java.util.Scanner;

public class EJ4_01 {

	/**
	 * Construir un programa que pida al usuario una serie de nœmeros enteros,
	 * los almacene en un array, y obtenga el m‡ximo y el m’nimo de los valores almacenados.
	 * 
	 * @param args
	 */
	
	public static void main(String[] args) {
		Scanner entrada = new Scanner(System.in);
		String nums, numeros[];
		
		System.out.print("Introduce varios números enteros separados por espacios: ");
		nums = entrada.next();
		numeros = nums.split("\\s");
		
		numeros.sort();
	}

}
