/*
 * Autor: Miguel Eduardo Castillo Landeros
 * Fecha: 6/6/21
 * Descripcion: Prueba de Punto Singular punto 2
 */

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class main {

	public static void main(String[] args) {
		
		//Objeto necesarios
		Scanner mScanner = new Scanner(System.in);
		Pattern pat = Pattern.compile("[pP][rR]|[pP][lL]|[bB][rR]|[bB][lL]|[fF][rR]|[fF][lL]|[tT][rR]|[tT][lL]|[dD][rR]|[dD][lL]|[cC][rR]|[cC][lL]|[gG][rR]|[cC][hH]|[lL][lL]|[rR][rR]");
		
		//Variables
		String palabra, resultado = "";
		char[] palabraPorLetras;
		boolean esVocal[];
		byte cantidadConsonantes = 0, contador = 0, cantidadVocales = 0, vocalesCruzadas = 0, consonantesFaltantes = 0, diferencia = 0;
		

		// Solicitar la palabra que se dividira
		System.out.print("Programa 2.\n\tPalabra a evaluar: ");
		palabra = mScanner.next();
		mScanner.close();
		
		Matcher mat = pat.matcher(palabra);


		// dividir la palabra por letras, en un arreglo
		palabraPorLetras = palabra.toCharArray();
		

		// detectar las vocales y su ubicacion asi como la cantidad de consonantes entre vocales
		esVocal = new boolean[palabraPorLetras.length];
		for (int i = 0; i < esVocal.length; i++) {
			
			//Es vocal
			if (String.valueOf( palabraPorLetras[i] ).equalsIgnoreCase("a") || String.valueOf( palabraPorLetras[i] ).equalsIgnoreCase("á") || 
				String.valueOf( palabraPorLetras[i] ).equalsIgnoreCase("e") || String.valueOf( palabraPorLetras[i] ).equalsIgnoreCase("é") || 
				String.valueOf( palabraPorLetras[i] ).equalsIgnoreCase("i") || String.valueOf( palabraPorLetras[i] ).equalsIgnoreCase("í") || 
				String.valueOf( palabraPorLetras[i] ).equalsIgnoreCase("o") || String.valueOf( palabraPorLetras[i] ).equalsIgnoreCase("ó") || 
				String.valueOf( palabraPorLetras[i] ).equalsIgnoreCase("u") || String.valueOf( palabraPorLetras[i] ).equalsIgnoreCase("ú") ) {
				esVocal[i] = true;
				
				//si la cantidad de consonantes es mayor a la registrada se remplaza el valor
				if ( cantidadConsonantes < contador ) {
					cantidadConsonantes = contador;
				}
				contador = 0;
				cantidadVocales++;
			} else { //Es consonante
				esVocal[i] = false;
				contador++;
			}
		}
		

		// tomando el dato de cantidad de consonantes entre vocales dirigir a la regla que le pertenece (1 a 4)
		switch (cantidadConsonantes) {
			case 1: // REGLA 1 (una consonante entre vocal)
		
				for (int i = 0; i < esVocal.length; i++) {
				
					if (esVocal[i]) {
						vocalesCruzadas++;
						if (vocalesCruzadas == cantidadVocales) {//es la ultima vocal

							resultado += palabraPorLetras[i];

							// cargar lo que resta de la palabra (si no es la ultima letra**)
							if ( i < esVocal.length ) {
								resultado += palabra.substring(i+1);
							}

							// romper el bucle
							break;

						} else {//No es la ultima vocal
							
							// Cargar vocal a resultado
							resultado += palabraPorLetras[i];
							
							// Cargar divicion a resultado (-)
							resultado += "-";
						}

					} else { //Es consonante
						// Cargar consonante a resultado
						resultado += palabraPorLetras[i];
					}	
				}
				break;
				
				
			case 2: // REGLA 2 (dos consonantes entre vocal)

				// verificar si tiene exepciones (pr, pl, br, bl, etc.,)// Si tiene exepciones
				if (mat.find()) {

					for (int i = 0; i < esVocal.length; i++) {
						if (esVocal[i]) {
							vocalesCruzadas++;
							if (vocalesCruzadas == cantidadVocales) {//es la ultima vocal

								// Cargar vocal a resultado
								resultado += palabraPorLetras[i];

								// cargar lo que resta de la palabra (si no es la ultima letra**)
								if ( i < esVocal.length ) {
									resultado += palabra.substring(i+1);
								}
								
								break;

							} else {//No es la ultima vocal

								// Cargar vocal a resultado
								resultado += palabraPorLetras[i];

								// variable consonantes faltantes = 2
								consonantesFaltantes = 2;

							}
						} else {// no es vocal
							if ( consonantesFaltantes == 2 ) {

								// juntar consonante actual y la sigiente, para verificar si  es una de las exepciones
								String cadString = String.valueOf(palabraPorLetras[i]) + String.valueOf(palabraPorLetras[i+1]);
								Matcher matCadena2 = pat.matcher(cadString);
								
								if ( matCadena2.find() ) {// es una exepcion
									resultado += "-";
									resultado += palabraPorLetras[i];
									i++;
									resultado += palabraPorLetras[i];
								} else {// no es una exepcion
									resultado += palabraPorLetras[i];
									consonantesFaltantes = 1;
								}

							} if (consonantesFaltantes < 2) {// consonantes faltantes < 2
								
								// resultado,lengh >= 1
								if ( resultado.length() >= 1 ) {
									resultado += "-";
									resultado += palabraPorLetras[i];
									consonantesFaltantes = 0;
								} else {// resultado,lengh < 1
									// Cargar consonante a resultad
									
								}
							}
						}
					}//final bule
				} else {// Si NO tiene exepciones

					//bucle para recorrer la palabra
					for (int i = 0; i < esVocal.length; i++) {

						if ( esVocal[i] ) {
							vocalesCruzadas++;
							if (vocalesCruzadas == cantidadVocales) {//es la ultima vocal
								resultado += palabraPorLetras[i];
								// cargar lo que resta de la palabra (si no es la ultima letra**)
								if ( i < esVocal.length ) {
									resultado += palabra.substring(i+1);
								}
								break;

							} else {//No es la ultima vocal
								resultado += palabraPorLetras[i];
								consonantesFaltantes = 2;
							}

						} else {// no es vocal

							// consonantes faltantes == 2
							if ( consonantesFaltantes == 2 ) {
								resultado += palabraPorLetras[i];
								consonantesFaltantes = 1;
							
							} else {// consonantes faltantes < 2

								// resultado,lengh >= 1
								if ( resultado.length() >= 1 ) {
									// dividir palabra (-)
									resultado += "-";
									resultado += palabraPorLetras[i];
									consonantesFaltantes = 0;
									
								} else {// resultado,lengh < 1
									// Cargar consonante a resultado 
									resultado += palabraPorLetras[i];
								}								
						
							}
						}
					}//final bucle

				}
				break;
				
				
			case 3: // REGLA 3 (tres consonantes entre vocal) 

				// verificar si tiene exepciones (pr, pl, br,bl... etc.,)
				// Si tiene exepciones
				if (mat.find()) {

					// bucle para recorrer la palabra
					for (int i = 0; i < esVocal.length; i++) {
						
						if ( esVocal[i] ) {// es vocal
							vocalesCruzadas++;
							if (vocalesCruzadas == cantidadVocales) {//es la ultima vocal
								resultado += palabraPorLetras[i];
								// cargar lo que resta de la palabra (si no es la ultima letra**)
								if ( i < esVocal.length ) {
									resultado += palabra.substring(i+1);
								}
								break;

							} else {//No es la ultima vocal
								resultado += palabraPorLetras[i];

								// variable consonantes faltantes = (cantidad de consonantes hasta la siguiente vocal)
								for (int j = i; j < esVocal.length; j++) {
									if ( !esVocal[j] )
										diferencia++;
									else
										break;
								}
								consonantesFaltantes = diferencia;

							}
						} else {// no es vocal

							// consonantes faltantes == 3
							if ( consonantesFaltantes == 3 ) {
								
								//  carga a resultado la consonante actual
								resultado += palabraPorLetras[i];

								//  actualizar apuntador bucle
								i++;
								
								//  carga a resultado la consonante actual (+1)
								resultado += palabraPorLetras[i];

								//  cargar a resultado divicion (-)
								resultado += ("-");

								//  actualizar apuntador bucle
								i++;

								//  carga a resultado la consonante actual (+2)
								resultado += palabraPorLetras[i];
										
							}
							// consonantes faltantes == 2
							if ( consonantesFaltantes == 2 ) {

								// juntar consonante actual y la sigiente, para verificar si  es una de las exepciones
								String cadString = String.valueOf(palabraPorLetras[i]) + String.valueOf(palabraPorLetras[i+1]);
								Matcher matCadena2 = pat.matcher(cadString);
								
								// es una exepcion
								if ( matCadena2.find() ) {								
									// cargar division (-)
									resultado += ("-");

									// cargar 1ra consonante
									resultado += palabraPorLetras[i];

									// actualizar apuntador del bucle
									i++;

									// cargar 2da consonante
									resultado += palabraPorLetras[i];

								} else {// no es una exepcion
									resultado += palabraPorLetras[i];
									consonantesFaltantes = 1;
								}
							}
							if ( consonantesFaltantes < 2 ) {
								if ( resultado.length() >= 1 ) {
									resultado += "-";
									resultado += palabraPorLetras[i];
									consonantesFaltantes = 0;									
								} else {
									// Cargar consonante a resultado
									resultado += palabraPorLetras[i];
								}
							}
						}
					}// final bucle
				} else {// Si NO tiene exepciones

					for (int i = 0; i < esVocal.length; i++) {

						if (esVocal[i]) {
							vocalesCruzadas++;
							if (vocalesCruzadas == cantidadVocales) {//es la ultima vocal
								resultado += palabraPorLetras[i]; 

								// cargar lo que resta de la palabra (si no es la ultima letra**)
								if ( i < esVocal.length ) {
									resultado += palabra.substring(i+1);
								}

								break;

							} else {//No es la ultima vocal

								resultado += palabraPorLetras[i];

								// variable consonantes faltantes = (cantidad de consonantes hasta la siguiente vocal)
								for (int j = i; j < esVocal.length; j++) {
									if ( !esVocal[j] )
										diferencia++;
									else
										break;
								}
								consonantesFaltantes = diferencia;
							}

						} else {// no es vocal

							if ( consonantesFaltantes == 3 ) {
								resultado += palabraPorLetras[i];
								consonantesFaltantes = 2;
							}

							if ( consonantesFaltantes == 2 ) {
								resultado += palabraPorLetras[i];
								consonantesFaltantes = 1;
								resultado += "-";
							}

							if (consonantesFaltantes < 2) {
								if ( resultado.length() >= 1 ) {
									resultado += palabraPorLetras[i];
									consonantesFaltantes = 0;
								} else {// resultado,lengh < 1
									resultado += palabraPorLetras[i];
								}									
							}
						
						}
					}//final bucle
				}
				break;
				
			case 4: // REGLA 4 (cuatro consonantes entre vocal) 

				byte cantConsonantes = 0;
				
				for (int i = 0; i < esVocal.length; i++) {

					if ( esVocal[i] ) {

						cantConsonantes = 0;
						// Es la ultima vocal
						vocalesCruzadas++;
						if (vocalesCruzadas == cantidadVocales) {
							resultado += palabra.substring(i+1);
							break;
						} else {// No es la ultima vocal
							resultado += palabraPorLetras[i];
						}

					} else {// No es vocal

						cantConsonantes++;				

						// si cantidad consonantes == 3
						if (cantConsonantes == 3) {
							resultado += ("-");
						}
						resultado += palabraPorLetras[i];
				
					}
				}
				break;
		}// fin swich
		
		System.out.println("\n\nResultado:\n\t"+resultado);
		
	}

}



