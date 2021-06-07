package prueba_PS_3;

import java.util.Scanner;

public class main {

	public static void main(String[] args) {
		
		//Objeto necesario para la entrada de los datos
		Scanner mScanner = new Scanner(System.in);
		
		//Variables necesarias
		String palabra, errores = "";
		char[] letras;
		byte cantidadMatysculas = 0, cantidadNumeros = 0;
		boolean tieneEspacios = false, tieneCaracteresEspeciales = false, numerosRepetidos = false;
		
		System.out.print("Programa 2.\n\tPalabra a evaluar: ");
		palabra = mScanner.next();
		mScanner.close();
		
		//separar la palabra por letras
		letras = palabra.toCharArray();
		
		//validar si hay 2 mayusculas minimo
		for (int i = 0; i < letras.length; i++) {
			if ( Character.isUpperCase( letras[i] ) ) {
				cantidadMatysculas++;
			}
			
			
			//Detecta si tiene espacios en blanco
			if ( String.valueOf( letras[i] )  == " " )
				tieneEspacios = true;
			
			//Detecta si tiene caracteres especiales
			if ( 	String.valueOf( letras[i] ).equalsIgnoreCase("*") || 
					String.valueOf( letras[i] ).equalsIgnoreCase("_") || 
					String.valueOf( letras[i] ).equalsIgnoreCase("-") || 
					String.valueOf( letras[i] ).equalsIgnoreCase("¡") || 
					String.valueOf( letras[i] ).equalsIgnoreCase("?") ||
					String.valueOf( letras[i] ).equalsIgnoreCase("#") ||
					String.valueOf( letras[i] ).equalsIgnoreCase("$"))
				tieneCaracteresEspeciales = true;
		}
		
		//Recorre la contraseña para verificar si tiene 3 numeros no repetidos.
		for (int i = 0; i < letras.length; i++) {
			
			if ( Character.isDigit( letras[i] ) ) {
				cantidadNumeros++;
				for (int j = 0; j < letras.length; j++) {
					if ( Character.isDigit( letras[j] ) )
						if (letras[i] == letras[j] && i != j)
							numerosRepetidos = true;
				}
			}
			
		}
		
		
		if ( cantidadMatysculas < 2 ) {
			errores = errores + "Error la contraseña debe tener 2 mayusculas.\n";
		}
		if ( cantidadNumeros <= 2) {
			errores = errores + "Error la contraseña debe tener 3 numeros.\n";
		}
		if ( numerosRepetidos ) {
			errores = errores + "Error la contraseña debe tener 3 numeros no repetidos.\n";
		}
		if ( !tieneCaracteresEspeciales ) {
			errores = errores + "Error la contraseña debe tener al menos un carácter especial (* _ - ¿ ¡ ? # $).\n";
		}
		if ( tieneEspacios ) {
			errores = errores + "Error la contraseña no debe tener espacios en blanco.\n";
		}
		if ( letras.length < 8 || letras.length > 15 ) {
			errores = errores + "Error la contraseña debe tener entre 8 y 15 caracteres";
		}
		
		
		if (errores.length() > 1) {
			System.out.print(errores);
		} else {
			System.out.println("Contraseña valida");
		}
		

	}

}
