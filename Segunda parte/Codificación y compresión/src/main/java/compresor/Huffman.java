package compresor;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

public class Huffman extends Compresor{

    public static HashMap<Character,String> generarAlfabetoCodigo(HashMap<Character,Float> probabilidades){
		if(probabilidades.size() == 2) {
			HashMap<Character,String> codificacionH = new HashMap<Character,String>();
			Iterator<Character> itSimbolos = probabilidades.keySet().iterator();
			Character simbolo1 = itSimbolos.next(), simbolo2 = itSimbolos.next();
			if(probabilidades.get(simbolo1) < probabilidades.get(simbolo2)) {
				codificacionH.put(simbolo1, "0");
	    		codificacionH.put(simbolo2, "1");
			} else {
				codificacionH.put(simbolo1, "1");
		    	codificacionH.put(simbolo2, "0");
			}
			return codificacionH;
	    } else {
	    	HashMap<Character,Float> probabilidadesReducida = (HashMap<Character,Float>)probabilidades.clone();
	        //Se busca el simbolo con la menor probabilidad.
	        Character minimoSimbolo1 = getClaveMinimo(probabilidadesReducida);
	        Float sumaProbabilidades = probabilidadesReducida.get(minimoSimbolo1);
	        probabilidadesReducida.remove(minimoSimbolo1);
	        //Se busca el siguiente simbolo con menor probabilidad.
	        Character minimoSimbolo2 = getClaveMinimo(probabilidadesReducida);
	        sumaProbabilidades += probabilidadesReducida.get(minimoSimbolo2);
	        probabilidadesReducida.remove(minimoSimbolo2);
	        //Se conforma un simbolo cuya probabilidad es la suma de las probabilidades de los anteriores.
	        //Y queda conformada la fuente reducida.
	        //ACLARACION:Se agrega un simbolo que ya antes existia,ya que se asegura que el mismo no existe dentro de la fuente reducida.
	        //Tambien se debe a que el tipo de datos que usamos como clave en el hashMap es un caracter,si fuera string,podriamos generar
	        //un nuevo simbolo como combinacion de los anteriores.
	        probabilidadesReducida.put(minimoSimbolo1, sumaProbabilidades);   
	        //Se llama recursivamente al metodo con la fuente reducida.
	        HashMap<Character,String> codificacionH = generarAlfabetoCodigo(probabilidadesReducida);
	        String nuevoCodigoLista = codificacionH.get(minimoSimbolo1);
	        //Cuando se retorna luego de tener la codificacion base,se comienzan a agregar a la derecha ceros y unos a los codigos de los 
	        //simbolos que dieron lugar al simbolo "nuevo".
	        codificacionH.replace(minimoSimbolo1, nuevoCodigoLista+"0");
	        codificacionH.put(minimoSimbolo2, nuevoCodigoLista+"1");
	        return codificacionH;
	    }
	}

    private static Character getClaveMinimo(HashMap<Character,Float> probabilidades) {
    	Float minimaProb = (float) 1,probabilidad;
		Character minimoChar = null;
		Set<Character> claves = probabilidades.keySet();
		for(Character clave:claves) {
			probabilidad = probabilidades.get(clave);
			if(probabilidad <= minimaProb) {
				minimaProb = probabilidad;
				minimoChar = clave;
			}
		}
		return minimoChar;
    }
}
