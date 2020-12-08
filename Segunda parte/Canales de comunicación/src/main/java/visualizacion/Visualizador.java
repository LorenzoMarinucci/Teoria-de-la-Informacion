package visualizacion;

import com.sun.xml.internal.ws.util.StringUtils;

public class Visualizador {

    public static void mostrarMatriz(double[][] matriz) {
        System.out.print("   ");
        for (int j = 0; j < matriz[0].length; j++) {
            System.out.print("  B" + (j + 1) + "     ");
        }
        System.out.println("");
        for (int i = 0; i < matriz.length; i++) {
            System.out.print("A" + (i + 1) + " ");
            for (int j = 0; j < matriz[i].length; j++) {
                String formateado = String.format("%.4f", matriz[i][j]);
                System.out.print(formateado + "   ");
            }
            System.out.println("");
        }
    }

    public static void mostrarPSalida(double[] probabilidades) {
        for (int i = 0; i < probabilidades.length; i++) {
            System.out.print("  B" + (i + 1) + "     ");
        }
        System.out.println("");
        for (int i = 0; i < probabilidades.length; i++) {
            String formateado = String.format("%.4f", probabilidades[i]);
            System.out.print(formateado + "   ");
        }
    }
    
    public static void mostrarEntropiasAPosteriori(double[] entropias) {
    	for (int i = 0; i < entropias.length; i++) {
            System.out.print("A/b=B" + (i + 1) + "   ");
        }
    	System.out.println("");
    	for (int i = 0; i < entropias.length; i++) {
            String formateado = String.format("%.4f", entropias[i]);
            System.out.print(formateado + "   ");
        }
    }

}
