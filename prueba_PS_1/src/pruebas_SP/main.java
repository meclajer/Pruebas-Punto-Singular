/*
 * Autor: Miguel Eduardo Castillo Landeros
 * Fecha: 6/6/21
 * Descripcion: Prueba de Punto Singular punto 1
 */

package pruebas_SP;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class main {

	public static void main(String[] args) {
		
		//Objeto necesario para la entrada de los datos
		Scanner mScanner = new Scanner(System.in);
		
		//Variables
		String palabra;
		char[] letras;
		ArrayList<Character> evaluadas = new ArrayList<Character>();
		byte contador = 0;
		
		
		//Entrada de la palabra a evaluar
		System.out.print("Programa 1.\n\tPalabra a evaluar: ");
		palabra = mScanner.next();
		mScanner.close();
		
		
		//Presenta la palabra sin modificaciones
		System.out.print("\n\n\tResultado:\n"+palabra+" -> [");
		
		
		//Recorrer la palabra para comparar la cantidad de letras, si ya fue contada no se vuelve a contar
		letras = palabra.toCharArray();
		for (int i = 0; i < letras.length; i++) {//recorre la palabra
			if ( !evaluadas.contains( letras[i] ) ) {				
				for (int j = 0; j < letras.length; j++) {
					if (letras[i] == letras[j]) {
						contador++;
					}
				}
				System.out.print(letras[i]+" -> "+contador+"  ");
				contador = 0;
				evaluadas.add(letras[i]);
			}
		}
		
		
		//Ordenar alfabeticamente
		Arrays.sort(letras);
		System.out.print("] [");
		System.out.print(letras);
		System.out.print("]");
	
	}

}
