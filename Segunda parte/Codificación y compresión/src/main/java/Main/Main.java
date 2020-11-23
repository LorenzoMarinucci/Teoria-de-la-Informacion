package Main;

import java.util.HashMap;

import compresor.ComprimirYDescomprimir;
import compresor.Huffman;
import compresor.RLC;
import compresor.Shanon_Fano;
import visualizacion.Visualizador;

public class Main {

    public static void main(String[] args) {
    	
    	System.out.println(RLC.comprimir("AABB CDD"));
    	System.out.println(RLC.descomprimir("  4 A 3   10 B 2 C 100   5"));
    	
    	Huffman h = new Huffman();
    	Shanon_Fano sf = new Shanon_Fano();
    	
    	HashMap<Character,Float> prob = new HashMap<Character,Float>();
    	prob.put('h',(float) 0.1);
    	prob.put('o',(float) 0.1);
    	prob.put('l',(float) 0.1);
    	prob.put('a',(float) 0.7);
    	
    	System.out.println(h.generarAlfabetoCodigo(prob));
    	//System.out.println(sf.generarAlfabetoCodigo(prob));
    	System.out.println(ComprimirYDescomprimir.comprimir("hola",h.generarAlfabetoCodigo(prob)));
    	System.out.println(ComprimirYDescomprimir.comprimir("hola",sf.generarAlfabetoCodigo(prob)));
    	
    	Visualizador.muestraAlfabeto(h.generarAlfabetoCodigo(prob));

    }

}
