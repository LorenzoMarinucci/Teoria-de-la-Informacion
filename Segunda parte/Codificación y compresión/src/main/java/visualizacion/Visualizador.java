package visualizacion;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class Visualizador {
	
	public static void muestraAlfabeto(HashMap<Character,String> alfabeto) {
		int i = 0,j;
		Object[] simbolos = alfabeto.keySet().toArray();
		while (i<simbolos.length) {
			j = 0;
			while (j<6 && i<simbolos.length) {
				System.out.print(simbolos[i] + ": " + alfabeto.get(simbolos[i]) + "   ");
				j++;
				i++;
			}
		}
	}
}
