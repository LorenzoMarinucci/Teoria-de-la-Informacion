package visualizacion;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class Visualizador {
	
	/**
	 * Método que muestra la distribución de probabilidades para una fuente
	 * @param hashMap: distribución de probabilidades de una fuente
	 */
	public static void muestraProbabilidades(HashMap<Character,Float> hashMap) {
		int i = 0,j;
		Object[] simbolos = hashMap.keySet().toArray();
		while (i<simbolos.length) {
			j = 0;
			while (j<6 && i<simbolos.length) {
				String formateado = String.format("%.4f",hashMap.get(simbolos[i]));
				System.out.print(simbolos[i] + ": " + formateado + "   ");
				j++;
				i++;
			}
			System.out.println();
		}
	}
	
	/**
	 * Método que muestra el alfabeto código resultante de una fuente
	 * @param hashMap: alfabeto código para una fuente
	 */
	public static void muestraAlfabeto(HashMap<Character,String> hashMap) {
		int i = 0,j;
		Object[] simbolos = hashMap.keySet().toArray();
		while (i<simbolos.length) {
			j = 0;
			while (j<6 && i<simbolos.length) {
				String formateado = String.format("%-15s",hashMap.get(simbolos[i]));
				System.out.print(simbolos[i] + ": " + formateado);
				j++;
				i++;
			}
			System.out.println();
		}
	}
	
}
