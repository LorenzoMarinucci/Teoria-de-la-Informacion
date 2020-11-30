package main;

import calculos.Canal;
import lectura.Lectura;
import visualizacion.Visualizador;

import javax.swing.text.StyledDocument;

public class Main {

    public static void main(String[] args) {

        Canal c1 = Lectura.leerDatos("canal1.txt");
        Canal c2 = Lectura.leerDatos("canal2.txt");
        Canal c3 = Lectura.leerDatos("canal3.txt");
        Double equivocacion, entropiaBA, entropiaA, entropiaB, informacionMutuaAB;

        System.out.println("-----------CANAL 1-----------\n");

        System.out.println("Probabilidades de las salidas:\n");
        Visualizador.mostrarPSalida(c1.getProbSalida());
        System.out.println("\n\nProbabilidades a posteriori:\n");
        Visualizador.mostrarMatriz(c1.getMatPPost());
        equivocacion = c1.calculoEquivocacion(c1.calculoEntropiaAPosteriori());
        System.out.println("\nEquivocacion = " + equivocacion);
        entropiaA = c1.calculoEntropiaAPriori();
        entropiaB = c1.calculoEntropiaSalida();
        System.out.println("H(A) = " + entropiaA);
        System.out.println("H(B) = " + entropiaB);
        entropiaBA = c1.entropiaBA();
        System.out.println("H(B/A) = " + entropiaBA);
        System.out.println("\nInformacion mutua a partir de sucesos simultaneos: " + c1.calculoInformacionMutuaConSucesosSimultaneos());
        informacionMutuaAB = c1.calculoInformacionMutuaAB(equivocacion);
        System.out.println("I(A, B) = H(A) - H(A/B) = " + informacionMutuaAB);
        System.out.println("I(B, A) = H(B) - H(B/A) = " + c1.calculoInformacionMutuaBA(entropiaBA));
        System.out.println("\nEntropía afín (sucesos simultáneos) = " + c1.entropiaAfin());
        System.out.println("H(A, B) = H(A) + H(B) - I(A, B) = " + (entropiaA + entropiaB - informacionMutuaAB));

        System.out.println("\n-----------CANAL 2-----------\n");

        System.out.println("Probabilidades de las salidas:\n");
        Visualizador.mostrarPSalida(c2.getProbSalida());
        System.out.println("\n\nProbabilidades a posteriori:\n");
        Visualizador.mostrarMatriz(c2.getMatPPost());
        equivocacion = c2.calculoEquivocacion(c2.calculoEntropiaAPosteriori());
        System.out.println("\nEquivocacion = " + equivocacion);
        entropiaA = c2.calculoEntropiaAPriori();
        entropiaB = c2.calculoEntropiaSalida();
        System.out.println("H(A) = " + entropiaA);
        System.out.println("H(B) = " + entropiaB);
        entropiaBA = c2.entropiaBA();
        System.out.println("H(B/A) = " + entropiaBA);
        System.out.println("\nInformacion mutua a partir de sucesos simultaneos: " + c2.calculoInformacionMutuaConSucesosSimultaneos());
        informacionMutuaAB = c2.calculoInformacionMutuaAB(equivocacion);
        System.out.println("I(A, B) = H(A) - H(A/B) = " + informacionMutuaAB);
        System.out.println("I(B, A) = H(B) - H(B/A) = " + c2.calculoInformacionMutuaBA(entropiaBA));
        System.out.println("\nEntropía afín (sucesos simultáneos) = " + c2.entropiaAfin());
        System.out.println("H(A, B) = H(A) + H(B) - I(A, B) = " + (entropiaA + entropiaB - informacionMutuaAB));

        System.out.println("\n-----------CANAL 3-----------\n");

        System.out.println("Probabilidades de las salidas:\n");
        Visualizador.mostrarPSalida(c3.getProbSalida());
        System.out.println("\n\nProbabilidades a posteriori:\n");
        Visualizador.mostrarMatriz(c3.getMatPPost());
        equivocacion = c3.calculoEquivocacion(c3.calculoEntropiaAPosteriori());
        System.out.println("\nEquivocacion = " + equivocacion);
        entropiaA = c3.calculoEntropiaAPriori();
        entropiaB = c3.calculoEntropiaSalida();
        System.out.println("H(A) = " + entropiaA);
        System.out.println("H(B) = " + entropiaB);
        entropiaBA = c3.entropiaBA();
        System.out.println("H(B/A) = " + entropiaBA);
        System.out.println("\nInformacion mutua a partir de sucesos simultaneos: " + c3.calculoInformacionMutuaConSucesosSimultaneos());
        informacionMutuaAB = c3.calculoInformacionMutuaAB(equivocacion);
        System.out.println("I(A, B) = H(A) - H(A/B) = " + informacionMutuaAB);
        System.out.println("I(B, A) = H(B) - H(B/A) = " + c3.calculoInformacionMutuaBA(entropiaBA));
        System.out.println("\nEntropía afín (sucesos simultáneos) = " + c3.entropiaAfin());
        System.out.println("H(A, B) = H(A) + H(B) - I(A, B) = " + (entropiaA + entropiaB - informacionMutuaAB));
    }

}
