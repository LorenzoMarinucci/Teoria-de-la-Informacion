package main;

import calculos.Canal;
import lectura.Lectura;

import javax.swing.text.StyledDocument;

public class Main {

    public static void main(String[] args) {

        Canal c1 = Lectura.leerDatos("canal1.txt");
        Canal c2 = Lectura.leerDatos("canal2.txt");
        Canal c3 = Lectura.leerDatos("canal3.txt");
        Double equivocacion;

        System.out.println("-----------CANAL 1-----------\n");
        equivocacion = c1.calculoEquivocacion(c1.calculoEntropiaAPosteriori());
        System.out.println("Equivocacion: " + equivocacion);
        System.out.println("Informacion mutua AB: " + c1.calculoInformacionMutuaAB(equivocacion));
        System.out.println("Informacion mutua BA: " + c1.calculoInformacionMutuaBA());
        System.out.println("Informacion mutua a partir de sucesos simultaneos: " + c1.calculoInformacionMutuaConSucesosSimultaneos());

        System.out.println("\n-----------CANAL 2-----------\n");
        equivocacion = c2.calculoEquivocacion(c2.calculoEntropiaAPosteriori());
        System.out.println("Equivocacion: " + equivocacion);
        System.out.println("Informacion mutua AB: " + c2.calculoInformacionMutuaAB(equivocacion));
        System.out.println("Informacion mutua BA: " + c2.calculoInformacionMutuaBA());
        System.out.println("Informacion mutua a partir de sucesos simultaneos: " + c2.calculoInformacionMutuaConSucesosSimultaneos());

        System.out.println("\n-----------CANAL 3-----------\n");
        equivocacion = c3.calculoEquivocacion(c3.calculoEntropiaAPosteriori());
        System.out.println("Equivocacion: " + equivocacion);
        System.out.println("Informacion mutua AB: " + c3.calculoInformacionMutuaAB(equivocacion));
        System.out.println("Informacion mutua BA: " + c3.calculoInformacionMutuaBA());
        System.out.println("Informacion mutua a partir de sucesos simultaneos: " + c3.calculoInformacionMutuaConSucesosSimultaneos());
    }

}
