package Main;

import java.util.ArrayList;
import java.util.HashMap;

import calculos.Calcular;
import compresor.ComprimirYDescomprimir;
import compresor.Huffman;
import compresor.RLC;
import compresor.Shannon_Fano;
import lectura.Lector;
import visualizacion.Visualizador;

public class Main {

    public static void main(String[] args) {
    	
    	String textoEspañol = Lector.leerArchivo("mdp-español.txt");
    	String textoAleman = Lector.leerArchivo("mdp-aleman.txt");
    	String codificacionRLC,codificacionHuffman,codificacionShannonFano;
    	HashMap<Character,String> alfabetoHuffman, alfabetoShannonFano;
    	HashMap<Character,Float> distribucionEspañol = Lector.generarDistribucion(textoEspañol);
    	HashMap<Character,Float> distribucionAleman = Lector.generarDistribucion(textoAleman);
    	Float entropia,longitudMedia;
    	
    	System.out.println("---TEXTO EN ESPAÑOL---");
    	System.out.println("\n---Distribucion de probabilidades---\n");
    	
    	Visualizador.muestraProbabilidades(distribucionEspañol);
    	
    	alfabetoHuffman = Huffman.generarAlfabetoCodigo(distribucionEspañol);
    	alfabetoShannonFano = Shannon_Fano.generarAlfabetoCodigo(distribucionEspañol);
    	codificacionRLC = RLC.comprimir(textoEspañol);
    	codificacionHuffman = Huffman.comprimir(textoEspañol, alfabetoHuffman);
    	codificacionShannonFano = Shannon_Fano.comprimir(textoEspañol, alfabetoHuffman);
    	entropia = Calcular.entropia(distribucionEspañol);
    	longitudMedia = Calcular.longitudMedia(distribucionEspañol, alfabetoHuffman);
    	
    	System.out.println("\n---HUFFMAN---");
    	System.out.println("\n---Alfabeto código---\n");
    	Visualizador.muestraAlfabeto(alfabetoHuffman);
    	System.out.println("\nTasa de compresión: " + Calcular.tasa_compresion(textoEspañol,codificacionHuffman));
    	System.out.println("\nRendimiento: " + Calcular.rendimiento(entropia, longitudMedia));
    	System.out.println("\nRedundancia: " + Calcular.redundancia(entropia, longitudMedia));
    	System.out.println("\n---------------------------------------------------------------------");
    	
    	System.out.println("\n---TEXTO EN ALEMÁN---");
    	System.out.println("\n---Distribucion de probabilidades---\n");
    	
    	Visualizador.muestraProbabilidades(distribucionAleman);
    	
    	alfabetoHuffman = Huffman.generarAlfabetoCodigo(distribucionAleman);
    	alfabetoShannonFano = Shannon_Fano.generarAlfabetoCodigo(distribucionAleman);
    	codificacionRLC = RLC.comprimir(textoAleman);
    	codificacionHuffman = Huffman.comprimir(textoAleman, alfabetoHuffman);
    	codificacionShannonFano = Shannon_Fano.comprimir(textoAleman, alfabetoHuffman);
    	entropia = Calcular.entropia(distribucionAleman);
    	longitudMedia = Calcular.longitudMedia(distribucionAleman, alfabetoHuffman);
    	
    	System.out.println("\n---HUFFMAN---");
    	System.out.println("\n---Alfabeto código---\n");
    	Visualizador.muestraAlfabeto(alfabetoHuffman);
    	System.out.println("\nTasa de compresión: " + Calcular.tasa_compresion(textoAleman,codificacionHuffman));
    	System.out.println("\nRendimiento: " + Calcular.rendimiento(entropia, longitudMedia));
    	System.out.println("\nRedundancia: " + Calcular.redundancia(entropia, longitudMedia));
    	
    }

}
