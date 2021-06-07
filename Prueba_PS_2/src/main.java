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
		
		//Objeto necesario para la entrada de los datos
		Scanner mScanner = new Scanner(System.in);
		
		//Variables necesarias
		String palabra, palabraSinExepciones[], palabraDividida;
		char[] letras;
		boolean[] vocal;
		byte cantidadConsonantes = 0, contador = 0, apuntador = 0, ubicacionVocales[], cantidadExepciones = 0;
		
		System.out.print("Programa 2.\n\tPalabra a evaluar: ");
		palabra = mScanner.next();
		mScanner.close();
		
		//separar la palabra por letras
		letras = palabra.toCharArray();
		
		//identificar cuales son vocales
		vocal = new boolean[letras.length];
		for (int i = 0; i < letras.length; i++) {
			if ( 	String.valueOf( letras[i] ).equalsIgnoreCase("a") || String.valueOf( letras[i] ).equalsIgnoreCase("á") || 
					String.valueOf( letras[i] ).equalsIgnoreCase("e") || String.valueOf( letras[i] ).equalsIgnoreCase("é") || 
					String.valueOf( letras[i] ).equalsIgnoreCase("i") || String.valueOf( letras[i] ).equalsIgnoreCase("í") || 
					String.valueOf( letras[i] ).equalsIgnoreCase("o") || String.valueOf( letras[i] ).equalsIgnoreCase("ó") || 
					String.valueOf( letras[i] ).equalsIgnoreCase("u") || String.valueOf( letras[i] ).equalsIgnoreCase("ú") ) {
				vocal[i] = true;
				if ( cantidadConsonantes < contador ) {
					cantidadConsonantes = contador;
				}
				contador = 0;
				apuntador++;
			} else {
				vocal[i] = false;
				contador++;
			}
		}
		
		//recorrer la palabra para guardar la ubicacion de las vocales
		ubicacionVocales = new byte[apuntador];
		apuntador = 0;
		for (byte i = 0; i < vocal.length; i++) {
			if ( vocal[i] ) {
				ubicacionVocales[apuntador] = i;
				apuntador++;
			}
		}
		
		apuntador = 0;
		
		
		//ver la cantidad de consonantes entre vocal y vocal, para identificar a que regla pertenecen
		switch (cantidadConsonantes) {
			case 1: {//regla 1 (Una sola consonante entre dos vocales se agrupa con la vocal que sigue)
				
				//cortar por secciones hasta cada vocal
				palabraDividida = palabra.substring(0, ubicacionVocales[apuntador])+"-";
				apuntador++;
				for (int i = ubicacionVocales[0]; i < ubicacionVocales.length && apuntador <= letras.length ; i = i) {
					//Cargar resultado a variable de resultado String
					palabraDividida = palabraDividida +"-"+ palabraDividida.substring(i, ubicacionVocales[apuntador+1]);
					apuntador++;
					i = ubicacionVocales[apuntador];
				}
				
				//agrego la letra faltante de haber
				if ( ubicacionVocales[apuntador] < letras.length ) {
					for (int i = ubicacionVocales[apuntador]; i <= letras.length; i++) {
						palabraDividida = palabraDividida + letras[i];
					}
				}
	
				
				break;
			}
			case 2: {//regla 2 (Dos consonantes entre dos vocales se agrupan una para cada vocal.)
		  		
				//buscar las exepciones de grupos consonanticos: pr, pl, br, bl, fr, fl, tr, tl, dr, dl, cr, cl, gr, ch, ll o rr
				Pattern pat = Pattern.compile("[pP][rR]|[pP][lL]|[bB][rR]|[bB][lL]|[fF][rR]|[fF][lL]|[tT][rR]|[tT][lL]|[dD][rR]|[dD][lL]|[cC][rR]|[cC][lL]|[gG][rR]|[cC][hH]|[lL][lL]|[rR][rR]");
				Matcher mat = pat.matcher(palabra);
				
				//De contar con exepciones reestructurar uniendo las consonantes de la exepcion (bucle)
				ArrayList<String> letrasReestructuradas = new ArrayList<String>();
				if (mat.find()) {
					for (int i = 0; i < letras.length-1; i++) {
						if ((String.valueOf(letras[i])+String.valueOf(letras[i+1])).equalsIgnoreCase("pr") ||
							(String.valueOf(letras[i])+String.valueOf(letras[i+1])).equalsIgnoreCase("pl") ||
							(String.valueOf(letras[i])+String.valueOf(letras[i+1])).equalsIgnoreCase("br") ||
							(String.valueOf(letras[i])+String.valueOf(letras[i+1])).equalsIgnoreCase("bl") ||
							(String.valueOf(letras[i])+String.valueOf(letras[i+1])).equalsIgnoreCase("fr") ||
							(String.valueOf(letras[i])+String.valueOf(letras[i+1])).equalsIgnoreCase("fl") ||
							(String.valueOf(letras[i])+String.valueOf(letras[i+1])).equalsIgnoreCase("tr") ||
							(String.valueOf(letras[i])+String.valueOf(letras[i+1])).equalsIgnoreCase("tl") ||
							(String.valueOf(letras[i])+String.valueOf(letras[i+1])).equalsIgnoreCase("dr") ||
							(String.valueOf(letras[i])+String.valueOf(letras[i+1])).equalsIgnoreCase("dl") ||
							(String.valueOf(letras[i])+String.valueOf(letras[i+1])).equalsIgnoreCase("cr") ||
							(String.valueOf(letras[i])+String.valueOf(letras[i+1])).equalsIgnoreCase("cl") ||
							(String.valueOf(letras[i])+String.valueOf(letras[i+1])).equalsIgnoreCase("gr") ||
							(String.valueOf(letras[i])+String.valueOf(letras[i+1])).equalsIgnoreCase("ch") ||
							(String.valueOf(letras[i])+String.valueOf(letras[i+1])).equalsIgnoreCase("ll") ||
							(String.valueOf(letras[i])+String.valueOf(letras[i+1])).equalsIgnoreCase("rr") ) {
							letrasReestructuradas.add( String.valueOf(letras[i]) + String.valueOf(letras[i+1]) );
							i += 1;
						} else {
							letrasReestructuradas.add( String.valueOf(letras[i]) );
						}
					}
					
					
					apuntador = 0;
					
					
					//reidentificar las vocales
					palabraSinExepciones = new String[letrasReestructuradas.size()];
					vocal = new boolean[letrasReestructuradas.size()];
					for (int i = 0; i < palabraSinExepciones.length; i++) {
						palabraSinExepciones[i] = letrasReestructuradas.get(i);
						if ( 	String.valueOf( palabraSinExepciones[i] ).equalsIgnoreCase("a") || String.valueOf( palabraSinExepciones[i] ).equalsIgnoreCase("á") || 
								String.valueOf( palabraSinExepciones[i] ).equalsIgnoreCase("e") || String.valueOf( palabraSinExepciones[i] ).equalsIgnoreCase("é") || 
								String.valueOf( palabraSinExepciones[i] ).equalsIgnoreCase("i") || String.valueOf( palabraSinExepciones[i] ).equalsIgnoreCase("í") || 
								String.valueOf( palabraSinExepciones[i] ).equalsIgnoreCase("o") || String.valueOf( palabraSinExepciones[i] ).equalsIgnoreCase("ó") || 
								String.valueOf( palabraSinExepciones[i] ).equalsIgnoreCase("u") || String.valueOf( palabraSinExepciones[i] ).equalsIgnoreCase("ú") ) {
							vocal[i] = true;
							apuntador++;
						} else {
							vocal[i] = false;
						}
					}
					
					
					//recorrer la palabra para guardar la ubicacion de las vocales
					ubicacionVocales = new byte[apuntador];
					apuntador = 0;
					for (byte i = 0; i < vocal.length; i++) {
						if ( vocal[i] ) {
							ubicacionVocales[apuntador] = i;
							apuntador++;
						}
					}
					
					
					
				} else {
					//Si no tiene ecepciones acomodo las letras en palabra sin exepciones
					palabraSinExepciones = new String[letras.length];
					for (int i = 0; i <= palabraSinExepciones.length; i++) {
						palabraSinExepciones[i] = String.valueOf( letras[i] );
					}
				}
				
		
				apuntador = 0;
				palabraDividida = palabra.substring(0, ubicacionVocales[apuntador+1])+"-";
				apuntador++;
				
				//Bucle para cortar la palabra conforme a la regla
				for (int i = ubicacionVocales[apuntador]; i < palabraSinExepciones.length; i = i) {
					
					//si hay dos consonantes una consonante para cada vocal
					if ( (i - ubicacionVocales[apuntador+1] ) > 1 ) {
						palabraDividida = palabraDividida + palabraSinExepciones[i+1] + "-" + palabraSinExepciones[i+2] + palabraSinExepciones[i+3];
						i = i+3;
					} else { //si entre vocal y vocal solo hay una consonante se agrupara a la siguiente vocal
						palabraDividida = palabraDividida + "-" + palabraSinExepciones[i+1] + palabraSinExepciones[i+2];
						i += 2;
					}
						
				}
					
					
				//agrego la letra faltante de haber
				if ( ubicacionVocales[apuntador] < letras.length ) {
					for (int i = ubicacionVocales[apuntador]; i <= letras.length; i++) {
						palabraDividida = palabraDividida + letras[i];
					}
				}
				
				break;
					}
			case 3: {//regla 3 (Tres consonantes en medio de dos vocales se agrupan dos con la primera vocal y la tercera con la vocal que sigue.)
				//buscar las exepciones de grupos consonanticos: pr, pl, br, bl, fr, fl, tr, tl, dr, dl, cr, cl, gr, ch, ll o rr
				
				
				//Reestructurar uniendo las consonantes de la exepcion (bucle)
	
				//reidentificar las vocales y su ubicacion
		
				//Bucle para cortar la palabra conforme a la regla
					//si hay 3 consonantes dos para la vocal izquierda y una para la derecha
						//Cargar resultado a variable de resultado String
		
					//si entre vocal y vocal hay 2 consonantes, una consonante para cada vocal
						//Cargar resultado a variable de resultado String
	
					//si entre vocal y vocal hay 1 consonantes dejar para la vocal derecha
						//Cargar resultado a variable de resultado String
				
				
				break;
			}
			case 4: {//regla 4 (Cuatro consonantes en medio de dos vocales se agrupan dos con la primera vocal y las otras dos con la vocal que sigue.)
				//Bucle para cortar la palabra conforme a la regla
				//dividir las 4 consonantes 2 para cada vocal (izq y derecha)
					//Cargar resultado a variable de resultado String
	
				
				break;
			}
			default:
				throw new IllegalArgumentException("Unexpected value: " + cantidadConsonantes);
		}//Fin de switch de reglas
		
		
		//Mostrar en pantalla el resultado
		
	}

}

class Exepciones{
	
	public boolean tieneExepciones() {
		
		
		
		return false;
	}
	
}



